package fr.zak.smash.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connexion implements Runnable{

	private PrintWriter out = null;
	private BufferedReader in = null;
	private Socket s;
	
	public Connexion(ServerSocket ss) {
		try {
			s = ss.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream());
			
			while(true){
				out.println(in.readLine());
				out.flush();
				out.println(in.readLine());
				out.flush();
				out.println(in.readLine());
				out.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
