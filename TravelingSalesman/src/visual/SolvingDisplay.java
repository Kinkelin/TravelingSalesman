package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SolvingDisplay extends JPanel {

	private List<City> cities;
	private List<City> route = new ArrayList<>();
	private List<City> testedRoute = new ArrayList<>();
	private JLabel label = new JLabel();

	private int steps;
	private double length;
	
	public SolvingDisplay(List<City> cities) {
		this.cities = cities;
		add(label);
	}

	private void updateLabel() {
		label.setText("Steps: " + steps + " Route length: " + length);
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
		updateLabel();
	}
	
	public void setLength(double length) {
		this.length = length;
		updateLabel();
	}
	
	public void setRoute(List<City> route) {
		this.route = route;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawRoute(g, testedRoute, Color.RED);
		drawRoute(g, route, Color.BLACK);
		cities.forEach(c -> c.draw(g));
	}

	private void drawLine(Graphics g, City c, City c2) {
		if (c != null && c2 != null) {
			Point p1 = c.getPosition();
			Point p2 = c2.getPosition();
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
	
	private void drawRoute(Graphics g, List<City> route, Color c) {
		if (route.size() > 1) {
		g.setColor(c);
		for (int i = 0; i < route.size() - 1; i++) {
			drawLine(g, route.get(i), route.get(i + 1));
		}
		drawLine(g, route.get(route.size() - 1), route.get(0));
		}
	}

	public void setTestedRoute(List<City> testedRoute) {
		this.testedRoute = testedRoute;
	}
}
