package fr.zak.smash;
import org.lwjgl.opengl.GL11;


public class TirMP {

	public void gen(int x, int y){
		GL11.glVertex2f(x + 4, y + 9);
		GL11.glVertex2f(x + 4, y + 9 + 3);
		GL11.glVertex2f(x + 4 + 10, y + 9 + 3);
		GL11.glVertex2f(x + 4 + 10, y + 9);
	}
}
