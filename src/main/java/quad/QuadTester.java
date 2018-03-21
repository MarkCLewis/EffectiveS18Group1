package quad;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.stage.Stage;

public class QuadTester extends Application {

	private static final int width = 600;
	private static final int height = 600;
	QuadTree quad = new QuadTree();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Group group = new Group();
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		drawQuadTree(gc);
	}
	
	public static void drawQuadTree(GraphicsContext gc) {
		
		
		PixelWriter pw = gc.getPixelWriter();
		
	}
	
}