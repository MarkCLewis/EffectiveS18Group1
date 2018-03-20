package agua;
import java.awt.AWTException;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

// pass a triangle mesh for animation?
@SuppressWarnings("restriction")
public class movingWater extends Application {

	private int seed = 2121;
	private int scale = 100;  // the highest y point on the mesh
	private int[] cds = {200,scale,200};
	private float noiseLevel = (float) .28;
	private MeshView currMesh;
	
	@Override
	public void start(Stage mainStage) throws Exception {
	createScene sceneSetup = new createScene();
	cameraSettings cameraSetup = new cameraSettings();
	//cameraSetup.cameraY = 50;
	//cameraSetup.cameraX = -150;
	PerspectiveCamera camera = cameraSetup.cameraSettings();

	
	Group sceneGroup = new Group();
	sceneGroup.getChildren().add(camera);
	Scene scene = new Scene(sceneGroup, 1280, 720);
	scene.setFill(Color.CORNSILK);
	
	generateTerrain testPlot = new generateTerrain();
	float[][] coords = testPlot.generateCoordinates(cds[0], cds[1], cds[2], scale, noiseLevel, seed); //int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed
	TriangleMesh mesh = testPlot.generateTerrain(cds[0], scale, coords);
	float[][] nextCoords;

	// Now we have everything setup to test animation
	
	createAnimationAssets();
	wave.play();
//	new WavePulseEngine() 
//	{
//
//	}.start();            // Rest of start() method code regarding Stage and Scene objects is in here }
	

	
	
	// using the simplex noise algorithm and math to create animation (?)
	// http://staffwww.itn.liu.se/~stegu/simplexnoise/simplexnoise.pdf
	
	// get the highest y value in the coordinate array (the highest point)
	//float tallest = getHighest(coords);
	/**
	 *  * The face smoothing group value is represented by an array of bits and up
     * to 32 unique groups is possible; (1 << 0) to (1 << 31). The face
     * smoothing group value can range from 0 (no smoothing group) to all 32
     * groups. A face can belong to zero or more smoothing groups. A face is a
     * member of group N if bit N is set, for example, groups |= (1 << N). A
     * value of 0 implies no smoothing group or hard edges. Smoothing is applied
     * when adjacent pair of faces shared a smoothing group. Otherwise the faces
     * are rendered with a hard edge between them.
	 */
	ObservableIntegerArray smoothGroups = mesh.getFaceSmoothingGroups();
	for(int i = 0; i < smoothGroups.size(); i++)
	{
		smoothGroups.set(i, ((1 << 4) | (1 << 14) | (1 << 31)));
		//smoothGroups.set(i, ((0 << 1) | (0 << 14) | (0 << 31)));
	}
	
	//mesh.getFaceSmoothingGroups().addAll(smoothGroups);
//	
	// modify points and a face (which would draw them in real time)
//	Interpolator test = Interpolator.EASE_BOTH;
//	for(int i= 0; i < cds[0]; i++)
//	{
//		for(int ii = 0; i < cds[0]; i++)
//		{
//			//test.interpolate(coords[i][ii], , fraction) // I have no idea
//		}
//	}
	
	
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
	scene.setCamera(camera);
	
	// the drawing part
	
	AmbientLight ambience = new AmbientLight(); // default color white
    ambience.setLightOn(true); // switch it off and everything is black
	sceneGroup.getChildren().add(ambience);
	
	PhongMaterial material = new PhongMaterial();
    material.setDiffuseColor(Color.AQUAMARINE); // gainsboro
    MeshView meshView = new MeshView(mesh);
	meshView.setDrawMode(DrawMode.LINE);
	meshView.setScaleX(scale/10);
	meshView.setScaleZ(scale/10);
	meshView.setScaleY(scale);
	meshView.setTranslateX(0);
	meshView.setTranslateY(0);
	meshView.setTranslateZ(0);
	meshView.setMaterial(material);
	
	this.currMesh = meshView;
	
	sceneGroup.getChildren().add(meshView);
	mainStage.setScene(scene);
	mainStage.show();
	}
	
	
	
	public void generateKeyFrames(float[][] coordinates)
	{
		// get the generated simplex noise and generate the relevant KeyFrames for creating an animation on the timeline
	}
	
	RotateTransition wave;
	private void createAnimationAssets() 
	{
		wave = new RotateTransition(Duration.seconds(5), currMesh);
		wave.setAxis(Rotate.Y_AXIS);
		wave.setCycleCount(1);
		wave.setRate(0.5);
		wave.setInterpolator(Interpolator.LINEAR);
		wave.setFromAngle(45);
		wave.setToAngle(1125);
		wave.play(); 
	}
	
	
	public float getHighest(float[][] coordinates)
	{
		float highest = 0;
		for(int i = 0; i < cds[0]; i++)
			for(int z = 0; z < cds[2]; z++)
			{
				if(coordinates[i][z] > highest)
					highest = coordinates[i][z];
			}
		return highest;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}