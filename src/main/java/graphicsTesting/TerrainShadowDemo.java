package graphicsTesting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sun.scenario.effect.light.SpotLight;

import agua.generateTerrain;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import terraintesting.TerrainObject;
import terraintesting.TerrainObjectBuilder;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class TerrainShadowDemo extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			 if(keySet.contains(KeyCode.L)) {
				 System.out.println("X = "+camera.getTranslateX()+" Z = "+camera.getTranslateZ()+" Y = "+camera.getTranslateY());
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
		
		generateTerrain testPlot = new generateTerrain();
		float[][] coords = testPlot.generateCoordinates(100, 100, 100, 10, (float).3, (int)seed); //int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed
		
		
		TriangleMesh mesh = testPlot.generateTerrain(100, 10, coords);
		
		MeshView mv = new MeshView(mesh);
		mv.setCullFace(CullFace.FRONT);
		mv.setTranslateX(0);
		//mv.setLayoutX(0);
		mv.setTranslateZ(0);
		
		//MeshView mv2 = new MeshView(mesh);
		//mv2.setTranslateX(-990);
		//mv2.setTranslateZ(0);
		//mv2.setCullFace(CullFace.FRONT);
		PhongMaterial pm = new PhongMaterial(Color.GREEN);
		
		PointLight light = new PointLight();
		//Point3D lightRotation = new Point3D(mesh.getPoints().get(50), mesh.getPoints().get(51) + 100, mesh.getPoints().get(52));
		//light.setRotationAxis();
		light.setRotate(45);
		light.setLayoutX(500);
		light.setLayoutY(500);
		light.setTranslateZ(0);
		light.setScaleX(5);
		light.setScaleY(10);
		
		//camera.setLayoutY(50);
		//camera.setRotate(180);

		/*
		Light.Distant light = new Light.Distant();
		light.setAzimuth(0); 
	    light.setElevation(100);
	    Lighting lighting = new Lighting();
	    lighting.setLight(light);
	    mv.setEffect(lighting);
		*/
		
		mv.setDrawMode(DrawMode.FILL);
		//mv2.setDrawMode(DrawMode.FILL);
		mv.setMaterial(pm);
		//mv2.setMaterial(pm);
		mainGroup.getChildren().add(mv);
		//mainGroup.getChildren().add(mv2);
		mainGroup.getChildren().add(light);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
