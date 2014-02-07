package fr.zak.smash;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


public class Joueur {

	public float x = 100, y = 100;
	int vSaut = 5;
	int v = 10;
	boolean isGround = true;
	boolean isJumping = false;
	int direction = 1;
	
	Keyboard kb;

	public void gen(int direc){
		if(direc == 1){
			direction = 1;
		}
		if(direc == 0){
			direction = 0;
		}
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

	@SuppressWarnings("static-access")
	public void update(){
		if(!isGround){
			if(y >= 110){
				y-=20;
			}
			if(y == 100){
				isGround = true;
				isJumping = false;
				new Thread(new Son("bin/res/bruits_de_pas_fichier_SF_saut_terre2.wav")).start();
				v = 10;
			}
		}
		
		if(isJumping){
//			v = 3;
			if(y <= 195){
				y+=vSaut;
			}
			if(y == 200){
				isGround = false;
			}
		}
		
		if(kb.isKeyDown(kb.KEY_D) && x <= 779){
			x += v;
			direction = 1;
		}
		
		if(kb.isKeyDown(kb.KEY_SPACE) && !isJumping){
			isJumping = true;
		}
		
		if(kb.isKeyDown(kb.KEY_Q) && x >= 1){
			x -= v;
			direction = 0;
		}		
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public int getDirection(){
		return direction;
	}
}
