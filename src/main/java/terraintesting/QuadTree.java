package terraintesting;

public class QuadTree<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	//Node class stores four children, key location, and value
	private class Node {
		Key cx, cy;
		Node north, east, south, west;
		Value value; 
		
		//Node constructor takes coordinates and a value
		private Node (Key x, Key y, Value v) {
			cx = x;
			cy = y;
			value = v;
		}
		
		public Key getX(Node node) {
			return node.cx;
		}
		
		public Key getY(Node node) {
			return node.cy;
		}
		
		public Value getVal(Node node) {
			return node.value;
		}
	}
	
	//checks to see if the key k1 is less than the key k2
	//use !less to check greater than or equal to
	private boolean less(Key k1, Key k2) { 
		return k1.compareTo(k2) <  0; 
	}
    
	//checks to see if the key k1 is equal to the key k2
	private boolean eq (Key k1, Key k2) { 
		return k1.compareTo(k2) == 0; 
    }

	//sets the root equal to an initial value
	public void insert(Key x, Key y, Value v) {
		root = new Node(x, y, v);
	}
	
	//inserts a node in the correct place
	public Node insert(Key x, Key y, Value v, Node n) {
		//if no node is passed in, a new node is created
		if (n == null) return new Node(x, y, v);
		
		//recursively calls insert until the node has been inserted into the correct place
		else if (less(x, n.cx) && less(y, n.cy))   n.west  = insert(x, y, v, n.west);
		else if (less(x, n.cx) && !less(y, n.cy))  n.north = insert(x, y, v, n.north);
		else if (!less(x, n.cx) && less(y, n.cy))  n.east  = insert(x, y, v, n.east);
 		else if (!less(x, n.cx) && !less(y, n.cy)) n.south = insert(x, y, v, n.south);
		
		return n;
	}
	
	public static void findAllInRange() {
		
	}
	
	public static void updateCameraLocation() {
		
	}
	
	public static void recursiveFunc() {
		//(cameraX - radius) < (xMax + size/2) && (cameraX + radius) > (xMin - size/2)
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
		
		
		
		
		
	}
	
	public static void visitAllNeighbors(double distance) {
		
	}
	
	public static void visitNeighbors(double distance) {
		
	}
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
