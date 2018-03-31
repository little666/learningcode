package day09;

import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantLock {

	private ReentrantLock lock = new ReentrantLock();
	
	public void method1(){
		lock.lock();
		try{
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入method1..");
			Thread.sleep(1000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出method1..");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	
	public void method2(){
		lock.lock();
		try{
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入method2..");
			Thread.sleep(2000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出method2..");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	
	public static void main(String[] args) {
		final UseReentrantLock useLock = new UseReentrantLock();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				useLock.method1();
				useLock.method2();
			}
			
		},"t1");
		t1.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
