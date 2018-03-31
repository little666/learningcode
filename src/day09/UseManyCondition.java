package day09;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseManyCondition {

	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	
	public void method1(){
		lock.lock();
		try{
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"���뷽��m1�ȴ�...");
			c1.await();
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����m1����...");
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
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"���뷽��m2�ȴ�...");
			c1.await();
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����m2����...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void method3(){
		lock.lock();
		try{
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"���뷽��m3�ȴ�...");
			c2.await();
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����m3����...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void method4(){
		lock.lock();
		try{
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����...");
			c1.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public void method5(){
		lock.lock();
		try{
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����...");
			c2.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final UseManyCondition umc = new UseManyCondition();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				umc.method1();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				umc.method2();
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				umc.method3();
			}
		},"t3");
		
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				umc.method4();
			}
		},"t4");
		
		Thread t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				umc.method5();
			}
		},"t5");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t4.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t5.start();
		
	}

}
