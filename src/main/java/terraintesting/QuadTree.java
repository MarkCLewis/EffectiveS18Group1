package terraintesting;

import javafx.scene.Camera;

public class QuadTree {
	
	private Node root;
	
	//Node class stores four children, key location, and value
	private class Node {
		Double x; Double y;
		Node north, east, south, west;
		Double size; 
		
		//Node constructor takes coordinates and a value
		private Node (Double x, Double y, Double s) {
			this.x = x;
			this.y = y;
			this.size = s;
		}
		
		public Double getX(Node node) {
			return node.x;
		}
		
		public Double getY(Node node) {
			return node.y;
		}
		
		public Double getSize(Node node) {
			return node.size;
		}
	}
	
	//sets the root equal to an initial value
	public void insert(Double x, Double y, Double s) {
		root = new Node(x, y, s);
	}
	
	//inserts a node in the correct place
	public Node insert(Double x, Double y, Double s, Node n) {
		//if no node is passed in, a new node is created
		if (n == null) return new Node(x, y, s);
		
		//recursively calls insert until the node has been inserted into the correct place
		else if (x < n.x && y < n.y)   n.west  = insert(x, y, s, n.west);
		else if (x < n.x && y >= n.y)  n.north = insert(x, y, s, n.north);
		else if (x >= n.x && y < n.y)  n.east  = insert(x, y, s, n.east);
 		else if (x >= n.x && y >= n.y) n.south = insert(x, y, s, n.south);
		
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
		
	
	//find all in range
	//update camera location
	//
	
	/*
	public class LNode extends Node {
		List<?> pnts;
		public LNode (double x, double y, double s, List<?> pnts) {
			super (x, y, s);
			this.pnts = pnts;
		}
	}
	
	public class INode extends Node {
		List<?> pnts;
		public INode (double x, double y, double s, List<?> pnts) {
			super (x, y, s);
			this.pnts = pnts;
		}
		
		
		
		
	/*
	//checks to see if the key k1 is less than the key k2
	//use !less to check greater than or equal to
	private boolean less(Key k1, Key k2) { 
		return k1.compareTo(k2) <  0; 
	}
    
	//checks to see if the key k1 is equal to the key k2
	private boolean eq (Key k1, Key k2) { 
		return k1.compareTo(k2) == 0; 
    }
	*/
	
	
		/*
		Node (int value) {
		this.value = value;
		this.north = null;
		this.east = null;
		this.south = null;
		this.west = null;
	}
	
*/
}
