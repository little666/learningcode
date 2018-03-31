/**
 * 
 */
package day12;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * @author zhangchao
 * @date   2018年2月4日
 * 
 */
public class ClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
//			ByteBuf buf = (ByteBuf)msg;
//			
//			byte[] data = new byte[buf.readableBytes()];
//			
//			buf.readBytes(data);
//			
//			String request = new String(data,"utf-8");
			String request = (String)msg;
			System.out.println("Client收到消息: "+request);
			
//			String response = "我是反馈信息";
//			ctx.write(Unpooled.copiedBuffer(response.getBytes()));
//			ctx.flush();
			
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
