package terraintesting;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class DyamicTerrainLoadingDemo2D extends Application {
	private static final int xWidth = 800;
	private static final int zWidth = xWidth;
	//private static final int playerViewDistance = 50;
	private static int playerX = 200; 
	private static int playerZ = 200;
	private static ArrayList<TerrainObject> terrains = new ArrayList<TerrainObject>();
	
	public static void main(String[] args)  {
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		double x = 0; 
		double z = 0; 
		double y = 0;
		double xW = xWidth; 
		double zW = zWidth; 
		double yW = xWidth;
		//long seed = 3838;
		//double noise = 0.30;
		bldr.setXLoc(x);
		bldr.setYLoc(y);
		bldr.setZLoc(z);
		bldr.setXWidth(xW);
		bldr.setYWidth(yW);
		bldr.setZWidth(zW);
		//bldr.setSeed(seed);
		//bldr.setNoise(noise);
		
		TerrainObject world = bldr.build();
		terrains.add(world);
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Group group = new Group();
		Canvas canvas = new Canvas(xWidth, zWidth);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Scene scene = new Scene(group);
		new AnimationTimer() {
            @Override
            public void handle(long now) {
            	clear(gc);
            	drawPlayer(gc);
            	readUserInput(scene);
            	notifyandDrawTerrains();
            	try {
            		Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Failure of thread.sleep() in animation timer. You shouldn't see this.");
				}
            	//System.out.println("time step");
            }
        }.start();
		group.getChildren().add(canvas);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void clear(GraphicsContext gc) {
		gc.setFill(javafx.scene.paint.Color.WHITE);
		gc.fillRect(0, 0, xWidth, zWidth);
	}
	
	public static void drawPlayer(GraphicsContext gc) {
		int playerDisplayWidth = 4;
		javafx.scene.paint.Color playerColor = javafx.scene.paint.Color.RED; 
		PixelWriter pw = gc.getPixelWriter();
		for(int x=0; x < playerDisplayWidth; x++) {
			for(int z=0; z < playerDisplayWidth; z++) {
				int xToDraw = cartToDispX(playerX)-playerDisplayWidth/2+x;
				int zToDraw = cartToDispZ(playerZ)-playerDisplayWidth/2+z;
				pw.setColor(xToDraw, zToDraw, playerColor);
			}
		}
	}
	
	private void readUserInput(Scene scene) {
		int moveDist = 4;
		scene.setOnKeyPressed(e -> {
    	    if (e.getCode() == KeyCode.UP) {
    	    	playerZ+=4;
    	    	System.out.println("Up");
    	    }
    	    else if (e.getCode() == KeyCode.DOWN) {
    	    	playerZ-=4;
    	    	System.out.println("Down");
    	    }
    	    else if (e.getCode() == KeyCode.RIGHT) {
    	    	playerX+=4;
    	    	System.out.println("Right");
    	    }
    	    else if (e.getCode() == KeyCode.LEFT) {
    	    	playerX-=4;
    	    	System.out.println("Left");
    	    }
    	});
	}
	
	private void notifyandDrawTerrains() {
		System.out.println(terrains.size());
		ArrayList<TerrainObject> newTerrains = new ArrayList<TerrainObject>();
		for(TerrainObject terr : terrains) {
			System.out.println("Width = "+terr.getX());
			newTerrains.addAll(terr.notifyAndGiveTerrain(playerX, playerZ));
		}
		terrains.addAll(newTerrains);
	}
	
	private void drawTerrains() {
		// TODO Auto-generated method stub
		
	}
	
	public static int[] CartesianToDisplay(int x, int z) {
		int[] coords = {x+xWidth/2, zWidth/2-z};
		return coords;
	}
	
	public static int cartToDispX(int x) {
		int coord = x+xWidth/2;
		return coord;
	}
	
	public static int cartToDispZ(int z) {
		int coord =  zWidth/2-z;
		return coord;
	}
	
}
