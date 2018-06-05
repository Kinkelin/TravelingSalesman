package animation;

import java.util.Arrays;

import mathematical.Node;
import mathematical.TspStepByStep;
import misc.MapGenerator;
import visual.SolvingDisplay;

public class SolvingAnimation extends Animation {

	private SolvingDisplay solvingDisplay;
	private MapGenerator generator;
	private TspStepByStep algorithm;
	private int stepCount = 0;
	
	public SolvingAnimation(SolvingDisplay display, double desiredFps, MapGenerator generator, TspStepByStep algorithm) {
		super(display, desiredFps);
		solvingDisplay = display;
		this.generator = generator;
		this.algorithm = algorithm;
	}

	@Override
	protected void animationStep() {
		Node[] route = algorithm.next();
		solvingDisplay.setRoute(generator.getCities(route));
		solvingDisplay.setLength(Math.round(Node.routeLength(Arrays.asList(route))));
		stepCount += 1;
		solvingDisplay.setSteps(stepCount);
		running = algorithm.hasNext();
	}
}
