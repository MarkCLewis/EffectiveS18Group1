package agua;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.AmbientLight;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

//
//			Testing closeup generation of water
//			Likely, this will involve some sort of difficult math to create multiple meshes that are then sewn together to form a body of water
//

@SuppressWarnings("restriction")
public class closeupRendering extends Application {
    // THESE ARE TEST COORDINATES
	// coordinates (and explanation) were found here: http://www.lagers.org.uk/javafx/usemeshview.html
	
	
	@Override
	public void start(Stage primaryStage) throws AWTException {
		
		// the main stage. I was not able to hide the controls block of code, unfortunately
		
		createScene sceneSetup = new createScene(); // Making this a sceneSetup so that later on i can add in the ability to easily adjust the scene settings 
		cameraSettings cameraSetup = new cameraSettings();
		PerspectiveCamera camera = cameraSetup.cameraSettings(); // creates camera with preset settings
		
		//AmbientLight ambience = new AmbientLight(); // default color white
	    //ambience.setLightOn(true); // switch it off and everything is black
		
		Group sceneGroup = new Group();
		//sceneGroup.getChildren().add(ambience);
		PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateX(0);
        pointLight.setTranslateY(0);
        pointLight.setTranslateZ(0);
        pointLight.setRotate(90);
        //sceneGroup.getChildren().add(pointLight);
		sceneGroup.getChildren().add(camera);
		Scene scene = new Scene(sceneGroup, 1280, 720);
		scene.setCamera(camera);
		scene.setFill(Color.DARKBLUE);
		
		// The camera controls, these cannot be hidden away without causing major issues		
		scene.setOnKeyPressed(event-> {
			double change = cameraSetup.cameraQuantity;
			if(event.isShiftDown()) { change = cameraSetup.cameraModifier;}
			KeyCode key = event.getCode();
			if(key == KeyCode.Q) {System.exit(0);}
			if(key == KeyCode.W) {camera.setTranslateZ(camera.getTranslateZ() + change);}
			if(key == KeyCode.S) {camera.setTranslateZ(camera.getTranslateZ() - change);}
			if(key == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change);}
			if(key == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change);}
			if(key == KeyCode.Z) { camera.setTranslateY(camera.getTranslateY() + change);}
			if(key == KeyCode.X) { camera.setTranslateY(camera.getTranslateY() - change);}
		});
		Robot mouse = new Robot();
		scene.addEventHandler(MouseEvent.ANY, event -> {
			if (event.getEventType() == MouseEvent.MOUSE_MOVED)
		 		{
				double mouseXnew = event.getSceneX();
				double mouseYnew = event.getSceneY();
				double pitchRotate =cameraSetup.xRotate.getAngle()+(mouseYnew - cameraSetup.mouseYold) / cameraSetup.rotateModifier;
				pitchRotate = pitchRotate > cameraSetup.cameraYlimit ? cameraSetup.cameraYlimit : pitchRotate;
				pitchRotate = pitchRotate < -cameraSetup.cameraYlimit ? -cameraSetup.cameraYlimit : pitchRotate;
				cameraSetup.xRotate.setAngle(pitchRotate);
				double yawRotate=cameraSetup.yRotate.getAngle()-(mouseXnew - cameraSetup.mouseXold) / cameraSetup.rotateModifier;
				cameraSetup.yRotate.setAngle(yawRotate);
				cameraSetup.mouseXold = mouseXnew;
				cameraSetup.mouseYold = mouseYnew;
				scene.setCursor(Cursor.NONE);
		 		}
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED){mouse.mouseMove((int)scene.getWidth()/2,(int)scene.getHeight()/2);}
			//if(event.getEventType() == MouseEvent.MOUSE_EXITED){}
		});
		
		
		
		//MeshView test = createMeshView(points, texCoords, faces, 0, 0, 0, Color.BROWN);
		generateTerrain testPlot = new generateTerrain();
		float[][] temp = testPlot.generateCoordinates(200, 200, 200);
		TriangleMesh testGenerate = testPlot.generateTerrain(200, 10, temp);
		MeshView meshView = new MeshView(testGenerate);
		PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.AQUA);
        material.setSpecularColor(Color.ANTIQUEWHITE.brighter());
		meshView.setDrawMode(DrawMode.LINE);
		
		meshView.setScaleX(100);
		meshView.setScaleZ(100);
		meshView.setScaleY(10000); // this should scale with the SimplexNoise function.
								  // The x and z scale are always the same (they only change the dimensions of the terrain)
								  // The y scale matters because it is how high the elevation at certain parts are (in combination with the "persistence" passed to the SimplexNoise function)
		meshView.setTranslateX(0);
		meshView.setTranslateY(0);
		meshView.setTranslateZ(0);
		meshView.setMaterial(material);
		
		sceneGroup.getChildren().add(meshView);
		
		//sceneGroup.getChildren().add(test);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private MeshView createMeshView(float [] Points, float[] textCoords, int[] Faces, double X, double Y, double Z, Color COLOR) {
		//ObservableIntegerArray t = null ;
		//t.resize(Faces.length+1);
	
		TriangleMesh mesh = new TriangleMesh();
		mesh.getTexCoords().addAll(textCoords);
		mesh.getPoints().addAll(Points);
		mesh.getFaces().addAll(Faces);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.ANTIQUEWHITE);
        material.setSpecularColor(Color.ANTIQUEWHITE.brighter());
        ObservableIntegerArray test;
        int[] smoothing = new int[Faces.length+1];
        
        mesh.getFaces().toArray(smoothing);
        
        Arrays.fill(smoothing, 1);
        //mesh.getFaceSmoothingGroups().addAll(smoothing);
		//In smooth surfaces vertices are shared between faces (or you could say those faces share a vertex). 
		//In that case the normal at the vertex is not one of the face normals of the faces it is part of, but a linear combination of them:
		ObservableFloatArray normals = mesh.getNormals(); //normals array where each normal is represented by 3 float values nx, ny and nz, in that order.
		// adjacent pair of faces shared a smoothing group
		 //If faceSmoothingGroups is not empty, its size must be equal to number of faces.
        mesh.getNormals().addAll(
        ///		X Y Z
        		1,1,1, 
        		0,1,1, 
        		0,1,1, 
        		0,1,1,
        		0,1,1,
        		0,1,1
        		);

		// The face smoothing group value is represented by an array of bits and up to 32 unique groups is possible; (1 << 0) to (1 << 31). 
		//The face smoothing group value can range from 0 (no smoothing group) to all 32 groups. A face can belong to zero or more smoothing groups.
		//A face is a member of group N if bit N is set, for example, groups |= (1 << N). A value of 0 implies no smoothing group or hard edges. 
		//Smoothing is applied when adjacent pair of faces shared a smoothing group. Otherwise the faces are rendered with a hard edge between them. 
		//An empty faceSmoothingGroups implies all faces in this mesh have a smoothing group value of 1.
		//If faceSmoothingGroups is not empty, its size must be equal to number of faces. 
		
		//normalizeMesh(mesh);
		MeshView meshView = new MeshView(mesh);
		meshView.setDrawMode(DrawMode.LINE);
		//meshView.setMaterial(new PhongMaterial(Color.ALICEBLUE));
		

		
		meshView.setScaleX(50*2);
		meshView.setScaleZ(15*2);
		meshView.setScaleY(15*2);
		meshView.setTranslateX(X);
		meshView.setTranslateY(Y);
		meshView.setTranslateZ(Z);
		meshView.setMaterial(material);
		
		
		return meshView;
		
	}

	

	public static void main(String[] args) {
		System.out.println("hello");
		launch(args);
	}
}