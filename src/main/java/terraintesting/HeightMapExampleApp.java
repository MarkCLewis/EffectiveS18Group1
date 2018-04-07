package terraintesting;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class HeightMapExampleApp extends Application {
	private static final int screenW = 600;
	private static final int screenH = 600;
	private static final int triangleWidth = screenW/6;
	
	// NOT my code - taken from John's Graphics Code
	private double x1 = 0;
	private double y1 = 0;
	private double x2;
	private double y2;
	private double dx;
	private double dy;
	private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	private double rotateModifier = 25;
	private double cameraYlimit = 15;
	private double cameraFarClip = 10000;
	private double cameraNearClip = 3;
	
	public static void main(String[] args) {
		System.out.println("app started");
		launch();
	}

	
	@Override
	public void start(Stage stage) throws Exception {	
		ExampleTGA tga = new ExampleTGA();
		HeightMapExample hme = new HeightMapExample(screenW, screenH, tga, 0); 
		TriangleMesh tm = TerrainToTriangleMesh.getTriangleMesh(hme, triangleWidth);
		MeshView mv = new MeshView(tm);
		
 
		// NOT my code - copied from John's graphics code
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, screenW, screenH, true);
		scene.setFill(Color.GRAY);
		
		
		//Camera
		Camera camera = new PerspectiveCamera(true);
		camera.setFarClip(cameraFarClip);
		camera.setNearClip(cameraNearClip);
		camera.getTransforms().addAll(xRotate,yRotate);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(camera);
		mainGroup.getChildren().add(cameraGroup);
		
		//Camera Movement
		double camSpeed = 1.0;
		scene.setOnKeyPressed(event -> {
		 KeyCode key = event.getCode();
		 double z = camera.getTranslateZ();
		 double x = camera.getTranslateX();
		 double theta = yRotate.getAngle()/360.0*2*Math.PI; 
		 if(key == KeyCode.W) {
			 camera.setTranslateZ(z+camSpeed*Math.cos(theta));
			 camera.setTranslateX(x+camSpeed*Math.sin(theta));
		 }
		 if(key == KeyCode.S) {
			 camera.setTranslateZ(z-camSpeed*Math.cos(theta));
			 camera.setTranslateX(x-camSpeed*Math.sin(theta));
		 }
		 if(key == KeyCode.A) {
			 camera.setTranslateZ(z+Math.sin(theta));
			 camera.setTranslateX(x-Math.cos(theta));
		 }
		 if(key == KeyCode.D) {
			 camera.setTranslateZ(z-Math.sin(theta));
			 camera.setTranslateX(x+Math.cos(theta));
		 }
		 
		 if(key == KeyCode.RIGHT) {
			 yRotate.setAngle(yRotate.getAngle()+10);
		 }
		 if(key == KeyCode.LEFT) {
			 yRotate.setAngle(yRotate.getAngle()-10);
		 }
		});

		scene.addEventHandler(MouseEvent.ANY, event -> {
			if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
				x2 = event.getSceneX();
				y2 = event.getSceneY();
			 
				//calculate the rotational change of the camera pitch
				double pitchRotate =xRotate.getAngle()+(y2 - y1) / rotateModifier;
				
				//set min/max camera pitch to prevent camera flipping
				pitchRotate = pitchRotate > cameraYlimit ? cameraYlimit : pitchRotate;
				pitchRotate = pitchRotate < -cameraYlimit ? -cameraYlimit : pitchRotate;
				
				//replace the old camera pitch rotation with the new one.
				xRotate.setAngle(pitchRotate);
				
				//calculate the rotational change of the camera yaw
				double yawRotate=yRotate.getAngle()-(x2 - x1) / rotateModifier;
				yRotate.setAngle(yawRotate);
			
				x1 = x2;
				y1 = y2;
				scene.setCursor(Cursor.NONE);
		 	}
		});	
		mainGroup.getChildren().add(mv);
		
		stage.setScene(scene);
		stage.show();
	}
	
}


class HeightMapExample implements HeightMap {
	private int xWidth;
	private int zWidth;
	private int[][] hm;
	
	@Override
	public int height(int x, int z) {
		return hm[x][z];
	}

	@Override
	public int xWidth() {
		return xWidth;
	}

	@Override
	public int zWidth() {
		return zWidth;
	}
	
	/*public HeightMapExample(int xW, int zW, int[][] hm) {
		this.hm = hm;
		xWidth = xW;
		z
	}*/
	
	public HeightMapExample(int xW, int zW, TerrainGenerationAlgorithm tga, int maxElev) {
		xWidth = xW;
		zWidth = zW;
		hm = new int[xWidth][zWidth];
		tga.generateTerrain(hm, maxElev);
	}
	

}

class ExampleTGA implements TerrainGenerationAlgorithm {

	@Override
	public void generateTerrain(int[][] heightMap, int maxElev) {
		for(int x=0; x<heightMap.length; x++) {
			for(int z=0; z<heightMap[0].length; z++) {
				heightMap[x][z] = 0;
			}
		}
		
	}
	
}