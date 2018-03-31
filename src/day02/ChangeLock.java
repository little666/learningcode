package day02;

public class ChangeLock {

	private String lock = "lock";
	private void method(){
		synchronized (lock) {
			try {
				System.out.println("��ǰ�̣߳� "+Thread.currentThread().getName()+"��ʼ");
				lock="change lock";
				Thread.sleep(2000);
				System.out.println("��ǰ�̣߳� "+Thread.currentThread().getName()+"����");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ChangeLock changeLock = new ChangeLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				changeLock.method();
			}
		},"t1");

		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				changeLock.method();
			}
		},"t2");
		
		t1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
	}

}
