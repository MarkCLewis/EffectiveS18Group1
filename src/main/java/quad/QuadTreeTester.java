package quad;

import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadTreeTester {
	
	
	public static QuadTree addToTree() {
		QuadTree quad = new QuadTree();
		
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j <= 10; i++) {
				WorldObject testObject = new ExampleObject(i*10, j*10, 5);
				quad.insert(testObject);
			}
		}
		
	
		return quad;
	}
	
	public static void main(String[] args) {
		QuadTree quad = addToTree();
		PrintVisitor printTest = new PrintVisitor();
		quad.accept(printTest);
	}
}