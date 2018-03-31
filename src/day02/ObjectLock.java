package day02;

public class ObjectLock {
	public void method1(){
		synchronized (this) {
			try {
				System.out.println("do method1..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void method2(){
		synchronized (ObjectLock.class) {
			try {
				System.out.println("do method2..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Object lock = new Object();
	public void method3(){
		synchronized (lock) {
			try {
				System.out.println("do method3..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ObjectLock objLock = new ObjectLock();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				objLock.method1();
			}
			
		});
		Thread t2 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				objLock.method2();
			}
			
		});
		Thread t3 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				objLock.method3();
			}
			
		});
		
		t1.start();
		t2.start();
		t3.start();
	}
}
