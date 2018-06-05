package mathematical;

public class GreedySolve extends GreedyAlgorithm implements TspSolve {

	@Override
	public Node[] solve(Node[] nodes) {
		Node[] route = new Node[nodes.length];
		route[0] = nodes[0];
		for (int i = 0; i < nodes.length - 1; i++) {
			step(nodes, i, route);
		}
		return route;
	}
}
