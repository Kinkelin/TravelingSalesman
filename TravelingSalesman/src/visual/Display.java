package visual;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JPanel {

	private List<City> cities = new LinkedList<>();
	private List<Trail> trails = new LinkedList<>();
	private List<VisitorMark> marks = new LinkedList<>();
	private List<Salesman> salesmen = new LinkedList<>();
	
	private JLabel fps = new JLabel();
	
	public Display() {
		add(fps);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		trails.forEach(t -> t.draw(g));
		cities.forEach(c -> c.draw(g));
		marks.forEach(m -> m.draw(g));
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
		this.fps.setText(Integer.toString(fps));
	}

}
