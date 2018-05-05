package terraintesting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sun.scenario.effect.light.SpotLight;

import agua.generateTerrain;
import graphicsTesting.CameraController;
import javafx.animation.AnimationTimer;
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

//@Deprecated
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
		
		
		new AnimationTimer() {
            @Override
            public void handle(long now) {
            	//clear(gc);
            	//drawPlayer(gc);
            	//readUserInput(scene);
            	//notifyandDrawTerrains();
            	handleUserInput(scene, pCam, camera);
            	if(checkCamPosition((int)camera.getTranslateX(), (int)camera.getTranslateZ())) {
            		updateTerrains((int)camera.getTranslateX(), (int)camera.getTranslateZ(), mainGroup);
            	}
            	
            	try {
            		Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Failure of thread.sleep() in animation timer. You shouldn't see this.");
				}
            	//System.out.println("time step");
            }

			
        }.start();
        
        
		

		

		
		
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
		
		mainGroup.getChildren().add(light);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static TerrainObjectBasic[] setWorldTerrains(int x, int z) {
		//Forward strip
		TerrainObjectBasic terr1 = new TerrainObjectBasic(x-terrainSize, z+terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr2 = new TerrainObjectBasic(x, z+terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr3 = new TerrainObjectBasic(x+terrainSize, z+terrainSize, terrainSize, 10, (float) 0.3, 3838);
		//Middle strip
		TerrainObjectBasic terr4 = new TerrainObjectBasic(x-terrainSize, z, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr5 = new TerrainObjectBasic(x, z, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr6 = new TerrainObjectBasic(x+terrainSize, z, terrainSize, 10, (float) 0.3, 3838);
		//Backward strip
		TerrainObjectBasic terr7 = new TerrainObjectBasic(x-terrainSize, z-terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr8 = new TerrainObjectBasic(x, z-terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr9 = new TerrainObjectBasic(x+terrainSize, z-terrainSize, terrainSize, 10, (float) 0.3, 3838);
		/*
		TerrainObjectBasic terr5 = new TerrainObjectBasic(x, 		z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr4 = new TerrainObjectBasic(x+1000, 	z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr6 = new TerrainObjectBasic(x+1000, 	z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr2 = new TerrainObjectBasic(x, 		z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		//TerrainObjectBasic terr1 = new TerrainObjectBasic(x-1000,	z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr3 = new TerrainObjectBasic(x-1000,	z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr7 = new TerrainObjectBasic(x, 		z-1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr8 = new TerrainObjectBasic(x-1000,	z-1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr9 = new TerrainObjectBasic(x+1000,		z-1000, 	terrainSize, 10, (float) 0.3, 3838);*/
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
			//unload backstrip
			g.getChildren().remove(mvCopies.get(7-1));
			g.getChildren().remove(mvCopies.get(8-1));
			g.getChildren().remove(mvCopies.get(9-1));
			centerTerrZ+=terrainSize;
			//Build new forward strip
			TerrainObjectBasic terr1 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr2 = new TerrainObjectBasic(centerTerrX, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr3 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
			
			world[7-1] = world[4-1];
			world[8-1] = world[5-1];
			world[9-1] = world[6-1];
			
			world[4-1] = world[1-1];
			world[5-1] = world[2-1];
			world[6-1] = world[3-1];
			
			world[1-1] = terr1;
			world[2-1] = terr2;
			world[3-1] = terr3;

			mvCopies.remove(9-1);
			mvCopies.remove(8-1);
			mvCopies.remove(7-1);
			
			mvCopies.add(1-1, terr1.getMeshview());
			mvCopies.add(2-1, terr2.getMeshview());
			mvCopies.add(3-1, terr3.getMeshview());
			
			/*mvCopies.add(1-1, terr1.getMeshview());
			mvCopies.add(2-1, terr2.getMeshview());
			mvCopies.add(3-1, terr3.getMeshview());*/
			
			g.getChildren().add(mvCopies.get(1-1));
			g.getChildren().add(mvCopies.get(2-1));
			g.getChildren().add(mvCopies.get(3-1));
		}
		else if(camZ<centerTerrZ) {
			g.getChildren().remove(mvCopies.get(1-1));
			g.getChildren().remove(mvCopies.get(2-1));
			g.getChildren().remove(mvCopies.get(3-1));
			centerTerrZ-=terrainSize;
			TerrainObjectBasic terr7 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr8 = new TerrainObjectBasic(centerTerrX, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr9 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
			
			world[1-1] = world[4-1];
			world[2-1] = world[5-1];
			world[3-1] = world[6-1];
			
			world[4-1] = world[7-1];
			world[5-1] = world[8-1];
			world[6-1] = world[9-1];
			
			world[7-1] = terr7;
			world[8-1] = terr8;
			world[9-1] = terr9;
			
			mvCopies.remove(3-1);
			mvCopies.remove(2-1);
			mvCopies.remove(1-1);
			
			mvCopies.add(7-1, terr7.getMeshview());
			mvCopies.add(8-1, terr8.getMeshview());
			mvCopies.add(9-1, terr9.getMeshview());
			
			g.getChildren().add(mvCopies.get(7-1));
			g.getChildren().add(mvCopies.get(8-1));
			g.getChildren().add(mvCopies.get(9-1));
			System.out.println(mvCopies.size());
		}
		else if(camX>terrainSize+centerTerrX) {
			g.getChildren().remove(mvCopies.get(1-1));
			g.getChildren().remove(mvCopies.get(4-1));
			g.getChildren().remove(mvCopies.get(7-1));
			centerTerrX+=terrainSize;
			TerrainObjectBasic terr3 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr6 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr9 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
			
			world[1-1] = world[2-1];
			world[4-1] = world[5-1];
			world[7-1] = world[8-1];
			
			world[2-1] = world[3-1];
			world[5-1] = world[6-1];
			world[8-1] = world[9-1];
			
			world[3-1] = terr3;
			world[9-1] = terr6;
			world[9-1] = terr9;
			
			mvCopies.remove(7-1);
			mvCopies.remove(4-1);
			mvCopies.remove(1-1);
			
			mvCopies.add(3-1, terr3.getMeshview());
			mvCopies.add(6-1, terr6.getMeshview());
			mvCopies.add(9-1, terr9.getMeshview());
			
			g.getChildren().add(mvCopies.get(3-1));
			g.getChildren().add(mvCopies.get(6-1));
			g.getChildren().add(mvCopies.get(9-1));
		}
		else if(camX<centerTerrX) {
			g.getChildren().remove(mvCopies.get(3-1));
			g.getChildren().remove(mvCopies.get(6-1));
			g.getChildren().remove(mvCopies.get(9-1));
			centerTerrX-=terrainSize;
			TerrainObjectBasic terr1 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr4 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ, terrainSize, 10, (float) 0.3, 3838);
			TerrainObjectBasic terr7 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
			
			world[3-1] = world[2-1];
			world[6-1] = world[5-1];
			world[9-1] = world[8-1];
			
			world[2-1] = world[1-1];
			world[5-1] = world[4-1];
			world[8-1] = world[7-1];
			
			world[1-1] = terr1;
			world[4-1] = terr4;
			world[7-1] = terr7;
			
			
			mvCopies.remove(9-1);
			mvCopies.remove(6-1);
			mvCopies.remove(3-1);
			
			mvCopies.add(1-1, terr1.getMeshview());
			mvCopies.add(4-1, terr4.getMeshview());
			mvCopies.add(7-1, terr7.getMeshview());
			
			g.getChildren().add(mvCopies.get(1-1));
			g.getChildren().add(mvCopies.get(4-1));
			g.getChildren().add(mvCopies.get(7-1));
		}
	}
	
	
	
	private void handleUserInput(Scene scene, CameraController pCam, Camera camera) {
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
	}
}
