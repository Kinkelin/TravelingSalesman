import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MonkeyTsp implements TspAlgorithm {

	@Override
	public Node[] solve(Node[] nodes) {
		Node[] solved = new Node[nodes.length + 1];
		List<Node> nodelist = Arrays.asList(nodes);
		Collections.shuffle(nodelist);
		solved = nodelist.toArray(solved);
		solved[nodes.length] = solved[0];
		return solved;
	}

}
