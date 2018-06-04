package mathematical;

import java.util.Arrays;
import java.util.List;

public class BruteForceTsp implements TspAlgorithm {

	@Override
	public Node[] solve(Node[] nodes) {
		PermutationIterator<Node> iter = new PermutationIterator<>(Arrays.asList(nodes));
		List<Node> bestRoute = null;
		double bestLength = Double.MAX_VALUE;
		while (iter.hasNext()) {
			List<Node> route = iter.next();
			if (route.get(0) == nodes[0]) {
				route.add(route.get(0));
				double length = Node.routeLength(route);
				if (length < bestLength) {
					bestLength = length;
					bestRoute = route;
				}
			}
		}
		return bestRoute.toArray(new Node[bestRoute.size()]);
	}
}
