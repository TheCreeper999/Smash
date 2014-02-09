package fr.zak.smash.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;

import fr.zak.smash.JoueurMP;

public class Reception implements Runnable{

	private BufferedReader in = null;
	JoueurMP jmp;
	
	public Reception(BufferedReader br, JoueurMP jmp){
		this.in = br;
		this.jmp = jmp;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				jmp.setDirection(Integer.parseInt(in.readLine()));
				jmp.setX(Integer.parseInt(in.readLine()));
				jmp.setY(Integer.parseInt(in.readLine()));
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
