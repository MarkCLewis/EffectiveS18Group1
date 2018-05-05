package terraintesting;

import java.util.HashSet;
import java.util.Set;

import agua.generateTerrain;
import graphicsTesting.CameraController;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

// Class for testing 3D meshview display for dynamic loading of terrain
// The graphics code was taken from Jon's code in the graphics package
public class TerrainObjectWorldDemo3D extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.GRAY);
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(camera);
		mainGroup.getChildren().add(cameraGroup);
		
		CameraController pCam = new CameraController.Builder(camera).build();
		
		Set<KeyCode> keySet = new HashSet<KeyCode>();
		scene.setOnKeyPressed(event ->{ 
			 KeyCode key = event.getCode();
			 keySet.add(key);
			 
			 if(keySet.contains(KeyCode.W)) {
				 pCam.moveForward();
			 }
			 if(keySet.contains(KeyCode.S)) {
				 pCam.moveBackward();
			 }
			 if(keySet.contains(KeyCode.A)) {
				 pCam.moveLeft();
			 }
			 if(keySet.contains(KeyCode.D)) {
				 pCam.moveRight();
			 }
			 if(keySet.contains(KeyCode.RIGHT)) {
				 pCam.rotateRight();
			 }
			 if(keySet.contains(KeyCode.LEFT)) {
				 pCam.rotateLeft();
			 }
			 if(keySet.contains(KeyCode.UP)) {
				 pCam.rotateUp();
			 }
			 if(keySet.contains(KeyCode.DOWN)) {
				 pCam.rotateDown();
			 }
			 if(keySet.contains(KeyCode.R)) {
				 pCam.moveUp();
			 }
			 if(keySet.contains(KeyCode.F)) {
				 pCam.moveDown();
			 }
			 if(keySet.contains(KeyCode.SHIFT)) {
				 pCam.boostOn();
			 }
		});
		
		scene.setOnKeyReleased(event ->{
			KeyCode key = event.getCode();
			if(key == KeyCode.SHIFT) {
				pCam.boostOff();
			}
			keySet.remove(key);
			
		});

		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		double x = 0; 
		double z = 0; 
		double y = 0;
		double xW = 100; 
		double zW = 100; 
		double yW = 100;
		long seed = 3838;
		double noise = 0.30;
		bldr.setXLoc(x);
		bldr.setYLoc(y);
		bldr.setZLoc(z);
		bldr.setXWidth(xW);
		bldr.setYWidth(yW);
		bldr.setZWidth(zW);
		bldr.setSeed(seed);
		bldr.setNoise(noise);
		
		TerrainObject worldTerrainObject = bldr.build();
		
		//generateTerrain testPlot = new generateTerrain();
		//float[][] coords = testPlot.generateCoordinates(100, 100, 100, 10, (float).3, (int)seed); //int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed
		
		
		//TriangleMesh mesh = testPlot.generateTerrain(100, 10, coords);
		
		MeshView mv = (MeshView) worldTerrainObject.display().get(0);//new MeshView(mesh);
		mv.setCullFace(CullFace.FRONT);
		
		PhongMaterial pm = new PhongMaterial(Color.GREEN);
		
		PointLight light = new PointLight();
		//Point3D lightRotation = new Point3D(mesh.getPoints().get(50), mesh.getPoints().get(51) + 100, mesh.getPoints().get(52));
		//light.setRotationAxis();
		light.setRotate(45);
		light.setLayoutX(500);
		light.setLayoutY(500);
		light.setScaleX(5);
		light.setScaleY(10);
		
		/*
		Light.Distant light = new Light.Distant();
		light.setAzimuth(0); 
	    light.setElevation(100);
	    Lighting lighting = new Lighting();
	    lighting.setLight(light);
	    mv.setEffect(lighting);
		*/
		
		mv.setDrawMode(DrawMode.LINE);
		mv.setMaterial(pm);
		mainGroup.getChildren().add(mv);
		//mainGroup.getChildren().add(light);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
