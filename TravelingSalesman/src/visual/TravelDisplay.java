package visual;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TravelDisplay extends JPanel {

	private List<City> cities = new LinkedList<>();
	private List<Trail> trails = new LinkedList<>();
	private List<VisitorMark> marks = new LinkedList<>();
	private List<Salesman> salesmen = new LinkedList<>();
	
	private JLabel fps = new JLabel();
	private JLabel distanceTravelled = new JLabel();
	
	public TravelDisplay() {
		add(fps);
		add(distanceTravelled);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<Trail> stableTrails = new ArrayList<Trail>(trails);
		Map<String, Double> distance = new HashMap<>();
		for (Trail trail : stableTrails) {
			String s = trail.getName();
			if (!distance.containsKey(s)) distance.put(s, 0.0);
			distance.put(s, (double) Math.round(distance.get(s) + trail.getStart().distance(trail.getEnd())));
		}
		setDistanceTravelled(distance);
		stableTrails.forEach(t -> t.draw(g));
		cities.forEach(c -> c.draw(g));
		new ArrayList<VisitorMark>(marks).forEach(m -> m.draw(g));
		salesmen.forEach(s -> s.draw(g));
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Trail> getTrails() {
		return trails;
	}

	public void setTrails(List<Trail> trails) {
		this.trails = trails;
	}

	public List<VisitorMark> getMarks() {
		return marks;
	}

	public void setMarks(List<VisitorMark> marks) {
		this.marks = marks;
	}

	public List<Salesman> getSalesmen() {
		return salesmen;
	}

	public void setSalesmen(List<Salesman> salesmen) {
		this.salesmen = salesmen;
	}
	
	public void setFps(int fps) {
		this.fps.setText("FPS = " + Integer.toString(fps));
	}
	
	public void setDistanceTravelled(Map<String, Double> distance) {
		this.fps.setText("Distance travelled " + distance);
	}

}
