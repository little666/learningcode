package day03;

import java.util.ArrayList;
import java.util.List;

public class ListAdd2 {

	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("sdfsf");
	}

	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		 final ListAdd2 list2 = new ListAdd2();
		 
		 final Object lock = new Object();
		 
		 Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					synchronized (lock) {
						for(int i=0;i<10;i++){
							list2.add();
							System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�����һ��Ԫ�ء���");
							Thread.sleep(500);
							if(list2.size()==5){
								System.out.println("�Ѿ�����֪ͨ����");
								lock.notify();
							}
						}
					}
				} catch (Exception e) {

				}
			}
		 },"t1");
		 
		 Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					if(list2.size()!=5){
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�յ�֪ͨ�߳�ֹͣ����");
					throw new RuntimeException();
				}
			}
		},"t2");
		 
		t2.start();
		t1.start();
	}

}
