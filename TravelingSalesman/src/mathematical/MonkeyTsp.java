package mathematical;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MonkeyTsp implements TspAlgorithm {

	@Override
	public Node[] solve(Node[] nodes) {
		Node[] route = new Node[nodes.length];
		List<Node> nodelist = new ArrayList<Node>(Arrays.asList(nodes));
		nodelist.remove(0);
		Collections.shuffle(nodelist);
		route[0] = nodes[0];
		for (int i=0; i<nodelist.size(); i++) {
			route[i+1] = nodelist.get(i);
		}
		return route;
	}

}
