package mathematical;

import java.util.LinkedList;

public class RouteList extends LinkedList<Node[]> {
	
	public RouteList(Node[] route) {
		add(route);
	}
	
	public RouteList(Node[] route, Node[] testedRoute) {
		this(route);
		add(testedRoute);
	}
}
