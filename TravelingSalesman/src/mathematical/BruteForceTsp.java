package mathematical;

import java.util.Arrays;
import java.util.List;

public class BruteForceTsp implements TspAlgorithm, TspStepByStep {

	private PermutationIterator<Node> iter;
	private Node[] nodes;
	private List<Node> bestRoute;
	private double length = Double.MAX_VALUE;
	private boolean stepResult = true;
	private Node[] testedRoute;
	
	private boolean step(PermutationIterator<Node> iter, Node[] nodes) {
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
