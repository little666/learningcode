package day02;

public class SyncException {

	private int i = 0;
	
	public synchronized void operation(){
		while(true){
			try {
				i++;
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName()+" ,i = "+i);
				if(i==10){
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" log info i = "+i);
//				throw new RuntimeException();
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		final SyncException se = new SyncException();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				se.operation();
			}
			
		},"t1");
		t1.start();
	}

}
