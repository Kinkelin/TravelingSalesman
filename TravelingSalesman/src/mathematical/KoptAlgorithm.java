package mathematical;

import java.util.Arrays;
import java.util.List;

public class KoptAlgorithm {
	
	protected Node[] route;
	protected double routeLength;
	protected int i;
	protected int j;
	protected int k;
	protected int iterations;
	protected Node[] testedRoute;
	
	protected KoptAlgorithm(int iterations) {
		this.iterations = iterations;
	}

	protected void step(int i, int j, int k) {
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
	
	private void swap(Node[] newRoute) {
		System.arraycopy(route, 0, newRoute, 0, j);
		for (int l = k; l >= j; l--)
			newRoute[j + k - l] = route[l];
		System.arraycopy(route, k + 1, newRoute, k + 1, route.length - (k + 1));
	}

}
