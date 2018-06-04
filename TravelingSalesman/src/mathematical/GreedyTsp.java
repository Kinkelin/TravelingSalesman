package mathematical;

import java.util.Arrays;
import java.util.Map;

public class GreedyTsp implements TspAlgorithm {

	@Override
	public Node[] solve(Node[] nodes) {
		Node[] solved = new Node[nodes.length + 1];
		solved[0] = nodes[0];
		for (int i = 0; i < nodes.length - 1; i++) {
			Node nearest = null;
			double distance = Double.MAX_VALUE;
			for (Map.Entry<Node, Double> entry : solved[i].getEdges().entrySet()) {
				if (!Arrays.asList(solved).contains(entry.getKey()) && entry.getValue() < distance) {
					nearest = entry.getKey();
					distance = entry.getValue();
				}
			}
			solved[i + 1] = nearest;
		}
		solved[nodes.length] = solved[0];
		return solved;
	}

}
