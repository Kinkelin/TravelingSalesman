import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Trail {
	
	private Color color;
	private Point start;
	private Point end;
	private int offset;
	
	public Trail(Point position, Color color, int offset) {
		start = position;
		end = position;
		this.color = color;
		this.offset = offset;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(start.x, start.y + offset, end.x, end.y + offset);
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
}
