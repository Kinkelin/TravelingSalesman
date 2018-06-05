package mathematical;

import java.util.List;

public interface TspStepByStep {
	public void setUp(Node[] nodes);
	public boolean hasNext();
	public List<Node[]> next();
}
