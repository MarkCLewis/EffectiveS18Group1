package terraintesting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sun.scenario.effect.light.SpotLight;

import agua.generateTerrain;
import graphicsTesting.CameraController;
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

public class TerrainShadowDemoWithDynamicLoading extends Application {
	private static final int terrainSize = 1000;
	private static ArrayList<MeshView> mvCopies = new ArrayList<MeshView>();
	private static int centerTerrX = 0;
	private static int centerTerrZ = 0;
	private static TerrainObjectBasic[] world = setWorldTerrains(centerTerrX, centerTerrZ);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.BLANCHEDALMOND);
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

		

		
		
		//TriangleMesh mesh = testPlot.generateTerrain(100, 10, coords);
		
		//MeshView mv = terr1.getMeshview();//new MeshView(mesh);
		//mv.setCullFace(CullFace.FRONT);
		
		//PhongMaterial pm = new PhongMaterial(Color.GREEN);
		
		PointLight light = new PointLight();
		//Point3D lightRotation = new Point3D(mesh.getPoints().get(50), mesh.getPoints().get(51) + 100, mesh.getPoints().get(52));
		//light.setRotationAxis();
		light.setRotate(45);
		light.setLayoutX(500);
		light.setLayoutY(500);
		light.setTranslateZ(500);
		light.setScaleX(5);
		light.setScaleY(10);
		
		camera.setTranslateX(500);
		camera.setTranslateZ(500);
		
		/*
		Light.Distant light = new Light.Distant();
		light.setAzimuth(0); 
	    light.setElevation(100);
	    Lighting lighting = new Lighting();
	    lighting.setLight(light);
	    mv.setEffect(lighting);
		*/
		
		//mv.setDrawMode(DrawMode.FILL);
		//mv.setMaterial(pm);
		
		for(TerrainObjectBasic terr : world) {
			MeshView mv = terr.getMeshview();
			mvCopies.add(mv);
			mainGroup.getChildren().add(mv);
		}
		mainGroup.getChildren().remove(mvCopies.get(0));
		mainGroup.getChildren().remove(mvCopies.get(1));
		mainGroup.getChildren().remove(mvCopies.get(2));
		
		mainGroup.getChildren().add(light);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static TerrainObjectBasic[] setWorldTerrains(int x, int z) {
		TerrainObjectBasic terr5 = new TerrainObjectBasic(x, 		z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr4 = new TerrainObjectBasic(x+1000, 	z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr6 = new TerrainObjectBasic(x+1000, 	z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr2 = new TerrainObjectBasic(x, 		z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr1 = new TerrainObjectBasic(x-1000,	z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr3 = new TerrainObjectBasic(x-1000,	z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr7 = new TerrainObjectBasic(x, 		z-1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr8 = new TerrainObjectBasic(x-1000,	z-1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr9 = new TerrainObjectBasic(x+1000,		z-1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic[] world = {terr1, terr2, terr3, terr4, terr5, terr6, terr7, terr8, terr9};
		return world;
	}
	
	// returns true if terrain needs to be loaded/unloaded
	public boolean checkCamPosition(int camX, int camZ) {
		return camX<centerTerrX || camZ<centerTerrZ || camX>terrainSize+centerTerrX || camZ>terrainSize+centerTerrZ;
	}
	//updates terrains and graphics
	public void updateTerrains(int camX, int camZ, Group g) {
		if(camZ>terrainSize+centerTerrZ) {
			g.getChildren().remove(mvCopies.get(6));
			g.getChildren().remove(mvCopies.get(7));
			g.getChildren().remove(mvCopies.get(8));
		}
	}
}
