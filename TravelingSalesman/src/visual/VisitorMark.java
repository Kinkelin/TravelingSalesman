package visual;
import java.awt.Color;
import java.awt.Point;

public class VisitorMark extends Circle {

	public VisitorMark(Point position, Color color, int offset) {
		this.position = position;
		this.color = color;
		this.offset = offset;
		radius = 3;
	}
}
