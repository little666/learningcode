package day08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseExecutors {
	
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		ExecutorService pool1 = Executors.newSingleThreadExecutor();
		
		ExecutorService pool2 = Executors.newCachedThreadPool();
		
		Executors.newScheduledThreadPool(10);
	}
} 
