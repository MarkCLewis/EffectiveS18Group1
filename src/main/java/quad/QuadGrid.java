package quad;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class QuadGrid extends Application {

	private static final int width = 600;
	private static final int height = 600;
	
	private double x1 = 0;
	private double y1 = 0;
	private double x2;
	private double y2;
	
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
