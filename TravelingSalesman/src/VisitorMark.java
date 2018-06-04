import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class VisitorMark {
	
	private static final int radius = 3;
	
	private Color color;
	private Point position;

	public VisitorMark(Point position, Color color) {
		this.position = position;
		this.color = color;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(position.x - radius, position.y - radius, 2*radius, 2*radius);
	}
}
