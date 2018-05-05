package terraintesting;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class TerrainObjectDisplayDemo2D extends Application {
	
	
	public static void main(String[] args) {
		System.out.println("app start");
		launch();
		System.out.println("app end");
	}
	
	@Override public void start(Stage primaryStage) {
		int xWidth = 300;
		int zWidth = 300;
		int yWidth = 300;
		Group group = new Group();
		Canvas canvas = new Canvas(xWidth, zWidth);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawHeatMap(heatmap(simplexHeatMapTest(xWidth, yWidth, zWidth), yWidth), gc);
		group.getChildren().add(canvas);
		primaryStage.setScene(new Scene(group));
		primaryStage.show();
	}
	
	public static void drawHeatMap(Color[][] heatmap, GraphicsContext gc) {
		PixelWriter pw = gc.getPixelWriter();
		for(int x=0; x<heatmap.length; x++)
			for(int z=0; z<heatmap[0].length; z++) 
				pw.setColor(x,z,heatmap[x][z]);
	} 
	
	public static Color[][] heatmap(float[][] terrain, float max) {
		Color[][] colors = new Color[terrain.length][terrain[0].length];
		for(int x=0; x<terrain.length; x++) {
			for(int z=0; z<terrain[0].length; z++) {
				colors[x][z] = HeatMapTerrainDemo.doubleToColor(terrain[x][z], max);
			}
		}
		return colors; 
	}

	public static float[][] simplexHeatMapTest(int xWidth, int yWidth, int zWidth) {
		agua.generateTerrain testPlot = new agua.generateTerrain();
		float[][] temp = testPlot.generateCoordinates(xWidth, yWidth, zWidth);
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		for(int x=0; x<xWidth; x++) {
			for(int z=0; z<zWidth; z++) {
				temp[x][z]*=-1;
				if(temp[x][z]>max)
					max = temp[x][z];
				if(temp[x][z]<min)
					min = temp[x][z];
			}
		}
		//scaling the heights
		for(int x=0; x<xWidth; x++) {
			for(int z=0; z<zWidth; z++) {
				temp[x][z] = (temp[x][z]-min)*yWidth/max;
				//System.out.print(temp[x][z]+" ");
			}
			System.out.println();
		}
		return temp;
		
	}
	
	public static void terrainObjectTest() {
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		double x = 0.0; 
		double z = 0.0; 
		double y = 0.0;
		double xW = 200; 
		double zW = 200; 
		double yW = 200;
		long seed = 3838;
		double noise = 0.60;
		bldr.setXLoc(x);
		bldr.setYLoc(y);
		bldr.setZLoc(z);
		bldr.setXWidth(xW);
		bldr.setYWidth(yW);
		bldr.setZWidth(zW);
		bldr.setSeed(seed);
		bldr.setNoise(noise);
		
		TerrainObject terr = bldr.build();
		terr.display(); // gives list of Shape3D
	}
}
