package url.uri;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlSplitter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] arr = new String[]{
			"ftp://mp3:mp3@138.247.121.61:21000/c%3a/",
			"http://www.oreilly.com",
			"http://www.ibiblio.org/nywc/compositions.phtml?category=Piano",
			"http://admin@www.blackstar.com:8080/"
		};
		splitter(arr);
	}
	
	public static void splitter(String [] arr){
		for(int i =0;i<arr.length;i++){
			try {
				URL url = new URL(arr[i]);
				System.out.println("The URL is "+url);
				System.out.println("The scheme is "+url.getProtocol());
				System.out.println("The user info is "+url.getUserInfo());
				
				String host = url.getHost();
				if(host!=null){
					int atSign = host.indexOf("@");
					if(atSign!=-1){
						host = host.substring(atSign+1);
					}
					System.out.println("The host is "+host);
				}else{
					System.out.println("The host is null.");
				}
				
				System.out.println("The port is "+url.getPort());
				System.out.println("The path is "+url.getPath());
				System.out.println("The ref is "+url.getRef());
				System.out.println("The query string is "+url.getQuery());
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println(arr[i]+" is not a URL understand.");
			}
			System.out.println();
		}
	}

}
