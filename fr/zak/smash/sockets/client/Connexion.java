package fr.zak.smash.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connexion implements Runnable{

	Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	public static Thread t2, t3;
	int direction;
	float x, y;
	
	public Connexion(Socket s, int dire, float x, float y){

		socket = s;
		this.direction = dire;
		this.x = x;
		this.y = y;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			
			t2 = new Thread(new Emission(out, direction, x, y));
			t2.start();
			
			t3 = new Thread(new Reception(in));
			t3.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
