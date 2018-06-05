package mathematical;

import java.util.List;

public class GreedyStep extends GreedyTsp implements TspStepByStep{

	@Override
	public void setUp(Node[] nodes) {
		this.nodes = nodes;
		progress = 0;
		route = new Node[nodes.length];
		route[0] = nodes[0];

	}

	@Override
	public boolean hasNext() {
		return progress < nodes.length-1;
	}

	@Override
	public List<Node[]> next() {
		if (!hasNext()) {
			return null;
		}
		step(nodes, progress, route);
		progress += 1;
		
		return new RouteList(route);
	}

}
