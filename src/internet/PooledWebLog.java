package internet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledWebLog {

	private static final int NUM_THREADS = 4;
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();
		
		try {
			FileInputStream fin = new FileInputStream(args[0]);
			InputStreamReader in = new InputStreamReader(fin,"UTF-8");
			BufferedReader bf = new BufferedReader(in);
			
			for(String entry=bf.readLine();bf!=null;entry=bf.readLine()){
				LookupTask task = new LookupTask(entry);
				Future<String> future = service.submit(task);
				LogEntry logEntry = new LogEntry(entry, future);
				results.add(logEntry);
			}
			
			for(LogEntry result:results){
				try {
					System.out.println(result.future.get());
				} catch (InterruptedException | ExecutionException e) {
					System.out.println(result.original);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		service.shutdown();
	}
	
	
	private static class LogEntry{
		String original;
		Future<String> future;
		
		public LogEntry(String original, Future<String> future) {
			this.original = original;
			this.future = future;
		}
	}
}
