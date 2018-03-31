package day09;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class UseReentrantReadWriteLock {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private ReadLock readLock = lock.readLock();
	private WriteLock writeLock = lock.writeLock();
	
	public void read(){
		readLock.lock();
		try{
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����...");
			Thread.sleep(3000);
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�˳�...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			readLock.unlock();
		}
	}
	
	public void write(){
		writeLock.lock();
		try{
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����...");
			Thread.sleep(3000);
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�˳�...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final UseReentrantReadWriteLock urw = new UseReentrantReadWriteLock();
	
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				urw.read();
			}
		},"t1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				urw.read();
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				urw.write();
			}
		},"t3");
		
		Thread t4 = new Thread(new Runnable() {
			public void run() {
				urw.write();
			}
		},"t4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
