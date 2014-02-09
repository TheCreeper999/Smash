package fr.zak.smash;
import java.io.IOException;
import java.net.Socket;

import fr.zak.smash.sockets.client.Connexion;


public class ThreadClient implements Runnable{

	public static Socket socket = null;
	public static Thread t1;
	Joueur j;
	JoueurMP jmp;
	
	public ThreadClient(Joueur j, JoueurMP jmp){
		this.j = j;
		this.jmp = jmp;
	}
	
	@Override
	public void run() {
		
		try {
			socket = new Socket("127.0.0.1", 666);
			
			t1 = new Thread(new Connexion(socket, j, jmp));
			t1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
