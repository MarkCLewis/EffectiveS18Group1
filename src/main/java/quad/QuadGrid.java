package quad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadGrid extends Application implements Element {

	//Scene variables
	private static final int width = 800;
	private static final int height = 800;
	
	//Quadtree variables
	static QuadTree quad = QuadTree.getInstance();
	static List<Node> quadNodes = new ArrayList<Node>();
	static List<ExampleObject> quadObjects = new ArrayList<ExampleObject>();
	List<ElementVisitor> visitorList;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("QuadTree Test");
		Group root = new Group();
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		populate();
		drawShapes(gc);
		root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
	}	
     
	private void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.BLANCHEDALMOND);
		
		//Nodes
		gc.setLineWidth(1);
		gc.setStroke(Color.BLACK);
		for (Node n : quadNodes) {
			double x = n.x - n.size;
			double z = n.z - n.size;
			gc.strokeRect(x, z, n.size, n.size);
			//System.out.println("x: " + x + " z: " + z);
		}
		
		//WorldObjects
		gc.setStroke(Color.RED);
		for(ExampleObject obj : quadObjects) {
			double radius = obj.getRadius();
			double x = obj.getXLoc();
			double z = obj.getZLoc();
			gc.strokeOval(x, z, radius, radius);
		}
	}

	public static void populate() {
		Random rand = new Random();
		ExampleObject firstOb = new ExampleObject(400, 400, 400);
		quad.insert(firstOb, null);
		for (int i = 0; i < 100; i++) {
			ExampleObject testObject = new ExampleObject(rand.nextDouble() + rand.nextInt(299), rand.nextDouble() + rand.nextInt(299), rand.nextInt(100));
			quad.insert(testObject, quad.getRootNode());
		}
		AllObjects nodeCollector = new AllObjects();
		nodeCollector.visit(quad.getRootNode());
		
		for (Node nodes : nodeCollector.allNodes) {
			quadNodes.add(nodes);
		}
		
		for (WorldObject items : nodeCollector.allWorldObjects) {
			quadObjects.add((ExampleObject) items);
		}
		
		System.out.println(quadNodes.size());
	}
	
	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, quad.getRootNode());
	}
	
	private void accept(final ElementVisitor visitor, Node n) {
		visitor.visit(n);
	}
	
}
