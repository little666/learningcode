package day06;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {

	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
		
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("任务1");
		Task t2 = new Task();
		t2.setId(6);
		t2.setName("任务2");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("任务3");
		
		q.add(t1);
		q.add(t2);
		q.add(t3);
		
		System.out.println(q.toString());
		System.out.println(q.take().getId());
		System.out.println(q.toString());
		System.out.println(q.take().getId());
//		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//			Task task = (Task) iterator.next();
//			System.out.println(task.getName());
//		}
		
		
	}
}
