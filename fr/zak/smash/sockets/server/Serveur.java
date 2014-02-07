package fr.zak.smash.sockets.server;
import java.io.IOException;
import java.net.ServerSocket;

public class Serveur{
	public static ServerSocket ss = null;
	public static Thread t;


	public static void main(String[] args) {

		try {
			ss = new ServerSocket(666);
			System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());

			t = new Thread(new Connexion(ss));
			t.start();

		} catch (IOException e) {
			System.err.println("Le port "+ss.getLocalPort()+" est déjà utilisé !");
		}

	}


}