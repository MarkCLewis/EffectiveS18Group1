package graphicsTesting;

import java.util.HashSet;
import java.util.Set;

import agua.generateTerrain;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import terraintesting.TerrainObjectBuilder;

public class PresentationDemo extends Application {
	public static void main(String[] args) {
		launch();
	}
	
	public void start(Stage stage) {
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
			 if(keySet.contains(KeyCode.K)) {
				 mainGroup.getChildren().add(DrawFacade.getBoxBuilder(20,20,20)
						 .transCoords(pCam.getCameraX(),pCam.getCameraY(),pCam.getCameraZ()).material(new PhongMaterial(Color.BLUE)).build().get());
			 }
		});
		
		scene.setOnKeyReleased(event ->{
			KeyCode key = event.getCode();
			if(key == KeyCode.SHIFT) {
				pCam.boostOff();
			}
			keySet.remove(key);
			
		});
		
		PhongMaterial pm1 = new PhongMaterial(Color.RED);
		PhongMaterial pm2 = new PhongMaterial(Color.BLUE);
		PhongMaterial pm3 = new PhongMaterial(Color.GREEN);
		PhongMaterial pm4 = new PhongMaterial(Color.YELLOW);
		
		Box box = DrawFacade.getBoxBuilder(3, 1, 1).transCoords(485, -5, 250).material(pm1).build().get();
		Cylinder cyl = DrawFacade.getCylinderBuilder(1, 2).transCoords(495, -5, 250).material(pm2).build().get();
		MeshView pyr = DrawFacade.getPyramidBuilder(2, 2).transCoords(505, -5, 250).material(pm3).build().get();
		
		float[] points = {
		        0,    0,    0,            // Point 0 - 000
		        1,    0,    0,            // Point 1 - l00
		        1,    0,    1,            // Point 2 - l0w
		        0,    0,    1,            // Point 3 - 00w
		        0,    1,    1,            // Point 4 - 0hw
		        0,    1,    0,            // Point 5 - 0h0
		        1,    1,    0,            // Point 6 - lh0
		        1,    1,    1             // Point 4 - lhw
		    };
		int[] faces = {
				4,0,  7,0,  6,0,          // Top front
				5,0,  4,0,  6,0,          // Top back
		        5,0,  6,0,  0,0,          // Left top
		        6,0,  1,0,  0,0,          // Left bottom
		        6,0,  7,0,  1,0,          // Front top
		        7,0,  2,0,  1,0,          // Front bottom
		        7,0,  4,0,  2,0,          // Right top
		        4,0,  3,0,  2,0,          // Right bottom
		        4,0,  5,0,  3,0,          // Back top
		        5,0,  0,0,  3,0,          // Back bottom
		        1,0,  2,0,  0,0,          // Bottom back
		        2,0,  3,0,  0,0           // Bottom front
		};
		MeshView box2 = DrawFacade.getCustomMeshViewBuilder(points, faces).transCoords(515, -5, 250).material(pm4).build().get();
		
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
		
		PhongMaterial pm = new PhongMaterial(Color.GREEN);
		
		PointLight light = new PointLight();
		//Point3D lightRotation = new Point3D(mesh.getPoints().get(50), mesh.getPoints().get(51) + 100, mesh.getPoints().get(52));
		//light.setRotationAxis();
		light.setRotate(45);
		light.setLayoutX(500);
		light.setLayoutY(500);
		light.setTranslateZ(500);
		light.setScaleX(5);
		light.setScaleY(10);
		
		mv.setDrawMode(DrawMode.FILL);
		mv.setMaterial(pm);
		
		mainGroup.getChildren().add(mv);
		mainGroup.getChildren().add(light);
		mainGroup.getChildren().addAll(box, cyl, pyr, box2);
		
		stage.setScene(scene);
		stage.show();
	}
}
