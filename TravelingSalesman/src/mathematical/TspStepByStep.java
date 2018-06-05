package mathematical;

public interface TspStepByStep {
	public void setUp(Node[] nodes);
	public boolean hasNext();
	public Node[] next();
}
