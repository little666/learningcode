package day03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatch1 {

	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("");
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		final CountDownLatch1 latch1 = new CountDownLatch1();
		
		final CountDownLatch count = new CountDownLatch(1);
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					latch1.add();
					System.out.println("当前线程："+Thread.currentThread().getName()+"添加了一个元素。。");
					try {
						Thread.sleep(500);
						if(latch1.size()==5){
							System.out.println("已经发出通知。。");
							count.countDown();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				if(latch1.size()!=5){
					try {
						count.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("当前线程："+Thread.currentThread().getName()+" 收到通知停止线程。。");
				throw new RuntimeException();
			}
			
		},"t2");
		
		t2.start();
		t1.start();
	}

}
