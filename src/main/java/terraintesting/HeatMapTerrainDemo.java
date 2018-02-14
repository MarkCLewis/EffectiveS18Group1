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
 * Daniel and Miranda helps
 */

public class HeatMapTerrainDemo extends Application {
	private static final int width = 600;
	private static final int height = 600;
	private static final int maxElevation = 2000;
	private static int[][] elevation = new int[width][height];
	private static Color[][] heatMap = new Color[width][height];
	
	@Override public void start(Stage primaryStage) {
		Group group = new Group();
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		buildElevation(); 
		buildHeatMap();
		drawHeatMap(gc);
		group.getChildren().add(canvas);
		primaryStage.setScene(new Scene(group));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public static void buildElevation() {
		simpleHillAndValleyWNeighborAvgSmoothing();//neighborAverageSmoothing(); //Modify this method call to try out different strategies
	}
	
	public static void neighborAverageSmoothing() {
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				elevation[x][y]= randomElevation();
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++) 
				elevation[x][y]= getNeighborAvg(x,y);
		/*for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				elevation[x][y] = maxElevation % (elevation[x][y]+randomElevation()/100);*/
	}
	
	
	private static int getNeighborAvg(int x, int y) {
		int sum = 0;
		int count = 0;
		int[] points = new int[8];
		points[0] = getNeighborElev(x, y, -1, -1);
		points[1] = getNeighborElev(x, y, -1, 0);
		points[2] = getNeighborElev(x, y, -1, 1);
		points[3] = getNeighborElev(x, y, 0, -1);
		points[4] = getNeighborElev(x, y, 0, 1);
		points[5] = getNeighborElev(x, y, 1, -1);
		points[6] = getNeighborElev(x, y, 1, 0);
		points[7] = getNeighborElev(x, y, 1, 1);
		
		for(int i=0; i<points.length; i++) {
			if(points[i]!=-1) {
				sum+=points[i];
				count++;
			}
		}
		
		return sum/count;
	}
	
	private static int getNeighborElev(int x, int y, int deltaX, int deltaY) {
		int x2 = x+deltaX;
		int y2 = y+deltaY;
		if(x2<0 || y2<0 || x2>=width || y2>=height)
			return -1;
		else
			return elevation[x2][y2];
	}
	
	public static void simpleHillAndValleyWNeighborAvgSmoothing() {
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				elevation[x][y] = simpleHillAndValley(x,y);
		for(int x=0; x<width; x+=2)
			for(int y=0; y<height; y+=2)
				elevation[x][y] = getNeighborAvg(x,y);
	}
	
	public static int simpleHillAndValley(int x, int y) {
		return (int) ((maxElevation/4*Math.sin((double)x/width*12)+maxElevation/4) + 
		 (maxElevation/4*Math.sin((double)y/height*12)+maxElevation/4));
	}
	
	/*
	 * diamond square algorithm to construct terrain (works with squares only)
	 * @ param squareWidth - the width of the square you're making terrain with
	 * @ param ulX - the upperleft hand corner of the square you're making terrain with
	 * @ param ulY - the upperright hand corner of the square you're making terrain with
	 */
	public static void buildFractalElevation(int squareWidth, int ulX, int ulY) {
		if (squareWidth >= 2) {
			diamondStep(squareWidth, ulX, ulY);
			//TODO
			
		}
		
	}

	private static void diamondStep(int squareWidth, int ulX, int ulY) {
		elevation[ulX][ulY] = randomElevation();
		elevation[ulX][ulY + squareWidth] = randomElevation();
		elevation[ulX + squareWidth][ulY] = randomElevation();
		elevation[ulX + squareWidth][ulY + squareWidth] = randomElevation();
		
		int averageElevation = (elevation[ulX][ulY] + 
								elevation[ulX][ulY + squareWidth] + 
								elevation[ulX + squareWidth][ulY] +
								elevation[ulX + squareWidth][ulY + squareWidth]) / 4;
		
		//TODO
		int peturbation = 0;
		elevation[ulX + squareWidth/2][ulY + squareWidth/2] = averageElevation + peturbation;
	}
	
	public static void squareStep(int squareWidth, int ulX, int ulY) {
		//TODO
		
	}

    /*
     * tesselates the square domain into a triangular grid because triangles are always coplaner
     * @param 
     */
    public static void triangleRender() {
        
    }
	
	/*
	 *  returns a random elevation between 0 and the maxElevation
	 *  @ return an elevation
	 */
	public static int randomElevation() {
		return (int) (maxElevation * Math.random());
	}
	
	public static void buildHeatMap() {
		for(int x=0; x<heatMap.length; x++)
			for(int y=0; y<heatMap[0].length; y++) {
				heatMap[x][y] = intToColor(elevation[x][y], maxElevation);
			}
	}
	
	public static void drawHeatMap(GraphicsContext gc) {
		PixelWriter pw = gc.getPixelWriter();
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++) 
				pw.setColor(x,y,heatMap[x][y]);
	}
	
	public static void simpleHillAndValley() {
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				elevation[x][y]= (int) ((maxElevation/4*Math.sin((double)x/width*12)+maxElevation/4) + 
						 (maxElevation/4*Math.sin((double)y/height*12)+maxElevation/4));;
	}
	
	//converts an int to a color representing its intensity given a specified max
	//min is assumed to be 0
	//assumes 0<=i<=max
	//There's a lot of conversions b/t ints and doubles to avoid integer division (it doesn't work with all ints)
	public static Color intToColor(int i, int max) {
		double red; double green; double blue;
		double iDouble = (double)i;
		double maxDouble = (double)max;
		//TODO
		/*
		if(i > max) {
			
		}
		*/
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
