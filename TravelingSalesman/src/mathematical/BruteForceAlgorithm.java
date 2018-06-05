package mathematical;

import java.util.List;

public class BruteForceAlgorithm {

	protected PermutationIterator<Node> iter;
	protected Node[] nodes;
	protected List<Node> bestRoute;
	protected double length = Double.MAX_VALUE;
	protected boolean stepResult = true;
	protected Node[] testedRoute;
	
	protected boolean step(PermutationIterator<Node> iter, Node[] nodes) {
		List<Node> route = iter.next();
		testedRoute = route.toArray(new Node[route.size()]);
		if (route.get(0) == nodes[0]) {
			double length = Node.routeLength(route);
			if (length < this.length) {
				this.length = length;
				bestRoute = route;
			}
			return true;
		}
		return false;
	}
}
