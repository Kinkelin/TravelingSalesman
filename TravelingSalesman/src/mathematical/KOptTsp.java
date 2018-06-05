package mathematical;

import java.util.Arrays;
import java.util.List;

public class KOptTsp implements TspStepByStep, TspAlgorithm {
	
	private Node[] route;
	private double routeLength;
	private int i;
	private int j;
	private int k;
	private int iterations;
	private Node[] testedRoute;
	
	public KOptTsp(int iterations) {
		this.iterations = iterations;
	}
	
	@Override
	public void setUp(Node[] nodes) {
		route = new MonkeyTsp().solve(nodes);
		routeLength = Double.MAX_VALUE;
		i = 0;
		j = 1;
		k = j + 1;

	}

	@Override
	public boolean hasNext() {
		return i < iterations;
	}

	@Override
	public List<Node[]> next() {
		step(i, j, k);
		k+=1;
		if (k >= route.length - 1) {
			k = j + 1;
			j+=1;
			if (j >= route.length - 1) {
				j = 1;
				i+=1;
			}
		}
		return new RouteList(route, testedRoute);
	}

	private void step(int i, int j, int k) {
		Node[] newRoute = new Node[route.length];
		swap(newRoute);
		double newRouteLength = Node.routeLength(Arrays.asList(newRoute));
		testedRoute = newRoute;
		if (newRouteLength < routeLength) {
			route = newRoute;
			routeLength = newRouteLength;
			i = 0;
		}
	}

	@Override
	public Node[] solve(Node[] nodes) {
		route = new MonkeyTsp().solve(nodes);
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
	
	private void swap(Node[] newRoute) {
		System.arraycopy(route, 0, newRoute, 0, j);
		for (int l = k; l >= j; l--)
			newRoute[j + k - l] = route[l];
		System.arraycopy(route, k + 1, newRoute, k + 1, route.length - (k + 1));
	}

}
