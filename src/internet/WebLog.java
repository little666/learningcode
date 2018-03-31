package internet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class WebLog {
	
	public static void main(String[] args) {
		try {
			FileInputStream fi = new FileInputStream(args[0]);
			InputStreamReader in = new InputStreamReader(fi);
			BufferedReader bf = new BufferedReader(in);
			
			for(String entry = bf.readLine();entry!=null;entry = bf.readLine()){
				int index = entry.indexOf(" ");
				String ip = entry.substring(0,index);
				String rest = entry.substring(index);
				
				InetAddress address = InetAddress.getByName(ip);
				System.out.println(address.getHostName()+rest);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
