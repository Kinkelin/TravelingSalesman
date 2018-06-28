package animation;

import javax.swing.JPanel;

public abstract class Animation extends Thread {
	protected static final long THREAD_SLEEP = 1;
	
	protected double desiredFps;
	protected JPanel display;
	protected boolean running = false;

	public Animation(JPanel display, double desiredFps) {
		this(display);
		this.desiredFps = desiredFps;
	}

	public Animation(JPanel display) {
		this.display = display;
		desiredFps = 60;
	}
	
	protected int fps;

	protected abstract void animationStep();

	@Override
	public void run() {
		running = true;
		fps = 0;

		int framesInCurrentSecond = 0;
		long lastSecond = System.currentTimeMillis();
		long lastFrameTime = 0;
		long currentTime;
		System.out.println("Simulation gestartet");
		while (running) {
			currentTime = System.currentTimeMillis();

			if (currentTime - lastFrameTime >= Math.floor(1000 / desiredFps)) {
				animationStep();
				display.repaint();
				display.revalidate();

				framesInCurrentSecond += 1;
				lastFrameTime = currentTime;
			}

			if ((currentTime - lastSecond) > 1000) {
				fps = framesInCurrentSecond;
				framesInCurrentSecond = 0;
				lastSecond = currentTime;
			}

			try {
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Simulation beendet");
	}
}
