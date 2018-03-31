package day09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class UseSemaphore {

	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool();

		final Semaphore s = new Semaphore(5);
		
		for(int index = 0;index<20;index++){
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						//获得许可
						s.acquire();
						System.out.println("Accessing:"+NO);
						Thread.sleep((long)(Math.random()*10000));
						s.release();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			};
			exe.execute(run);
		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
