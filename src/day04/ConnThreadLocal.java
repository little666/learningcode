package day04;

public class ConnThreadLocal {

	public static ThreadLocal<String> th = new ThreadLocal<String>();
	
	public void setTh(String value){
		th.set(value);
	}
	
	public void getTh(){
		System.out.println(Thread.currentThread().getName()+":"+this.th.get());
	}
	
	public static void main(String[] args) {
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				ct.setTh("ÕÅÈý");
				ct.getTh();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					ct.getTh();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		},"t2");
		
		t1.start();
		t2.start(); 
	}

}
