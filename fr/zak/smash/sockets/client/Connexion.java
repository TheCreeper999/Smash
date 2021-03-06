package fr.zak.smash.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.zak.smash.Joueur;
import fr.zak.smash.JoueurMP;

public class Connexion implements Runnable{

	Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	public static Thread t2, t3;
	Joueur j;
	JoueurMP jmp;
	
	public Connexion(Socket s, Joueur j, JoueurMP jmp){

		socket = s;
		this.j = j;
		this.jmp = jmp;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			
			t2 = new Thread(new Emission(out, j));
			t2.start();
			
			t3 = new Thread(new Reception(in, jmp));
			t3.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
