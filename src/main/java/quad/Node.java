package quad;

import java.util.ArrayList;
import java.util.List;

import virtualworld.WorldObject;

public class Node {
	double x; double y;
	List<Node> children = new ArrayList<Node>();
	List<WorldObject> contents = new ArrayList<WorldObject>();
	double size;

	//Node constructor takes coordinates and a value
	public Node (double x, double y, double s) {
		this.x = x;
		this.y = y;
		this.size = s;
	}

	public double getX(Node node) {
		return node.x;
	}

	public double getY(Node node) {
		return node.y;
	}

	public static double getSize(Node node) {
		return node.size;
	}
			
	/**
	 * Gets the number of the child from a given coordinate
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return child the int value of the child node
	 */
	public int getChild (Double x, Double y) {
		int child = 0;
		if (x > this.x) child |= 1;
		if (y > this.y) child |= 2;
		return child;
	}
}
