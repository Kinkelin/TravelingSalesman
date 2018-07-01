package misc;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.nio.file.Paths;
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
	private ThreadMXBean bean = ManagementFactory.getThreadMXBean();

	private long runtime;
	private long routeLength;
	private Node[] route;

	public Main() {
		//generateCSVData();
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
		problemSizeField.addActionListener(a -> problemSize = Integer.parseInt(problemSizeField.getText()));

		JComboBox selectedAlgoBox = new JComboBox<String>(
				new String[] { "Brute Force", "Greedy", "KOpt Random", "KOpt Greedy" });
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

	private void generateCSVData() {
		String path = "C:\\Users\\Finn Kuenkele\\data.csv";
		System.out.println(path);

		TspSolve random = new MonkeySolve();
		TspSolve bruteForce = new BruteForceSolve();
		TspSolve greedy = new GreedySolve();
		TspSolve kopt = new KoptSolve(5);

		CSVFile file = new CSVFile(path);
		file.writeLine(new String[] { "size", "Brute Force", "Random", "Greedy", "KOpt(Random)", "KOpt(Greedy)",
				"Brute Force", "Random", "Greedy", "KOpt(Random)", "KOpt(Greedy)" });

		for (int i = 2; i <= 100; i++) {
			String[] line = new String[11];
			long[] cumulated = new long[10];
			for (int j = 0; j < 1000; j++) {
				generator = new MapGenerator(i);
				Node[] nodes = generator.getNodes();

				line[0] = Integer.toString(i);
				if (i < 9) {
					solve(bruteForce, nodes);
					cumulated[0] += runtime;
					cumulated[5] += routeLength;
				}
				solve(random, nodes);
				long randomRuntime = runtime;
				Node[] randomRoute = route;
				cumulated[1] += runtime;
				cumulated[6] += routeLength;

				solve(greedy, nodes);
				long greedyRuntime = runtime;
				Node[] greedyRoute = route;
				cumulated[2] += runtime;
				cumulated[7] += routeLength;

				solve(kopt, randomRoute);
				cumulated[3] += randomRuntime + runtime;
				cumulated[8] += routeLength;

				solve(kopt, greedyRoute);
				cumulated[4] += greedyRuntime + runtime;
				cumulated[9] += routeLength;
			}
			for (int j = 0; j < 10; j++) {
				line[j + 1] = Long.toString(cumulated[j] / 1000);
			}
			file.writeLine(line);
			System.out.println(i + "done");
		}
		file.save();
	}

	/** Get CPU time in nanoseconds. */
	public long getCpuTime() {
		return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
	}

	private void solve(TspSolve algo, Node[] nodes) {
		long t1 = getCpuTime();
		route = algo.solve(nodes);
		long t2 = getCpuTime();

		runtime = t2 - t1;
		routeLength = (long) Math.floor(Node.routeLength(Arrays.asList(route)));
	}

}
