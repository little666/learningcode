package day04;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

	//1 ��Ҫһ����װԪ�صļ���
	private final LinkedList<Object> list = new LinkedList<Object>();

	//2 ��Ҫһ��������
	private AtomicInteger count = new AtomicInteger(0);
	
	//3 ��Ҫָ�����޺�����
	private final int minSize = 0;
	private final int maxSize;
	
	//4���췽��
	public MyQueue(int size){
		this.maxSize = size;
	}

	//5��ʼ��һ��������Զ����
	private final Object lock = new Object();
	
	//put����
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
			//����Ԫ��
			list.add(obj);
			//�������ۼ�
			count.incrementAndGet();
			//��������һ���߳�
			lock.notify();
			System.out.println("�¼����Ԫ��Ϊ��"+obj);
		}
	}
	
	//take����
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
			//�Ƴ�Ԫ��
			ret = list.removeFirst();
			//�������ݼ�
			count.decrementAndGet();
			//��������һ���߳�
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
		
		System.out.println("��ǰ�������ȣ�"+queue.getSize());
		
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
				System.out.println("�Ƴ�Ԫ��Ϊ��"+o1);
				Object o2 = queue.take();
				System.out.println("�Ƴ�Ԫ��Ϊ��"+o2);
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
