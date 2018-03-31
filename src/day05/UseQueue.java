package day05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class UseQueue {

	public static void main(String[] args) throws InterruptedException {
		/* 非阻塞队列
		 * ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");

		System.out.println(q.poll());
		System.out.println(q.size());
		System.out.println(q.peek());
		System.out.println(q.size());*/
		
		//阻塞队列
		
		//有界 必须传长度值
		/*ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		array.put("a");
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		array.add("e");
		System.out.println(array.offer("a", 2, TimeUnit.SECONDS));*/
		
		//无界
		/*LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.offer("f");
//		System.out.println(q.size());
//		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			System.out.println(string);
//		}
		List<String> list = new ArrayList<String>();
		System.out.println(q.drainTo(list,3));
		System.out.println(list.size());
		for(String string:list){
			System.out.println(string);
		}*/
		
		
		SynchronousQueue<String> q = new SynchronousQueue<String>();
		q.add("a");
		
	}

}
