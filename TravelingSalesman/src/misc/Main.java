package misc;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private JPanel displayContainer;
	private int problemSize = 10;
	private int selectedAlgo = 0;

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		initGui();
		setVisible(true);
	}

	private void initGui() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JPanel guiPanel = new JPanel();
		JButton travelingButton = new JButton("Traveling Animation");
		travelingButton.addActionListener(a -> initializeTravelling());

		JButton solvingButton = new JButton("Solving Animation");
		solvingButton.addActionListener(a -> initializeSolving());
		
		JTextField problemSizeField = new JTextField("", 4);
		problemSizeField.addActionListener( a -> problemSize = Integer.parseInt(problemSizeField.getText()));
		
		JComboBox selectedAlgoBox = new JComboBox<String>(new String [] {"Brute Force", "Greedy", "KOpt Random", "KOpt Greedy"});
		selectedAlgoBox.addActionListener(a -> selectedAlgo = selectedAlgoBox.getSelectedIndex());

		guiPanel.add(problemSizeField);
		guiPanel.add(travelingButton);
		guiPanel.add(solvingButton);
		guiPanel.add(selectedAlgoBox);

		add(guiPanel);
		displayContainer = new JPanel();
		add(displayContainer);
	}

	private void initializeSolving() {
		generator = new MapGenerator(problemSize);
		
		TspStep[] algorithms = new TspStep[4];
		
		SolvingDisplay display = new SolvingDisplay(generator.getCities());
		display.setPreferredSize(new Dimension(1000, 800));

		TspStep bruteForce = new BruteForceStep();
		bruteForce.setUp(generator.getNodes());
		algorithms[0] = bruteForce;

		TspStep greedy = new GreedyStep();
		greedy.setUp(generator.getNodes());
		algorithms[1] = greedy;
		
		TspStep koptFromRandom = new KoptStep(5);
		koptFromRandom.setUp(new MonkeySolve().solve(generator.getNodes()));
		algorithms[2] = koptFromRandom;
		
		TspStep koptFromGreedy = new KoptStep(5);
		koptFromGreedy.setUp(new GreedySolve().solve(generator.getNodes()));
		algorithms[3] = koptFromGreedy;


		displayContainer.removeAll();
		displayContainer.add(display);
		new SolvingAnimation(display, 60, generator, algorithms[selectedAlgo]).start();
	}

	private void initializeTravelling() {
		generator = new MapGenerator(problemSize);
		TravelDisplay display = new TravelDisplay();
		display.setPreferredSize(new Dimension(1000, 800));
		display.setCities(generator.getCities());
		List<Salesman> salesmen = new LinkedList<>();

		List<City> randomRoute = generator.getCities(new MonkeySolve().solve(generator.getNodes()));
		List<City> greedyRoute = generator.getCities(new GreedySolve().solve(generator.getNodes()));
		List<City> bestRoute = generator.getCities(new BruteForceSolve().solve(generator.getNodes()));
		List<City> kopt5Route = generator.getCities(new KoptSolve(5).solve(generator.getNodes()));
		List<City> kopt5FromGreedyRoute = generator
				.getCities(new KoptSolve(5).solve(new GreedySolve().solve(generator.getNodes())));

		// salesmen.add(new Salesman(randomRoute, Color.BLACK, "Monkey", display, -9));

		salesmen.add(new Salesman(greedyRoute, Color.RED, "Greedy", display, -3));
		salesmen.add(new Salesman(bestRoute, Color.BLACK, "Brute Force", display, 9));
		salesmen.add(new Salesman(kopt5Route, Color.BLUE, "K-Opt 5", display, -9));
		salesmen.add(new Salesman(kopt5FromGreedyRoute, Color.GREEN, "K-Opt 5 (Greedy)", display, 3));

		display.setSalesmen(salesmen);
		displayContainer.removeAll();
		displayContainer.add(display);
		new TravelAnimation(display, 60, 6).start();
	}

	private void genereateCSVData() {
		// @Finn solltest den Pfad anpassen wenn du bei dir ausführst
		CSVFile file = new CSVFile("C:\\Users\\jonah\\Documents\\Nordakademie\\travelingSalesmanDataWOBruteForce.csv");

		file.writeLine(new String[] { "nodes", /* "optimum (bruteForce)", */ "greedy", "KOpt5", "KOpt50" });
		TspSolve[] algorithms = new TspSolve[] { /* new BruteForceSolve(), */ new GreedySolve(), new KoptSolve(5),
				new KoptSolve(50) };

		// file.writeLine(new String[] {"nodes", "optimum (bruteForce)", "KOpt5", });
		// TspSolve[] algorithms = new TspSolve[] {new BruteForceSolve(), new
		// KoptSolve(5)};

		for (int i = 5; i < 151; i++) {
			generator = new MapGenerator(10);
			String[] iterationResult = new String[algorithms.length + 1];
			iterationResult[0] = Integer.toString(i);

			// algoritmen mit problemgroeße i durchgehen durchgehen
			for (int j = 0; j < algorithms.length; j++) {
				iterationResult[j + 1] = Double.toString(
						Math.floor(Node.routeLength(Arrays.asList(algorithms[j].solve(generator.getNodes())))));
			}

			file.writeLine(iterationResult);
		}
		file.save();
	}

}
