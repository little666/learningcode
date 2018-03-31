package url.uri;

import java.net.MalformedURLException;
import java.net.URL;

public class URLEquality {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			/*URL url = new URL("http://www.ibiblio.org/");
			URL url2 = new URL("http://ibiblio.org/");
			if(url2.equals(url)){
				System.out.println(url2+" is the same as "+url);
			}else{
				System.out.println(url2+" is not the same as "+url);
			}*/
			
			URL u1 = new URL("http://www.ncsa.uiuc.edu/HTMLPrimer.html#GS");
			URL u2 = new URL("http://www.ncsa.uiuc.edu/HTMLPrimer.html#HD");
			if(u1.sameFile(u2)){
				System.out.println(u1+" is the same file as \n"+u2);
			}else{
				System.out.println(u1+" is the not same file as \n"+u2);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e);
		}
	}

}
