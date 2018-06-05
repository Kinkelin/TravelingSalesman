package mathematical;

import java.util.Arrays;

public class BruteForceSolve extends BruteForceAlgorithm implements TspSolve {
	@Override
	public Node[] solve(Node[] nodes) {
		PermutationIterator<Node> iter = new PermutationIterator<>(Arrays.asList(nodes));
		bestRoute = null;
		length = Double.MAX_VALUE;
		while (iter.hasNext()) {
			step(iter, nodes);
		}
		return bestRoute.toArray(new Node[bestRoute.size()]);
	}
}
