package quad;

import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadTreeTester {
	
	
	public static QuadTree addToTree() {
		QuadTree quad = new QuadTree();
		
		WorldObject testOb1 = new ExampleObject(0, 0, 5);
		WorldObject testOb2 = new ExampleObject(0, 0, 5);
		WorldObject testOb3 = new ExampleObject(0, 0, 5);
		WorldObject testOb4 = new ExampleObject(0, 0, 5);
		WorldObject testOb5 = new ExampleObject(0, 0, 5);
		WorldObject testOb6 = new ExampleObject(0, 0, 5);
		WorldObject testOb7 = new ExampleObject(0, 0, 5);
	
		quad.insert(testOb1);
		quad.insert(testOb2);
		quad.insert(testOb3);
		quad.insert(testOb4);
		quad.insert(testOb5);
		quad.insert(testOb6);
		quad.insert(testOb7);
	
		return quad;
	}
	
	
		PrintVisitor printTest = new PrintVisitor();
	
	public static void main(String[] args) {
		
	}
}
