package internet;

import java.net.InetAddress;
import java.util.concurrent.Callable;

public class LookupTask implements Callable<String> {

	public String line;
	
	public LookupTask(String line) {
		this.line = line;
	}

	@Override
	public String call() throws Exception {
		try {
			int index = line.indexOf(" ");
			String ip = line.substring(0,index);
			String theRest = line.substring(index);
			String hostName = InetAddress.getByName(ip).getHostName();
			return hostName + " " +theRest;
		} catch (Exception e) {
			return line;
		}
	}

}
