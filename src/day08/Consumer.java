package day08;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;
	
	public Consumer(BlockingQueue<Data> queue) {
		this.queue = queue;
	}

	private static Random r = new Random();
	
	@Override
	public void run() {
		while(true){
			try {
				Data data = this.queue.take();
				Thread.sleep(r.nextInt(1000));
				System.out.println("��ǰ�����̣߳�"+Thread.currentThread().getName()+",���ѳɹ�������idΪ��"+data.getId());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
