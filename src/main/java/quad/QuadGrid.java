package quad;

import terraintesting.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import virtualworld.WorldObject;

public class QuadGrid extends Application {

	//scene variables
	private static final int width = 600;
	private static final int height = 600;
	private Point center = new Point(width/2, height/2);
	
	//Quadtree variables
	QuadTree quad = QuadTree.getInstance();
	private List<WorldObject> objects = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println("app started");
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group group = new Group();
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Sphere sphere1 = new Sphere(100);
		Material mat1 = new PhongMaterial(Color.FORESTGREEN);
		sphere1.setMaterial(mat1);
		sphere1.setTranslateZ(200);
		group.getChildren().add(sphere1);
		primaryStage.setScene(new Scene(group));
		primaryStage.show();
	}
	
	public static void drawQuadTree(GraphicsContext gc) {
		PixelWriter pw = gc.getPixelWriter();
		//for(int x=0; x<width; x++)
		//	for(int y=0; y<height; y++) 
		//		pw.setColor(x,y,heatMap[x][y]);
	}
	
}
