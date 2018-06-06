package mathematical;

import java.util.List;

public class KoptStep extends KoptAlgorithm implements TspStep {
	
	public KoptStep(int iterations) {
		super(iterations);
	}

	@Override
	public void setUp(Node[] nodes) {
		route = nodes;
		routeLength = Double.MAX_VALUE;
		i = 0;
		j = 1;
		k = j + 1;

	}

	@Override
	public boolean hasNext() {
		return i < iterations;
	}

	@Override
	public List<Node[]> next() {
		step(i, j, k);
		k += 1;
		if (k >= route.length - 1) {
			k = j + 1;
			j += 1;
			if (j >= route.length - 1) {
				j = 1;
				i += 1;
			}
		}
		return new RouteList(route, testedRoute);
	}
}
