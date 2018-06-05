package misc;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import animation.SolvingAnimation;
import animation.TravelAnimation;
import mathematical.BruteForceTsp;
import mathematical.GreedyAlgorithm;
import mathematical.GreedyStep;
import mathematical.KOptTsp;
import mathematical.MonkeyTsp;
import mathematical.TspAlgorithm;
import mathematical.TspStepByStep;
import visual.City;
import visual.TravelDisplay;
import visual.Salesman;
import visual.SolvingDisplay;

public class Main extends JFrame {
	public static void main(String[] args) {
		new Main();
	}

	private MapGenerator generator;

	public Main() {
		generator = new MapGenerator(100);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setVisible(true);

		 initializeSolving();
		//initializeTravelling();
	}

	private void initializeSolving() {
		SolvingDisplay display = new SolvingDisplay(generator.getCities());

		TspStepByStep bruteForce = new BruteForceTsp();
		bruteForce.setUp(generator.getNodes());

		TspStepByStep greedy = new GreedyStep();
		greedy.setUp(generator.getNodes());

		TspStepByStep kopt = new KOptTsp(10);
		kopt.setUp(generator.getNodes());

		add(display);
		new SolvingAnimation(display, 600, generator, kopt).run();
	}

	private void initializeTravelling() {
		TravelDisplay display = new TravelDisplay();
		display.setCities(generator.getCities());

		BruteForceTsp bruteForce = new BruteForceTsp();
		 List<City> bestRoute = generator.getCities(bruteForce.solve(generator.getNodes()));

		TspAlgorithm monkey = new MonkeyTsp();
		List<City> randomRoute = generator.getCities(monkey.solve(generator.getNodes()));

		TspAlgorithm greedy = new GreedyAlgorithm();
		List<City> greedyRoute = generator.getCities(greedy.solve(generator.getNodes()));

		TspAlgorithm kopt = new KOptTsp(100);
		List<City> koptRoute = generator.getCities(kopt.solve(generator.getNodes()));

		List<Salesman> salesmen = new LinkedList<>();
		salesmen.add(new Salesman(randomRoute, Color.RED, "Monkey", display, -9));
		salesmen.add(new Salesman(greedyRoute, Color.BLUE, "Greedy", display, -3));
		salesmen.add(new Salesman(bestRoute, Color.GREEN, "Brute Force", display, 3));
		salesmen.add(new Salesman(koptRoute, Color.MAGENTA, "K-Opt", display, 9));
		display.setSalesmen(salesmen);

		add(display);
		new TravelAnimation(display, 60, 2.0).run();
	}

}
