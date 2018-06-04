import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle {
	protected int radius = 10;
	protected Color color = Color.BLACK;
	protected Point position = new Point(0,0);
	protected String name = "";
	protected int offset = 0;
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(position.x - radius, position.y - radius + offset, 2*radius, 2*radius);
		g.setColor(Color.BLACK);
		g.drawString(name, position.x, position.y);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
