package quad;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import terraintesting.Point;
import virtualworld.WorldObject;

public class QuadGrid extends Application implements Element {

	//scene variables
	private static final int width = 600;
	private static final int height = 600;
	private Point center = new Point(width/2, height/2);
	List<Node> quadNodes;
	List<ElementVisitor> visitorList;
	
	//Quadtree variables
	QuadTree quad = QuadTree.getInstance();
	private List<WorldObject> objects = new ArrayList<>();
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group group = new Group();
		Scene scene = new Scene(group,height,width,Color.WHITE);
		
		populate();
		
		allObjects nodeCollector = new allObjects();
		visitorList.add(nodeCollector);
		
		for (ElementVisitor visitor : visitorList) {
			accept(visitor);
		}
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
		
	}
	
	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, quad.getRootNode());
	}
	
	private void accept(final ElementVisitor visitor, Node n) {
		visitor.visit(n);
	}
	
}
