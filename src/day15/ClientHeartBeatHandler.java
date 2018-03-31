/**
 * 
 */
package day15;


import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * @author zhangchao
 * @date   2018年3月21日
 * 
 */
public class ClientHeartBeatHandler extends ChannelHandlerAdapter {

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private ScheduledFuture<?> heartBeat;
	
	private InetAddress addr;
	
	private static final String SUCCESS_KEY = "auth_success_key";
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		System.out.println("Client IP:"+ip);
		String key = "1234";
		String auth = ip+","+key;
		ctx.writeAndFlush(auth);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			if(msg instanceof String){
				String ret = (String)msg;
				if(SUCCESS_KEY.equals(ret)){
					this.heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx), 0, 2, TimeUnit.SECONDS);
					System.out.println(msg);
				}else{
					System.out.println(msg);
				}
			}else{
				System.out.println(msg);
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if(heartBeat !=null ){
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);
	}

	private class HeartBeatTask implements Runnable{

		private final ChannelHandlerContext ctx;
		
		public HeartBeatTask(ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			try {
				RequestInfo info = new RequestInfo();
				info.setIp(addr.getHostAddress());
				Sigar sigar = new Sigar();
				CpuPerc cpuPerc = sigar.getCpuPerc();
				HashMap<String, Object> cpuPercMap = new HashMap<String,Object>();
				cpuPercMap.put("combined", cpuPerc.getCombined());
				cpuPercMap.put("user", cpuPerc.getUser());
				cpuPercMap.put("sys", cpuPerc.getSys());
				cpuPercMap.put("wait", cpuPerc.getWait());
				cpuPercMap.put("idle", cpuPerc.getIdle());
				
				Mem mem = sigar.getMem();
				HashMap<String, Object> memoryMap = new HashMap<String,Object>();
				memoryMap.put("total", mem.getTotal()/1024L);
				memoryMap.put("used", mem.getUsed()/1024L);
				memoryMap.put("free", mem.getFree()/1024L);
				
				info.setCpuPercMap(cpuPercMap);
				info.setMemoryMap(memoryMap);
				
				ctx.writeAndFlush(info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
}
