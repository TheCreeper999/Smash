package fr.zak.smash.sockets.client;

import java.io.PrintWriter;

import fr.zak.smash.Joueur;

public class Emission implements Runnable{

	private PrintWriter out = null;
	Joueur j;
	
	public Emission(PrintWriter pw, Joueur j){
		this.out = pw;
		this.j = j;
	}
	
	@Override
	public void run() {
		while(true){
			out.println(j.getDirection());
			out.flush();
			out.println(j.getX());
			out.flush();
			out.println(j.getY());
			out.flush();
		}
		
	}

}
