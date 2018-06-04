import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
	private Map<Node, Double> edges = new HashMap<>();
	
	public Map<Node, Double> getEdges() {
		return edges;
	}
	
	public void setEdges(Map<Node, Double> edges) {
		this.edges = edges;
	}
	
	public void addEdge(Node node, Double weight) {
		edges.put(node, weight);
	}
	
	public Double getWeight(Node node) {
		return edges.get(node);
	}
	
	public static double routeLength(List<Node> route) {
		double length = 0;
		for (int i = 0; i < route.size() - 1; i++) {
			length += route.get(i).getWeight(route.get(i+1));
		}
		return length;
	}
}
