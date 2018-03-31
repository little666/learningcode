package day02;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUse {

	private static AtomicInteger count = new AtomicInteger(0);
	
	public  int multiAdd(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		count.addAndGet(1);
		count.addAndGet(2);
		count.addAndGet(3);
		count.addAndGet(4);
		return count.get();
	}
	
	public static void main(String[] args) {

		AtomicUse au = new AtomicUse();
		ArrayList<Thread> ts = new ArrayList<Thread>();
		for(int i=0;i<100;i++){
			ts.add(new Thread(new Runnable(){

				@Override
				public void run() {
					System.out.println(au.multiAdd());
				}
				
			}));
		}
		
		for(Thread t:ts){
			t.start();
		}
	}

}
