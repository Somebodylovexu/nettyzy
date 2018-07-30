package com.yzy.socket;


import com.yzy.common.base.SocketMessage;
import com.yzy.dataentry.connect.AlreadyClient;
import com.yzy.operation.service.ReceiveClientFactory;
import com.yzy.operation.service.impl.AbsIReceiveClientMsg;
import com.yzy.operation.service.impl.AnalysisMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Jhy
 * @Date: 2018-07-04 
 */
@Slf4j
@Component
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    @Autowired
    private AnalysisMsg analysisMsg;

    @Autowired
    private AlreadyClient alreadyClient;

    @Autowired
    private ReceiveClientFactory receiveClientFactory;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //channel失效，从Map中移除
        alreadyClient.remove((SocketChannel) ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("server exception:" + cause.getMessage());
    }

    //读取客户端消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        //解析数据
        SocketMessage socketMessage = analysisMsg.analysis(message);
        AbsIReceiveClientMsg rcm = receiveClientFactory.createRcm(socketMessage);
        if (rcm != null && rcm.check()) {
            //根据业务处理
            rcm.setChx(ctx).answer().task().append();
        }

    }

}