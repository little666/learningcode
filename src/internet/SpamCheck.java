package internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheck {

	public static final String BLACKHOLE = "sbl.spamhaus.org";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(String arg:args){
			if(isSpammer(arg)){
				System.out.println(arg+" is a known spammer.");
			}else{
				System.out.println(arg+" appears legitimate.");
			}
		}
	}
	
	public static boolean isSpammer(String arg){
		InetAddress address;
		try {
			address = InetAddress.getByName(arg);
			byte[]quad = address.getAddress();
			String query = BLACKHOLE;
			for(byte octet:quad){
				int unsignedByte=octet<0?octet+256:octet;
				query = unsignedByte+"."+query;
			}
			InetAddress.getByName(query);
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
