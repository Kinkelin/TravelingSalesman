package visual;
import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Salesman extends Circle {

	private List<City> route;
	private int routePosition = 0;
	private double currentDistance = 0;
	private Trail currentTrail;
	private Display display;
	private int offset;
	
	public Salesman(List<City> route, Color color, String name, Display display, int offset) {
		this.offset = offset;
		this.route = route;
		radius = 9;
		position = route.get(0).getPosition();
		this.color = color;
		this.name = name;
		currentTrail = new Trail(position, color, offset);
		this.display = display;
		display.getTrails().add(currentTrail);
		display.getMarks().add(new VisitorMark(position, color, offset));
		
	}
	
	private boolean isBusy() {
		return routePosition < route.size() - 1;
	}
	
	private double currentToNextCityDistance() {
		return route.get(routePosition).getSelf().getWeight(route.get(routePosition+1).getSelf());
	}
	
	public boolean move(double distance) {
		if (isBusy()) {
			currentDistance += distance;
			double currentToNextCityDistance = currentToNextCityDistance();
			while (isBusy() && currentDistance > currentToNextCityDistance) {
				currentDistance -= currentToNextCityDistance;
				routePosition += 1;
				currentTrail.setEnd(route.get(routePosition).getPosition());
				currentTrail = new Trail(currentTrail.getEnd(), color, offset);
				display.getTrails().add(currentTrail);
				display.getMarks().add(new VisitorMark(currentTrail.getEnd(), color, offset));
				if (isBusy()) currentToNextCityDistance = currentToNextCityDistance();
			}
			if (isBusy()) {
				double trailProgress = currentDistance / currentToNextCityDistance;
				Point start = currentTrail.getStart();
				Point end = route.get(routePosition + 1).getPosition();
				double vectorX = end.x - start.x;
				double vectorY = end.y - start.y;
				
				currentTrail.setEnd(new Point((int) (start.getX() + (trailProgress * vectorX)),(int) (start.getY() + (trailProgress * vectorY)))); 
			}
			position = currentTrail.getEnd();
		}
		return isBusy();
		
	}

	public List<City> getRoute() {
		return route;
	}

	public void setRoute(List<City> route) {
		this.route = route;
	}

	public int getRoutePosition() {
		return routePosition;
	}

	public void setRoutePosition(int routePosition) {
		this.routePosition = routePosition;
	}

	public double getCurrentDistance() {
		return currentDistance;
	}

	public void setCurrentDistance(double currentDistance) {
		this.currentDistance = currentDistance;
	}
}
