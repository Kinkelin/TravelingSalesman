package misc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import animation.SolvingAnimation;
import animation.TravelAnimation;
import mathematical.BruteForceAlgorithm;
import mathematical.BruteForceSolve;
import mathematical.BruteForceStep;
import mathematical.GreedySolve;
import mathematical.GreedyStep;
import mathematical.KoptSolve;
import mathematical.KoptStep;
import mathematical.MonkeySolve;
import mathematical.Node;
import mathematical.TspSolve;
import mathematical.TspStep;
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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(1000, 1000);
		//setVisible(true);
		//initializeSolving();
		//initializeTravelling();
		genereateCSVData();
	}

	private void initializeSolving() {
		generator = new MapGenerator(50);
		SolvingDisplay display = new SolvingDisplay(generator.getCities());

		TspStep bruteForce = new BruteForceStep();
		bruteForce.setUp(generator.getNodes());

		TspStep greedy = new GreedyStep();
		greedy.setUp(generator.getNodes());

		TspStep koptFromGreedy = new KoptStep(5);
		koptFromGreedy.setUp(new GreedySolve().solve(generator.getNodes()));
		
		TspStep koptFromRandom = new KoptStep(5);
		koptFromRandom.setUp(new MonkeySolve().solve(generator.getNodes()));

		add(display);
		new SolvingAnimation(display, 120, generator, koptFromRandom).run();
	}

	private void initializeTravelling() {
		generator = new MapGenerator(10);
		TravelDisplay display = new TravelDisplay();
		display.setCities(generator.getCities());
		List<Salesman> salesmen = new LinkedList<>();

		
		List<City> randomRoute = generator.getCities(new MonkeySolve().solve(generator.getNodes()));
		List<City> greedyRoute = generator.getCities(new GreedySolve().solve(generator.getNodes()));
		List<City> bestRoute = generator.getCities(new BruteForceSolve().solve(generator.getNodes()));
		List<City> kopt5Route = generator.getCities(new KoptSolve(5).solve(generator.getNodes()));
		List<City> kopt5FromGreedyRoute = generator.getCities(new KoptSolve(5).solve(new GreedySolve().solve(generator.getNodes())));
	
		//salesmen.add(new Salesman(randomRoute, Color.BLACK, "Monkey", display, -9));
		
		salesmen.add(new Salesman(greedyRoute, Color.RED, "Greedy", display, -3));
		salesmen.add(new Salesman(bestRoute, Color.BLACK, "Brute Force", display, 9));
		salesmen.add(new Salesman(kopt5Route, Color.BLUE, "K-Opt 5", display, -9));
		salesmen.add(new Salesman(kopt5FromGreedyRoute, Color.GREEN, "K-Opt 5 (Greedy)", display, 3));
		
		display.setSalesmen(salesmen);
		add(display);
		new TravelAnimation(display, 60, 6).run();
	}
	
	private void genereateCSVData() {
		// @Finn solltest den Pfad anpassen wenn du bei dir ausführst
		CSVFile file = new CSVFile("C:\\Users\\jonah\\Documents\\Nordakademie\\travelingSalesmanData.csv");
		
		file.writeLine(new String[] {"nodes", "optimum (bruteForce)", "greedy", "KOpt5", "KOpt50" });
		TspSolve[] algorithms = new TspSolve[] {new BruteForceSolve(), new GreedySolve(), new KoptSolve(5), new KoptSolve(50)};
		
		for(int i = 5; i < 12; i++) {
			generator = new MapGenerator(i);
			String[] iterationResult = new String[algorithms.length + 1];
			iterationResult[0] = Integer.toString(i);
			
			// algoritmen mit problemgroeße i durchgehen durchgehen
			for(int j = 0; j < algorithms.length; j++) {
				iterationResult[j + 1] = Double.toString(Math.floor(Node.routeLength(Arrays.asList(algorithms[j].solve(generator.getNodes())))));
			}
			
			file.writeLine(iterationResult);
		}
		file.save();
	}

}
