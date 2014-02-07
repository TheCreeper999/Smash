package fr.zak.smash;
import java.io.IOException;
import java.net.Socket;

import fr.zak.smash.sockets.client.Connexion;


public class ThreadClient implements Runnable{

	public static Socket socket = null;
	public static Thread t1;
	int direction;
	float x, y;
	
	public ThreadClient(int dire, float x, float y){
		this.direction = dire;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run() {
		
		try {
			socket = new Socket("127.0.0.1", 666);
			
			t1 = new Thread(new Connexion(socket, direction, x, y));
			t1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
