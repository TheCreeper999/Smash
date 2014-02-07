package fr.zak.smash.sockets.client;

import java.io.PrintWriter;

public class Emission implements Runnable{

	private PrintWriter out = null;
	int direction;
	float x, y;
	
	public Emission(PrintWriter pw, int dire, float x, float y){
		this.out = pw;
		this.direction = dire;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run() {
		while(true){
			out.println(direction);
			out.flush();
			out.println(x);
			out.flush();
			out.println(y);
			out.flush();
		}
		
	}

}
