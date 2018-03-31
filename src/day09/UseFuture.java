package day09;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{

	private String para;
	
	public UseFuture(String para){
		this.para = para;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String queryStr = "query";
		FutureTask<String> future = new FutureTask<>(new UseFuture(queryStr));
		FutureTask<String> future2 = new FutureTask<>(new UseFuture(queryStr));
		ExecutorService exe = Executors.newFixedThreadPool(2);
		Future f = exe.submit(future);
		Future f2 = exe.submit(future2);
		System.out.println("请求完毕");
//		System.out.println(f.get());
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("数据："+future.get());
		System.out.println("数据："+future2.get());
		
		exe.shutdown();
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(5000);
		String result = this.para +"处理完成";
		return result;
	}

}
