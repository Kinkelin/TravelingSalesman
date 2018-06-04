import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Map;

public class City {
	
	private static final int radius = 12;
	
	private String name;
	private Point position;
	private Node self;
	
	public Object draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(position.x - radius, position.y - radius, 2*radius, 2*radius);
		return null;
	}

}
