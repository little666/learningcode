package day09;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseCondition {

	private Lock lock = new ReentrantLock();
	private Condition conditon = lock.newCondition();

	public void method1(){
		lock.lock();
		try{
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入等待状态..");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"释放锁..");
			conditon.await();
			System.out.println("当前线程："+Thread.currentThread().getName()+"继续执行..");
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
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入..");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"发出唤醒..");
			conditon.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
	
		final UseCondition useConditon = new UseCondition();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				useConditon.method1();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				useConditon.method2();
			}
		},"t2");
	
		t1.start();
		t2.start();
	}

}
