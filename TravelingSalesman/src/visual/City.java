package visual;
import java.awt.Color;
import java.awt.Point;

import mathematical.Node;

public class City extends Circle {
	
	private Node self;

	public City(Point position, String name, Node self) {
		this.position = position;
		this.name = name;
		this.self = self;
		radius = 12;
		color = Color.GRAY;
	}
	
	public Node getSelf() {
		return self;
	}
}
