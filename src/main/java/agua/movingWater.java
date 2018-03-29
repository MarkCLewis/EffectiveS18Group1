package agua;
import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Vector;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.beans.value.*;
import javafx.scene.shape.Sphere;

// pass a triangle mesh for animation?
@SuppressWarnings("restriction")
public class movingWater extends Application {

	private int seed = 2121;
	private int scale = 100;  // the highest y point on the mesh
	private int[] cds = {200,scale,200};
	private float noiseLevel = (float) .35;
	private MeshView currMesh;
	//remove
	public static KeyValue kv;
	
	//@SuppressWarnings("unchecked")
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
	
	float[][] endCoords = testPlot.generateCoordinates(cds[0], cds[1], cds[2], scale, noiseLevel, seed+45);;
	
	TriangleMesh mesh = testPlot.generateTerrain(cds[0], scale, coords);
	
	 Pair<ObservableFloatArray, ObservableIntegerArray> meshValues = new  Pair<ObservableFloatArray, ObservableIntegerArray>(mesh.getPoints(), mesh.getFaces());
	//TriangleMesh testMesh = testPlot.generateTerrainValues(cds[0], scale, coords);
	
	 writableMeshFaces testF = new writableMeshFaces(mesh);
	 writableMeshPoints testP = new writableMeshPoints(mesh); 
	 
	ObservableIntegerArray smoothGroups = mesh.getFaceSmoothingGroups();
	
	for(int i = 0; i < smoothGroups.size(); i++)
	{
		smoothGroups.set(i, ((1 << 4) | (1 << 14) | (1 << 31)));
		//smoothGroups.set(i, ((0 << 1) | (0 << 14) | (0 << 31)));
	}
		
	mesh.getFaceSmoothingGroups().setAll(smoothGroups);
	MeshView meshView = new MeshView(mesh);

	//meshView.getLocalToSceneTransform()
	Transform meshTransform = meshView.getLocalToSceneTransform();
	
	// get the highest point
//	Point3D highestPoint = getHighest(coords);
//	// create an array(list) of point3d from the coordinates
	ArrayList<Point3D> points = new ArrayList<Point3D>();
//	ArrayList<Point3D> endPoints = new ArrayList<Point3D>();
//	//ArrayList<Rotate> rotates = new ArrayList<Rotate>();
//	ArrayList<Transform> moveZ = new ArrayList<Transform>();
//	// the two as a pair
//	//ArrayList<Pair<Point3D, Rotate>> rInfo = new ArrayList<Pair<Point3D, Rotate>>();
//	ArrayList<Pair<Point3D, Transform>> tInfo = new ArrayList<Pair<Point3D, Transform>>();
	TriangleMesh endMesh = testPlot.generateTerrain(cds[0], scale/2, endCoords);
	MeshView endMV = new MeshView(endMesh);
	
	PhongMaterial material = new PhongMaterial();
    material.setDiffuseColor(Color.BLACK); // gainsboro
    
	meshView.setDrawMode(DrawMode.LINE);
	meshView.setScaleX(scale/10);
	meshView.setScaleZ(scale/10);
	meshView.setScaleY(scale);
	meshView.setTranslateX(0);
	meshView.setTranslateY(0);
	meshView.setTranslateZ(0);
	meshView.setMaterial(material);
	
	endMV.setDrawMode(DrawMode.LINE);
	endMV.setScaleX(scale/10);
	endMV.setScaleZ(scale/10);
	endMV.setScaleY(scale);
	endMV.setTranslateX(0);
	endMV.setTranslateY(0);
	endMV.setTranslateZ(0);
	endMV.setMaterial(material);
	
	TranslateTransition hmm = new TranslateTransition(Duration.seconds(10), meshView);
	
	
	Mesh t = endMV.meshProperty().get();
	//meshView.meshProperty().get();
	KeyValue kvMesh = new KeyValue(meshView.meshProperty(), endMV.meshProperty().get(), Interpolator.LINEAR);
	KeyValue kvFace = new KeyValue(testF, endMesh.getFaces(), Interpolator.EASE_BOTH);
	KeyValue kvPoints = new KeyValue(testP, endMesh.getPoints(), Interpolator.EASE_BOTH);
	 final Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		final KeyFrame fkf = new KeyFrame(Duration.seconds(1), kvFace);
		final KeyFrame pkf = new KeyFrame(Duration.seconds(1), kvPoints);
		KeyFrame kfMesh = new KeyFrame(Duration.seconds(1), kvMesh);
		timeline.getKeyFrames().addAll(kfMesh);
		
