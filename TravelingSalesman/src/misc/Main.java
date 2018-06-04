package misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import mathematical.BruteForceTsp;
import mathematical.EasiestPathTsp;
import mathematical.MonkeyTsp;
import mathematical.TspAlgorithm;
import visual.City;
import visual.Display;
import visual.Salesman;
import visual.Simulation;

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
		
		TspAlgorithm easiestPath = new EasiestPathTsp();
		List<City> easiestRoute = generator.getCities(easiestPath.solve(generator.getNodes()));
		
		salesmen.add(new Salesman(randomRoute, Color.RED, "Monkey", display, -5));
		salesmen.add(new Salesman(easiestRoute, Color.YELLOW, "Easiest Path", display, 0));
		
		salesmen.add(new Salesman(bestRoute, Color.GREEN, "Brute Force", display, 5));
		
		display.setSalesmen(salesmen);
		
		
		window.add(display);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 1000);
		window.setVisible(true);
		new Simulation(display).run();
	}
}
