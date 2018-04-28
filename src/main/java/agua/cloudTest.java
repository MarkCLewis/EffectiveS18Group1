package agua;
import java.util.Vector;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import javafx.util.Duration;


// I cleaned this up. 
// THIS IS A DEMONSTRATION OF AN ANIMATED WAVE POOL
// THIS IS NOT USABLE FOR EXTENDING TO ANYTHING


@SuppressWarnings("restriction")
public class cloudTest extends Application {

	private int seed = 2121;
	private int scale = 50;  
	private int[] cds = {50,scale,50};
	private float noiseLevel = (float) .20;
	private MeshView currMesh;
	private Vector<cloudTransition> Transitions = new Vector<cloudTransition>(10);
	//@SuppressWarnings("unchecked")
	@Override
	public void start(Stage mainStage) throws Exception {
		
	createScene sceneSetup = new createScene();
	cameraSettings cameraSetup = new cameraSettings();
	PerspectiveCamera camera = cameraSetup.cameraSettings();

	
	Group sceneGroup = new Group();
	sceneGroup.getChildren().add(camera);
	Scene scene = new Scene(sceneGroup, 1280, 720);
	scene.setFill(Color.CORNSILK);
	
	int[] tempArraySizes = {20, 30, 40, 50};
	cloudFactory cloudFac = new cloudFactory(tempArraySizes, scale, 10, 50, 100, seed);
	//cloud testCloud = cloudFac.formCloud(100);
	
	
	
	Vector<cloud> atmosphere = cloudFac.createClouds(4, tempArraySizes);
	
	
	scene.setOnKeyPressed(event-> {
		double change = cameraSetup.cameraQuantity;
		if(event.isShiftDown()) { change = cameraSetup.cameraModifier+250;}
		KeyCode key = event.getCode();
		if(key == KeyCode.I) 
		{
			int i = 0;
			for(cloud c : atmosphere)
			{
				//c.calcNormals();
				
				waveTransition wv = new waveTransition(c.getMeshViewer(), c.getStartMesh(), c.getEndMesh(), 60, 10000);
				waveTransition backwardsWV = new waveTransition(c.getMeshViewer(), c.getEndMesh(), c.getStartMesh(), 60, 10000);
				//wv.setOnFinished(e -> {wv.pause(); backwardsWV.play();});
				//backwardsWV.setOnFinished(e -> {backwardsWV.pause();wv.play();});
				wv.setAutoReverse(true);
				wv.setCycleCount(wv.INDEFINITE);
				//Duration start = new Duration(10000/2); // change this to a variable later
				//wv.playFrom(start);
				i++;
				MeshView tempViewer = c.createViewer();
				c.getMeshViewer().setDrawMode(DrawMode.LINE);
				c.makeVisible();
				//c.moveTo(randomPoint());
				sceneGroup.getChildren().add(tempViewer);
				//c.getTransition().play();
				c.waveT = wv;
				c.backT = backwardsWV;

			}
		}
		if(key == KeyCode.U) {
			for(cloud c : atmosphere)
			{
				cloudTransition ct = c.getTransition();

				//c.waveT.play();
				//ct.play();
				ParallelTransition pt = new ParallelTransition(c.waveT, ct); // should properly go between the two now (setonfinished ^ )
				pt.play();
			}
			
		}
		if(key == KeyCode.Q) {System.exit(0);}
		if(key == KeyCode.W) {camera.setTranslateZ(camera.getTranslateZ() + change);}
		if(key == KeyCode.S) {camera.setTranslateZ(camera.getTranslateZ() - change);}
		if(key == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change);}
		if(key == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change);}
		if(key == KeyCode.Z) { camera.setTranslateY(camera.getTranslateY() + change);}
		if(key == KeyCode.X) { camera.setTranslateY(camera.getTranslateY() - change);}
		});
	scene.setCamera(camera);
	
	AmbientLight ambience = new AmbientLight(); // default color white
    ambience.setLightOn(false); // switch it off and everything is black
	
  PointLight pointLight = new PointLight(Color.WHITE);
  pointLight.setTranslateX(0);
  pointLight.setTranslateY(0);
  pointLight.setTranslateZ(0);
  pointLight.setRotate(camera.getRotate());
  pointLight.maxHeight(1000);
  pointLight.minHeight(-100);
  sceneGroup.getChildren().add(pointLight);
    
    
    sceneGroup.getChildren().add(ambience);
	//sceneGroup.getChildren().add(meshView);
	mainStage.setScene(scene);
	//transTest.play();
	
	mainStage.show();
	}
	public Point3D randomPoint()
	{
		Point3D rando = new Point3D(Math.random()*(250 - 10)+45,Math.random()*(170 - 5)+45,Math.random()*(430 - 22)+45);
		return rando;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	}
