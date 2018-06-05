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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setVisible(true);
		 initializeSolving();
//		initializeTravelling();
	}

	private void initializeSolving() {
		generator = new MapGenerator(50);
		SolvingDisplay display = new SolvingDisplay(generator.getCities());

		TspStepByStep bruteForce = new BruteForceTsp();
		bruteForce.setUp(generator.getNodes());

		TspStepByStep greedy = new GreedyStep();
		greedy.setUp(generator.getNodes());

		TspStepByStep kopt = new KOptTsp(5);
		kopt.setUp(generator.getNodes());

		add(display);
		new SolvingAnimation(display, 15, generator, kopt).run();
	}

	private void initializeTravelling() {
		generator = new MapGenerator(10);
		TravelDisplay display = new TravelDisplay();
		display.setCities(generator.getCities());
		List<Salesman> salesmen = new LinkedList<>();

		
		//List<City> randomRoute = generator.getCities(new MonkeyTsp().solve(generator.getNodes()));
		List<City> greedyRoute = generator.getCities(new GreedyAlgorithm().solve(generator.getNodes()));
		List<City> bestRoute = generator.getCities(new BruteForceTsp().solve(generator.getNodes()));
		List<City> kopt5Route = generator.getCities(new KOptTsp(5).solve(generator.getNodes()));
	
		//salesmen.add(new Salesman(randomRoute, Color.BLACK, "Monkey", display, -9));
		salesmen.add(new Salesman(greedyRoute, Color.RED, "Greedy", display, -3));
		salesmen.add(new Salesman(bestRoute, Color.GREEN, "Brute Force", display, 3));
		salesmen.add(new Salesman(kopt5Route, Color.BLUE, "K-Opt 5", display, -9));
		
		display.setSalesmen(salesmen);
		add(display);
		new TravelAnimation(display, 60, 6).run();
	}

}
