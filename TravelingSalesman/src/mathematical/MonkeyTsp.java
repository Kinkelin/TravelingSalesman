package mathematical;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MonkeyTsp implements TspAlgorithm {

	@Override
	public Node[] solve(Node[] nodes) {
		Node[] solved = new Node[nodes.length + 1];
		List<Node> nodelist = new ArrayList(Arrays.asList(nodes));
		nodelist.remove(0);
		Collections.shuffle(nodelist);
		solved[0] = nodes[0];
		for (int i=0; i<nodelist.size(); i++) {
			solved[i+1] = nodelist.get(i);
		}
		solved[nodes.length] = solved[0];
		return solved;
	}

}
