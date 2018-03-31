/**
 * 
 */
package day12;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author zhangchao
 * @date   2018年2月4日
 * 
 */
public class Server {

	public static void main(String[] args) throws InterruptedException {
		//接受Client连接的线程组
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		//实际业务处理的线程组
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			//辅助类，对Server进行配置
			ServerBootstrap b = new ServerBootstrap();
			//将两个工作线程加入进来
			b.group(bossGroup,workerGroup);
			//指定使用通道的类型
			b.channel(NioServerSocketChannel.class);
			//一定要使用childHandler去绑定具体的事件处理器 
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//特殊分隔符
					ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
					ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new ServerHandler());
				}
			});
			//连接队列数
			b.option(ChannelOption.SO_BACKLOG, 128);
			//保持连接
			b.option(ChannelOption.SO_KEEPALIVE, true);
			//绑定指定的端口进行监听
			ChannelFuture f = b.bind(8765).sync();
//			ChannelFuture f1 = b.bind(8764).sync();
			
			f.channel().closeFuture().sync();
//			f1.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
