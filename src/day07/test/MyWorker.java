package day07.test;

public class MyWorker extends Worker {
	
	public static Object handle(Task input) {
		Object output = null;
		try {
			//��ʾ����task����ĺ�ʱ�����������ݵļӹ���Ҳ�����ǲ������ݿ�...
			Thread.sleep(500);
			
			output = input.getPrice();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
}
