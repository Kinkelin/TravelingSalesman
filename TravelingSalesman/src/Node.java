import java.util.HashMap;
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
}
