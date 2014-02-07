package fr.zak.smash.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;

public class Reception implements Runnable{

	private BufferedReader in = null;
	public static int direction;
	public static int x, y;
	
	public Reception(BufferedReader br){
		this.in = br;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			direction = Integer.parseInt(in.readLine());
			x = Integer.parseInt(in.readLine());
			y = Integer.parseInt(in.readLine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
