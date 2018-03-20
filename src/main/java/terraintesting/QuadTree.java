package terraintesting;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Camera;
import virtualworld.WorldObject;

public class QuadTree {
	
	private Node root;
	
	//Node class stores four children, key location, and value
	static class Node {
		double x; double y;
		List<Node> children = new ArrayList<Node>();
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
		
		public double getSize(Node node) {
			return node.size;
		}
		public int getChild (Double x, Double y) {
			int child = 0;
			if (x > this.x) child |= 1;
			if (y > this.y) child |= 2;
			return child;
		}
	}
	
	//sets the root equal to an initial value
	public void insert(double x, double y, double s) {
		root = new Node(x, y, s);
	}
	
	//inserts a node in the correct place
	public Node insert(double x, double y, double s, Node n) {
		//if no node is passed in, a new node is created
		if (n == null) return new Node(x, y, s);
		//recursively calls insert until the node has been inserted into the correct place
		else {
			//finds the child
			int child = n.getChild(x, y);
			//sets the child to the recursive call
			n.children.set(child, insert(x, y, s, n.children.get(child)));
		}
		return n;
	}
	
	public static void findAllInRange() {
		
	}
	
	public static void updateCameraLocation(Camera camera) {
		
	}
	
	public static void recursiveFunc(Camera camera) {
		//(cameraX - radius) < (xMax + size/2) && (cameraX + radius) > (xMin - size/2)
	}
	
	public static void visitAllNeighbors(double distance) {
		
	}
	
	public static void visitNeighbors(double distance) {
		
	}
}
