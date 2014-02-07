package fr.zak.smash;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import fr.zak.smash.sockets.client.Reception;
 
public class Launch {

	public Tirs tirs = new Tirs();
	
	public Joueur j = new Joueur();
	
	public JoueurMP jmp = new JoueurMP();
	
	private static Launch instance = new Launch();

	/** time at last frame */
	long lastFrame;
 
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
 
	public static Launch instance(){
		return instance;
	}
	
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
 
			update(delta);
			new Thread(new ThreadClient(j.getDirection(), j.getX(), j.getY())).start();
			renderGL();
 
			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
 
		Display.destroy();
	}
	int rapidite = 0;
	int level = 0;
	public void update(int delta) {
		//if (Keyboard.isKeyPressed(...
		j.update();
		tirs.update(j.getX(), j.getY());
		updateFPS();
	}
 
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
 
	    return delta;
	}
 
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
 
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
 
	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public void renderGL() {
		GL11.glPushMatrix();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glColor3f(0.2F, 0.6F, 0F);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(0, 100);
		GL11.glVertex2f(800, 100);
		GL11.glVertex2f(800, 0);
		GL11.glEnd();
		GL11.glColor3f(0, 0.4F, 1);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(0, 100);
		GL11.glVertex2f(0, 600);
		GL11.glVertex2f(800, 600);
		GL11.glVertex2f(800, 100);
		GL11.glEnd();
		GL11.glColor3f(1F, 0.7F, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(700, 500);
		GL11.glVertex2f(700, 560);
		GL11.glVertex2f(760, 560);
		GL11.glVertex2f(760, 500);
		GL11.glEnd();
		GL11.glColor3f(1F, 1F, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(705, 505);
		GL11.glVertex2f(705, 555);
		GL11.glVertex2f(755, 555);
		GL11.glVertex2f(755, 505);
		GL11.glEnd();
		GL11.glColor3f(1F, 1F, 0f);
		GL11.glBegin(GL11.GL_QUADS);
		tirs.gen();
		GL11.glEnd();
		j.gen(tirs.getDirection());
		jmp.gen(Reception.direction, Reception.x, Reception.y);
		GL11.glPopMatrix();
	}

	
	public static void main(String[] argv) {
		Launch launch = new Launch();
		launch.start();
	}
}