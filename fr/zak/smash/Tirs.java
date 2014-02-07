package fr.zak.smash;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;


public class Tirs {

	float x = -20, y = -20;
	
	float dist = 0;

	int vTir = 20;

	Mouse mouse;
	/**
	 * 0 = gauche
	 * 1 = droite
	 */
	int direction = -1;

	@SuppressWarnings("static-access")
	public void update(float jX, float jY){
		if(mouse.isButtonDown(0)){
			if(jX + 10 >= mouse.getX() && direction == -1){
				direction = 0;
				this.x = jX;
				this.y = jY;
				dist = x - 1000;
				new Thread(new Son("bin/res/heat-vision.wav")).start();
			}
			if(jX + 10 < mouse.getX() && direction == -1){
				direction = 1;
				this.x = jX;
				this.y = jY;
				dist = x + 1000;
				new Thread(new Son("bin/res/heat-vision.wav")).start();
			}
		}

		if(direction == 1){
			if(x < dist){
				x+=vTir;
			}
			if(x >= dist){
				direction = -1;
				dist = 0; 
			}
		}
		if(direction == 0){
			if(x > dist){
				x-=vTir;
			}
			if(x <= dist){
				direction = -1;
				dist = 0;
			}
		}

	}

	public void gen(){
		GL11.glVertex2f(x + 4, y + 9);
		GL11.glVertex2f(x + 4, y + 9 + 3);
		GL11.glVertex2f(x + 4 + 10, y + 9 + 3);
		GL11.glVertex2f(x + 4 + 10, y + 9);
	}
	
	public int getDirection(){
		return direction;
	}
}
