/**
 * 
 */
package day12;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author zhangchao
 * @date   2018年2月4日
 * 
 */
public class Client {

	public static void main(String[] args) throws InterruptedException {
		
		NioEventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group);
		b.channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
				sc.pipeline().addLast(new StringDecoder());
				sc.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture f1 = b.connect("127.0.0.1",8765);
//		ChannelFuture f2 = b.connect("127.0.0.1",8764);
		f1.channel().writeAndFlush(Unpooled.copiedBuffer("BBB$_".getBytes()));
//		Thread.sleep(1000);
		f1.channel().writeAndFlush(Unpooled.copiedBuffer("CCCC$_".getBytes()));
//		Thread.sleep(1000);
		f1.channel().writeAndFlush(Unpooled.copiedBuffer("DDDDD$_".getBytes()));
//		f2.channel().writeAndFlush(Unpooled.copiedBuffer("Hello World!".getBytes()));
		
		f1.channel().closeFuture().sync(); 
//		f2.channel().closeFuture().sync(); 
		
//		f1.addListener(ChannelFutureListener.CLOSE);
		group.shutdownGracefully();
	}
}
