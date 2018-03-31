/**
 * 
 */
package day15;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author zhangchao
 * @date   2018年3月21日
 * 
 */
public class RequestInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ip;
	private HashMap<String,Object> cpuPercMap;
	private HashMap<String,Object> memoryMap;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public HashMap<String, Object> getCpuPercMap() {
		return cpuPercMap;
	}
	public void setCpuPercMap(HashMap<String, Object> cpuPercMap) {
		this.cpuPercMap = cpuPercMap;
	}
	public HashMap<String, Object> getMemoryMap() {
		return memoryMap;
	}
	public void setMemoryMap(HashMap<String, Object> memoryMap) {
		this.memoryMap = memoryMap;
	}
	
	
	
}
