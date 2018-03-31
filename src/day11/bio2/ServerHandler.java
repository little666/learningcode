package day11.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{

	private Socket socket;
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(),true);
			String body = null;
			while(true){
				body = in.readLine();
				if(body==null)break;
				System.out.println("Server:"+body);
				out.println("�������˻��͵���Ӧ����...");
			}
		} catch (Exception e) {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(out!=null){
				out.close();
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			socket = null;
		}
	
		
	}

}
