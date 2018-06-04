package visual;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Trail {
	
	private Color color;
	private Point start;
	private Point end;
	private int offset;
	private String name;
	
	public Trail(Point position, Color color, String name, int offset) {
		start = position;
		end = position;
		this.color = color;
		this.name = name;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
