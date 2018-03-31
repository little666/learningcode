package day09;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch countDown = new CountDownLatch(1);
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					System.out.println("�����߳�t1"+"�ȴ������̴߳������...");
					countDown.await();
					System.out.println("t1�̼߳���ִ��...");
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				
			}
			
		});

		Thread t2 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				try {
					System.out.println("t2�߳̽��г�ʼ������...");
					Thread.sleep(3000);
					System.out.println("t2�̳߳�ʼ�����,֪ͨt1�̼߳���...");
					countDown.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});

		Thread t3 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				try {
					System.out.println("t3�߳̽��г�ʼ������...");
					Thread.sleep(4000);
					System.out.println("t3�̳߳�ʼ�����,֪ͨt1�̼߳���...");
					countDown.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		t1.start();
		t2.start();
		t3.start();
		
	}

}
