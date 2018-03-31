package day04;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Tickets {

	public static void main(String[] args) {
		final Vector<String> tickets = new Vector<String>();
		
		for(int i=1;i<1000;i++){
			tickets.add("»ð³µÆ±"+i);
		}
//		for(Iterator iterator = tickets.iterator();iterator.hasNext();){
//			String  string = (String) iterator.next();
//			tickets.remove(20);
//		}
		
		for(int i=1;i<10;i++){
			new Thread("Ïß³Ì"+i){
				public void run() {
					while(true){
						if(tickets.isEmpty())break;
						System.out.println(Thread.currentThread().getName()+"---"+tickets.remove(0));
					}
				};
			}.start();
		}
	}

}
