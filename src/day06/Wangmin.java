package day06;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Wangmin implements Delayed{

	private String id;
	private String name;
	private long endTime;
	private TimeUnit timeUnit = TimeUnit.SECONDS;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Wangmin( String name,String id,long endTime) {
		this.id = id;
		this.name = name;
		this.endTime = endTime;
	}
	@Override
	public int compareTo(Delayed o) {
		Wangmin w = (Wangmin) o;
		return this.getDelay(this.timeUnit)-w.getDelay(this.timeUnit)>0?1:0;
	}
	@Override
	public long getDelay(TimeUnit unit) {
		return endTime - System.currentTimeMillis();
	}
	
	
}
