package com.yzy.start;

import com.yzy.controller.WebsocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：hpp
 * @date：2018/7/5 11:15
 * @description:
 */

public class WebSocketServer {
    final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    int port;

    public WebSocketServer(int port) {
        this.port = port;
        start(port);
    }

    public void start(int port) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //开启服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup,workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast("http-codec",new HttpServerCodec());
                    sc.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));
                    sc.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                    sc.pipeline().addLast("handler",new WebsocketHandler());
                }
            });
            logger.info("websocket服务端已开启,等待客户端连接...");
            Channel channel = serverBootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //退出程序
            eventLoopGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


}
