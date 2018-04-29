package quad;

import java.util.ArrayList;
import java.util.List;

import virtualworld.WorldObject;

public class Node {
	//center and size of the Node
	double x; double z; double size;
	
	int depth;
	
	//Lists of children and items within
	List<Node> children = new ArrayList<Node>();
	List<WorldObject> contents = new ArrayList<WorldObject>();	

	//Node constructor takes coordinates and a value
	public Node (double x, double z, double s, int d) {
		this.x = x;
		this.z = z;
		this.size = s;
		this.depth = d;
	}

	public double getX(Node node) {
		return node.x;
	}

	public double getz(Node node) {
		return node.z;
	}

	public static double getSize(Node node) {
		return node.size;
	}
			
	/**
	 * Gets the number of the child from a given coordinate
	 * 
	 * @param x x-coordinate
	 * @param z z-coordinate
	 * @return child the int value of the child node
	 */
	public int getChild (Double x, Double z) {
		int child = 0;
		if (x > this.x) child |= 1;
		if (z > this.z) child |= 2;
		return child;
	}
}
