package mathematical;

public class KoptSolve extends KoptAlgorithm implements TspSolve {

	public KoptSolve(int iterations) {
		super(iterations);
	}

	@Override
	public Node[] solve(Node[] nodes) {
		route = new MonkeySolve().solve(nodes);
		routeLength = Double.MAX_VALUE;
		for (i = 0; i < iterations; i++) {
			for (j = 1; j < route.length - 1; j++) {
				for (k = j + 1; k < route.length - 1; k++) {
					step(i, j, k);
				}
			}
		}
		return route;
	}
}
