package quad;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Camera;
import virtualworld.WorldObject;

public class QuadTree implements Element {

	private Node root;

	//Node class stores four children, key location, and value
	static class Node {
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
	public void insert(WorldObject item) {
		double x = item.getX();
		double y = item.getY();
		double s = item.getSize();
		root = new Node(x, y, s);
	}

	//inserts a node in the correct place
	private Node insert(WorldObject item, Node n) {
		//if no node is passed in, a new node is created
		double x = item.getX();
		double y = item.getY();
		double s = item.getSize();
		if (n == null) return new Node(x, y, s);
		//recursively calls insert until the node has been inserted into the correct place
		else {
			//finds the child
			int child = n.getChild(x, y);
			//sets the child to the recursive call
			n.children.set(child, insert(item, n));
		}
		return n;
	}

	public static void updateCameraLocation(Camera camera) {

	}

	public static void recursiveFunc(Camera camera) {
		//(cameraX - radius) < (xMax + size/2) && (cameraX + radius) > (xMin - size/2)
	}

	public static void visitAllNeighbors(double distance) {
		// TODO Auto-generated method stub
	}

	public static void visitNeighbors(double distance) {

	}

	private void accept(final ElementVisitor visitor, Node n) {
		if (visitor.cares(n)) {
			visitor.visit(n);
		}
	}

	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, root);
	}

	//purely for testing purposes, hardcoded in
	public void print(Node n, String tab) {
		
	}
}
