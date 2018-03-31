package day04;

public class DoubleCheckSingleton {

	private static DoubleCheckSingleton dcs;
	
	public static DoubleCheckSingleton getDcs(){
		if(dcs==null){
			try {
				//模拟初始化对象的准备时间
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (DoubleCheckSingleton.class) {
				if(dcs==null){
					dcs = new DoubleCheckSingleton();
				}
			}
		}
		return dcs;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDcs().hashCode());
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDcs().hashCode());
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDcs().hashCode());
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
