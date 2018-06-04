import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();

		Display display = new Display();
		MapGenerator generator = new MapGenerator();
		display.setCities(generator.getCities());
		
		
		List<Salesman> salesmen = new LinkedList<>();
		
		TspAlgorithm bruteForce = new BruteForceTsp();
		List<City> bestRoute = generator.getCities(bruteForce.solve(generator.getNodes()));
		
		TspAlgorithm monkey = new MonkeyTsp();
		List<City> randomRoute = generator.getCities(monkey.solve(generator.getNodes()));
		
		salesmen.add(new Salesman(bestRoute, Color.GREEN, "Brute Force", display, -2));
		salesmen.add(new Salesman(randomRoute, Color.CYAN, "Monkey", display, 2));
		
		
		display.setSalesmen(salesmen);
		
		
		window.add(display);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 1000);
		window.setVisible(true);
		new Simulation(display).run();
	}
}
