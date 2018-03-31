package day11.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	final static String ADDRESS = "127.0.0.1";
	final static int PORT = 8765;
	
	public static void main(String[] args) {
		
		Socket socket = null;
		BufferedReader r = null;
		PrintWriter p = null;
		
		try {
			socket = new Socket(ADDRESS,PORT);
			r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			p = new PrintWriter(socket.getOutputStream(),true);
			
			p.println("接收到客户端的请求数据..."); 
			String response = r.readLine();
			System.out.println("Client: "+response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(r!=null){
				try {
					r.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(p!=null){
				p.close();
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			socket = null;
		}
	
	
	}
	
}
