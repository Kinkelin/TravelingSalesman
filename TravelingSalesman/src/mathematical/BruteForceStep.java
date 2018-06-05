package mathematical;

import java.util.Arrays;
import java.util.List;

public class BruteForceStep extends BruteForceAlgorithm implements TspStep {
	@Override
	public void setUp(Node[] nodes) {
		this.nodes = nodes;
		iter = new PermutationIterator<>(Arrays.asList(nodes));
		bestRoute = null;
		length = Double.MAX_VALUE;
	}

	@Override
	public boolean hasNext() {
		return stepResult && iter.hasNext();
	}

	@Override
	public List<Node[]> next() {
		stepResult = step(iter, nodes);
		return new RouteList(bestRoute.toArray(new Node[bestRoute.size()]), testedRoute);
	}
}
