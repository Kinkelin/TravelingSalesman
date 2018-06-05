package mathematical;

public class Edge {
	private Node start;
	private Node end;
	public Edge(Node start, Node end) {
		this.start = start;
		this.end = end;
	}
	public Node getStart() {
		return start;
	}
	public void setStart(Node start) {
		this.start = start;
	}
	public Node getEnd() {
		return end;
	}
	public void setEnd(Node end) {
		this.end = end;
	}
	
	public static Edge[] createEdgeArray(Node[] nodes) {
		Edge[] array = new Edge[nodes.length-1];
		for (int i = 0; i < nodes.length-1; i++) {
			array[i] = new Edge(nodes[i], nodes[i+1]);
		}
		return array;
	}
	
	public static Node[] createNodeArray(Edge[] edges) {
		Node[] array = new Node[edges.length + 1];
		for (int i = 0; i < edges.length; i++) {
			array[i] = edges[i].getStart();
		}
		array[edges.length] = edges[edges.length-1].getEnd();
		return array;
	}
}
