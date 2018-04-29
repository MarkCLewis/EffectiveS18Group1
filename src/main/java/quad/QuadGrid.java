package quad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadGrid extends Application implements Element {

	//scene variables
	private static final int width = 600;
	private static final int height = 600;
	private double centerX = width/2;
	private double centerZ = height/2;
	static List<Node> quadNodes = new ArrayList<Node>();
	List<ElementVisitor> visitorList;
	
	//Quadtree variables
	static QuadTree quad = QuadTree.getInstance();
	private List<WorldObject> objects = new ArrayList<>();
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group group = new Group();
		Scene scene = new Scene(group,height,width,Color.WHITE);
		
		populate();
		
		//allObjects nodeCollector = new allObjects();
		//accept(nodeCollector);
		//for (Node nodes : nodeCollector.allNodes) {
		///	quadNodes.add(nodes);
		//}
		
		allObjects nodeCollector = new allObjects();
		nodeCollector.visit(quad.getRootNode());
		
		for (Node nodes : nodeCollector.allNodes) {
			quadNodes.add(nodes);
		}
		
		System.out.println(quadNodes.size());
		
		for (Node n : quadNodes) {
			Rectangle rect = new Rectangle();
			rect.setX(n.x - n.size/2);
			rect.setY(n.z - n.size/2);
			rect.setWidth(n.size);
			rect.setHeight(n.size);
			rect.setFill(Color.TRANSPARENT);
			rect.setStroke(Color.BLACK);
			rect.setStrokeWidth(.5);
			group.getChildren().add(rect);
		}
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void populate() {
		Random rand = new Random();
		for (int i = 0; i <100; i++) {
			//Point p = new Point (rand.nextDouble() + rand.nextInt(599), rand.nextDouble() + rand.nextInt(599));
			//ExampleObject testObject = new ExampleObject(p.getX(), p.getZ(), 0);
			//quad.insert(testObject, quad.getRootNode());
		}
		ExampleObject testObject = new ExampleObject(500, 500, 0);
		ExampleObject testObject1 = new ExampleObject(200, 200, 0);
		
		quad.insert(testObject);
		quad.insert(testObject1, quad.getRootNode());
	}
	
	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, quad.getRootNode());
	}
	
	private void accept(final ElementVisitor visitor, Node n) {
		visitor.visit(n);
	}
	
}
