package day04;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

	//1 需要一个承装元素的集合
	private final LinkedList<Object> list = new LinkedList<Object>();

	//2 需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);
	
	//3 需要指定上限和下限
	private final int minSize = 0;
	private final int maxSize;
	
	//4构造方法
	public MyQueue(int size){
		this.maxSize = size;
	}

	//5初始化一个对象永远加锁
	private final Object lock = new Object();
	
	//put方法
	public void put(Object obj){
		synchronized (lock) {
			while(count.get()==this.maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//加入元素
			list.add(obj);
			//计数器累加
			count.incrementAndGet();
			//唤醒另外一个线程
			lock.notify();
			System.out.println("新加入的元素为："+obj);
		}
	}
	
	//take方法
	public Object take(){
		Object ret = null;
		synchronized (lock) {
			while(count.get()==this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//移除元素
			ret = list.removeFirst();
			//计数器递减
			count.decrementAndGet();
			//唤醒另外一个线程
			lock.notify();
		}
		return ret;
	}
	
	public int getSize(){
		return this.count.get();
	}
	
	public static void main(String[] args) {
		MyQueue queue = new MyQueue(5);
		queue.put("a");
		queue.put("b");
		queue.put("c");
		queue.put("d");
		queue.put("e");
		
		System.out.println("当前容器长度："+queue.getSize());
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				queue.put("f");
				queue.put("g");
			}
		},"t1");
		t1.start();
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				Object o1 = queue.take();
				System.out.println("移除元素为："+o1);
				Object o2 = queue.take();
				System.out.println("移除元素为："+o2);
			}
		},"t2");
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t2.start();
	}
}
