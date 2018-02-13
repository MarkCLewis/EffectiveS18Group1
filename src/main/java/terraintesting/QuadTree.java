package terraintesting;

public class QuadTree {

	private Node root;
	
	private class Node {
		int value;
		Node north;
		Node east;
		Node south;
		Node west;
		
		Node (int value) {
			this.value = value;
			this.north = null;
			this.east = null;
			this.south = null;
			this.west = null;
		}
	}
}