	ObservableFloatArray test = mesh.getPoints(); 
	ObservableFloatArray other = endMesh.getPoints();
	System.out.println(test.get(5) +"\n");
	System.out.println(other.get(5) + "\n");
	

	
	
	for(int i=0; i<cds[0]; i++)
	{
		for(int z=0; z<cds[1]; z++)
		{
			
			Point3D tempPoint = new Point3D(i, coords[i][z], z);
			Point3D zPoint = new Point3D(i, coords[i][z], -z); // endpoint with a different (opposite) z value
			points.add(tempPoint);
//			endPoints.add(zPoint);
			
			// testing out my implemented WritableValue for meshfaces
			
			
			//KeyValue kv = new KeyValue(, zPoint.getZ(), Interpolator.EASE_BOTH);
			//Rotate tRotate = new Rotate(tempPoint.angle(highestPoint), tempPoint);
			//rotates.add(tRotate);
			// adding these together as a pairing (more organized, maybe faster (?))
			//tInfo.add(new Pair<Point3D, Transform>(tempPoint, tRotate));
		}
	}

	

	
	// now use the previously created transform on each point3d
	
//	rInfo.stream().forEach(
//			tPair -> {
//				Point3D trashPoint = tPair.getKey();
//				Rotate trashRotate = tPair.getValue();
//				Point3D endPoint = trashRotate.(trashPoint);
//				
//				endPoints.add(endPoint);
//				//RotateTransition rt = new RotateTransition(Duration.seconds(3);
//				}
//	);
//	
//	endPoints.stream(
//			ePoint -> {
//				ePoint.
//			}
//	testPlot.generateTerrain(cds[0], scale, )
	
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
	
	AmbientLight ambience = new AmbientLight(); // default color white
    ambience.setLightOn(true); // switch it off and everything is black
    
	scene.setOnKeyPressed(event-> {
		double change = cameraSetup.cameraQuantity;
		if(event.isShiftDown()) { change = cameraSetup.cameraModifier;}
		KeyCode key = event.getCode();
		
		if(key == KeyCode.P)
		{
			mesh.getPoints().clear();
			mesh.getFaces().clear();
			
			mesh.getPoints().setAll(endMesh.getPoints());
			mesh.getFaces().setAll(endMesh.getFaces());
			
		}
		if(key == KeyCode.L)
		{
			mesh.getPoints().clear();
			mesh.getFaces().clear();
			
			mesh.getPoints().setAll(mesh.getPoints());
			mesh.getFaces().setAll(mesh.getFaces());
			
		}
		if(key == KeyCode.F) {meshView.setDrawMode(DrawMode.LINE);}
		if(key == KeyCode.G) {meshView.setDrawMode(DrawMode.FILL);}
		if(key == KeyCode.I){ambience.setLightOn(true);}
		if(key == KeyCode.K){ambience.setLightOn(false);}
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
	
	
	sceneGroup.getChildren().add(ambience);
	
	
	
	
	this.currMesh = meshView;
	
	PointLight pointLight = new PointLight(Color.GHOSTWHITE);
    pointLight.setTranslateX(0);
    pointLight.setTranslateY(0);
    pointLight.setTranslateZ(0);
    pointLight.setRotate(90);
    //sceneGroup.getChildren().add(pointLight);
    
	sceneGroup.getChildren().add(meshView);
	mainStage.setScene(scene);
	timeline.play();
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
	
	
	public Point3D getHighest(float[][] coordinates)
	{
		Point3D highest = new Point3D(0, 0, 0);
		for(int i = 0; i < cds[0]; i++)
			for(int z = 0; z < cds[2]; z++)
			{
				if(coordinates[i][z] > highest.getY())
					highest = new Point3D(i, coordinates[i][z], z);
			}
		return highest;
		
	}
	
	public int getHighestSpot(float[][] coordinates)
	{
		Point3D highest = new Point3D(0, 0, 0);
		int r = 0;
		for(int i = 0; i < cds[0]; i++)
			for(int z = 0; z < cds[2]; z++)
			{
				if(coordinates[i][z] > highest.getY())
					r = z;
			}
		return r;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}