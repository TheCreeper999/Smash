package fr.zak.smash;
import org.lwjgl.opengl.GL11;


public class JoueurMP {
	
	int direction = 0;
	float x, y;
	
	public void gen(){
		GL11.glColor3f(1F, 0F, 0f);
		GL11.glBegin(GL11.GL_QUADS);
		if(direction == 1){
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x, y + 20);
			GL11.glVertex2f(x + 20, y + 20);
			GL11.glVertex2f(x + 20, y);
		}
		if(direction == 0){
			GL11.glVertex2f(x - 5, y);
			GL11.glVertex2f(x - 5, y + 20);
			GL11.glVertex2f(x + 20 - 5, y + 20);
			GL11.glVertex2f(x + 20 - 5, y);
		}
		GL11.glEnd();
		GL11.glColor3f(1F, 1F, 1f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y + 20);
		GL11.glVertex2f(x, y + 15 + 20);
		GL11.glVertex2f(x + 15, y + 15 + 20);
		GL11.glVertex2f(x + 15, y + 20);
		GL11.glEnd();
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
}
