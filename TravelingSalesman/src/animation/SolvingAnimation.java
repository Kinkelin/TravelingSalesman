package animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mathematical.Node;
import mathematical.TspStep;
import misc.MapGenerator;
import visual.City;
import visual.SolvingDisplay;

public class SolvingAnimation extends Animation {

	private SolvingDisplay solvingDisplay;
	private MapGenerator generator;
	private TspStep algorithm;
	private int stepCount = 0;
	
	public SolvingAnimation(SolvingDisplay display, double desiredFps, MapGenerator generator, TspStep algorithm) {
		super(display, desiredFps);
		solvingDisplay = display;
		this.generator = generator;
		this.algorithm = algorithm;
	}

	@Override
	protected void animationStep() {
		List<Node[]> routes = algorithm.next();
		solvingDisplay.setRoute(generator.getCities(routes.get(0)));
		if (routes.size() == 2) solvingDisplay.setTestedRoute(generator.getCities(routes.get(1)));
		solvingDisplay.setLength(Math.round(Node.routeLength(Arrays.asList(routes.get(0)))));
		stepCount += 1;
		solvingDisplay.setSteps(stepCount);
		running = algorithm.hasNext();
		if (!running) solvingDisplay.setTestedRoute(new ArrayList<City>());
	}
}
