import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Trail {
	
	private Color color;
	private Point start;
	private Point end;
	
	public Trail(Point position, Color color) {
		start = position;
		end = position;
		this.color = color;
	}
	
	public void newPosition(Point newPosition) {
		end = newPosition;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(start.x, start.y, end.x, end.y);
	}
}
