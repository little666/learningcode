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
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����ȴ�״̬..");
			Thread.sleep(3000);
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�ͷ���..");
			conditon.await();
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����ִ��..");
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
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����..");
			Thread.sleep(3000);
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"��������..");
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
