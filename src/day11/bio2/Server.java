package day11.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import day11.bio.ServerHandler;

public class Server {

	final static int PORT = 8765;
	
	public static void main(String[] args) {
		
		ServerSocket server = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server start");
			Socket socket = null;
			HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
			while(true){
				socket = server.accept();
				executorPool.execute(new ServerHandler(socket));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			if(server!=null){
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			server = null;
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(out!=null){
				out.close();
			}
		}
		
		
	}
}
