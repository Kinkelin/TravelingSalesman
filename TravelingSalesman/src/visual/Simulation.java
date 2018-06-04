package visual;
import java.util.List;

public class Simulation implements Runnable {
	public static long THREAD_SLEEP = 1;
	public static double DESIRED_FPS = 60;
	public static double STEP_MOVEMENT = 9;

	private Display display;

	private boolean running = false;

	public Simulation(Display display) {
		this.display = display;
	}

	private int fps;

	private void simulationStep() {
		List<Salesman> salesmen = display.getSalesmen();
		boolean salesmanBusy = false;
		for (Salesman salesman : salesmen) {
			salesmanBusy = salesman.move(STEP_MOVEMENT) ? true : salesmanBusy;
		}
		running = salesmanBusy;
	}

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

			if (currentTime - lastFrameTime >= Math.floor(1000 / DESIRED_FPS)) {

				simulationStep();
				display.repaint();
				display.revalidate();

				framesInCurrentSecond += 1;
				lastFrameTime = currentTime;
			}

			if ((currentTime - lastSecond) > 1000) {
				fps = framesInCurrentSecond;
				display.setFps(fps);
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
