package terraintesting;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * This program displays terrain (represented by a 2D int array that maps x,y values to an elevation) as a 2D heat map 
 * We can potentially use it to test various strategies for making terrain
 * 
 * Modify the buildElevation() method to test different terrain-building strategies
 * 
 * @author Silas
 */

public class HeatMapTerrainDemo extends Application {
	private static final int width = 600;
	private static final int height = 600;
	private static final int maxElevation = 100;
	private static int[][] elevation = new int[width][height];
	private static Color[][] heatMap = new Color[width][height];
	
	@Override public void start(Stage primaryStage) {
		Group group = new Group();
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		buildElevation(); buildHeatMap();
		drawHeatMap(gc);
		group.getChildren().add(canvas);
		primaryStage.setScene(new Scene(group));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public static void drawHeatMap(GraphicsContext gc) {
		PixelWriter pw = gc.getPixelWriter();
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++) 
				pw.setColor(x,y,heatMap[x][y]);
	}
	
	public static void buildElevation() {
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				elevation[x][y]= simpleHillAndValley(x,y); //Modify this method call to try out different strategies
	}
	
	public static int simpleHillAndValley(int x, int y) {
		return (int) (maxElevation/4*Math.sin((double)x/maxElevation)+maxElevation/4) + 
		(int) (maxElevation/4*Math.sin((double)y/maxElevation)+maxElevation/4);
	}
	
	//TODO
	public static int fractalTerrain(int x, int y) {
		return 0;
	}
	
	public static void buildHeatMap() {
		for(int x=0; x<heatMap.length; x++)
			for(int y=0; y<heatMap[0].length; y++) {
				heatMap[x][y] = intToColor(elevation[x][y], maxElevation);
			}
	}
	
	//converts an int to a color representing its intensity given a specified max
	//min is assumed to be 0
	//assumes 0<=i<=max
	//There's a lot of conversions b/t ints and doubles to avoid integer division (it doesn't work with all ints)
	public static Color intToColor(int i, int max) {
		double red; double green; double blue;
		double iDouble = (double)i;
		double maxDouble = (double)max;
		if(iDouble <= maxDouble/4) {
			red = 0;
			green = iDouble/(maxDouble/4)*255; // 0 when i = 0, 255 when i = max/4
			blue = 255;
		} 
		else if (iDouble <= maxDouble/2) {
			red = 0;
			green = 255;
			blue = (maxDouble/4-(iDouble-maxDouble/4))/(maxDouble/4)*255; //0 when i = max/2, 255 when i=~max/4
		}
		else if(iDouble <= 3*maxDouble/4) {
			red = (iDouble-maxDouble/2)/(maxDouble/4)*255; //0 when i=~max/2, 255 when i=3*max/4
			green = 255;
			blue = 0;
		}
		else {
			red = 255;
			green = (maxDouble/4-(iDouble-3*maxDouble/4))/(maxDouble/4)*255; //0 when i=max, 255 when i=~3*max/4
			blue = 0;
		}
		return Color.rgb((int)red, (int)green, (int)blue);
	}
}
