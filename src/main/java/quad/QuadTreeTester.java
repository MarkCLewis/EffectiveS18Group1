package quad;

import quad.QuadTree.Node;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadTreeTester {
	
	
	public static QuadTree addToTree() {
		QuadTree quad = new QuadTree();
		
		/**
		for (int a = 0; a <= 10; a++) {
			for (int b = 0; b <= 10; b++) {
				for (int c = 0; c <= 10; c++) {
					double x = a*10;
					double z = b*10;
					double y = c*10;
					double size = x*z;
					WorldObject testObject = new ExampleObject(x, z, y, size);
					quad.insert(testObject);
				}
			}
		}
		**/
		
		
		Node node = new Node (1, 1, 5);
		WorldObject testObject1 = new ExampleObject(1.0, 1.0, 5.0);
		WorldObject testObject2 = new ExampleObject(5, 5, 5);
		WorldObject testObject3 = new ExampleObject(5, 5, 5);
		quad.insert(testObject1);
		quad.insert(testObject2);
		quad.insert(testObject3);
		return quad;
	}
	
	public static void main(String[] args) {
		QuadTree quad = addToTree();
		//PrintVisitor printTest = new PrintVisitor();
		//quad.accept(printTest);
		quad.print();
	}
}