package misc;
import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mathematical.Node;
import visual.City;

public class MapGenerator {

	private static int MAP_SIZE = 800;
	private static int PROBLEM_SIZE = 10;

	private City[] cities;
	private Node[] nodes;
	private Map<Node, City> map;

	public MapGenerator() {
		Random rnd = new Random();
		cities = new City[PROBLEM_SIZE];
		nodes = new Node[PROBLEM_SIZE];
		map = new HashMap<>();
		for (int i = 0; i < PROBLEM_SIZE; i++) {
			Point position = new Point(rnd.nextInt(MAP_SIZE), rnd.nextInt(MAP_SIZE));
			nodes[i] = new Node();
			cities[i] = new City(position, String.valueOf(""/*"Stadt " + i*/), nodes[i]);
			map.put(nodes[i], cities[i]);
		}
		for (int i = 0; i < PROBLEM_SIZE; i++) {
			for (int j = 0; j < PROBLEM_SIZE; j++) if (i != j) {
				 nodes[i].addEdge(nodes[j], cities[i].getPosition().distance(cities[j].getPosition()));
			}
		}
	}

	public List<City> getCities() {
		return Arrays.asList(cities);
	}

	public Node[] getNodes() {
		return nodes;
	}
	
	public City getCity(Node node) {
		return map.get(node);
	}
	
	public List<City> getCities(Node[] nodes) {
		List<City> cities = new LinkedList<>();
		for (int i = 0; i < nodes.length; i++) {
			cities.add(getCity(nodes[i]));
		}
		return cities;
	}
}
