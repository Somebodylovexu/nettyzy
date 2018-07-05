package com.yzy.controller;

import com.yzy.common.base.type.EchartsSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：hpp
 * @date：2018/7/5 11:31
 * @description:
 */
public class WebsocketHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(WebsocketHandler.class);
    private WebSocketServerHandshaker webSocketServerHandshaker;


    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        logger.info("客户端与服务端第一次连接" + context.toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (o instanceof FullHttpRequest) {
            //处理http握手请求
            httpHandler();
        } else if (o instanceof WebSocketFrame) {
            //处理websocket任务
            websocketHandler(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    public void httpHandler() {
        //这个方法可以不实现
    }

    public void websocketHandler(ChannelHandlerContext context, WebSocketFrame webSocketFrame) {
        if (webSocketFrame instanceof CloseWebSocketFrame) {//判断是否是关闭websocket的指令
            webSocketServerHandshaker.close(context.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
        }
        if (webSocketFrame instanceof PingWebSocketFrame) {//判断是否是ping消息
            context.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        if (!(webSocketFrame instanceof TextWebSocketFrame)) {//判断是否是二进制消息
            System.out.println("不支持二进制消息");
            throw new RuntimeException(this.getClass().getName());
        }
        //返回应答消息
        //获取客户端向服务端发送的消息
        String type = ((TextWebSocketFrame) webSocketFrame).text();
        //为每个连接做session处理
        EchartsSession.put(type, context.channel());
        logger.info("已将用户加入{" + type + "}通道");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        logger.info(throwable.getMessage());
        context.close();
    }
}
