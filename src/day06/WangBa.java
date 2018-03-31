package day06;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable {

	private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();
	public boolean yinye = true;
	
	public void shangji(String name,String id,int money){
		Wangmin man = new Wangmin(name,id,1000*money+System.currentTimeMillis());
		System.out.println("����"+man.getName()+" ���֤"+man.getId()+"��Ǯ"+money+"�죬��ʼ����������");
		this.queue.add(man);
	}
	
	public void xiaji(Wangmin man){
		System.out.println("����"+man.getName()+" ���֤"+man.getId()+"ʱ�䵽�»�..");
	}
	
	@Override
	public void run() {
		while(yinye){
			try {
				Wangmin man = queue.take();
				xiaji(man);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		try{
			System.out.println("���ɿ�ʼӪҵ");
			WangBa siyu = new WangBa();
			Thread shangwang = new Thread(siyu);
			shangwang.start();
			
			siyu.shangji("·�˼�", "123", 1);
			siyu.shangji("·����", "234", 10);
			siyu.shangji("·�˱�", "345", 5);
		}catch(Exception e){
			
		}
	}

}
