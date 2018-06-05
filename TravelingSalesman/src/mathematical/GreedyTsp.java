package mathematical;

import java.util.Arrays;
import java.util.Map;

public class GreedyTsp {

	protected Node[] nodes;
	protected int progress;
	protected Node[] route;

	protected void step(Node[] nodes, int i, Node[] route) {
		Node nearest = null;
		double distance = Double.MAX_VALUE;
		for (Map.Entry<Node, Double> entry : route[i].getEdges().entrySet()) {
			if (!Arrays.asList(route).contains(entry.getKey()) && entry.getValue() < distance) {
				nearest = entry.getKey();
				distance = entry.getValue();
			}
		}
		route[i + 1] = nearest;
	}
}
