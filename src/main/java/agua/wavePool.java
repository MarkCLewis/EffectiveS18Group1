package agua;
import java.util.Vector;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import javafx.util.Duration;


// I cleaned this up. 
// THIS IS A DEMONSTRATION OF AN ANIMATED WAVE POOL
// THIS IS NOT USABLE FOR EXTENDING TO ANYTHING


@SuppressWarnings("restriction")
public class wavePool extends Application {

	private int seed = 2121;
	private int scale = 100;  
	private int[] cds = {100,scale,100};
	private float noiseLevel = (float) .23;
	private MeshView currMesh;

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
	
	generateTerrain testPlot = new generateTerrain();
	float[][] coords = testPlot.generateCoordinates(cds[0], cds[1], cds[2], scale, noiseLevel, seed+21); //int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed
	
	float[][] endCoords = testPlot.generateCoordinates(cds[0], cds[1], cds[2], scale, (float)(noiseLevel+.05), seed+45);;
	
	
	
	TriangleMesh mesh = testPlot.generateTerrain(cds[0], scale, coords);
	TriangleMesh meshTwo = testPlot.generateTerrain(cds[0], scale, coords);
	TriangleMesh endMesh = testPlot.generateTerrain(cds[0], scale, endCoords);
	
	MeshView meshView = new MeshView(mesh);
	meshView.setCullFace(CullFace.FRONT);
	MeshView meshViewTwo = new MeshView(meshTwo);
	meshViewTwo.setCullFace(CullFace.FRONT);

			//int[] sizes, int scale, int minSpeed, int maxSpeed, int minHeight, int seed
	PhongMaterial material = new PhongMaterial(Color.AQUA);
	material.setSpecularColor(Color.AQUA);
	material.setDiffuseColor(Color.AQUAMARINE);
	meshView.setDrawMode(DrawMode.FILL);
	meshView.setScaleX(scale/10);
	meshView.setScaleZ(scale/10);
	meshView.setScaleY(scale*2);
	meshView.setTranslateX(0);
	meshView.setTranslateY(0);
	meshView.setTranslateZ(-500);
	meshView.setMaterial(material);
	
	PhongMaterial materialTwo = new PhongMaterial(Color.DARKRED);
	materialTwo.setSpecularColor(Color.DARKGOLDENROD);
	meshViewTwo.setDrawMode(DrawMode.LINE);
	meshViewTwo.setScaleX(scale/10);
	meshViewTwo.setScaleZ(scale/10);
	meshViewTwo.setScaleY(scale*2);
	meshViewTwo.setTranslateX(0);
	meshViewTwo.setTranslateY(0);
	meshViewTwo.setTranslateZ(-500);
	meshViewTwo.setMaterial(materialTwo);
	
	Lighting lighting = new Lighting();
	PointLight light = new PointLight();
	light.setRotate(90);
	light.setLayoutX(0);
	light.setLayoutY(100);
	
	light.setScaleX(scale);
	light.setScaleY(scale*10);
	light.setScaleZ(scale*10);
	//lighting.setLight(light);
	
	//TranslateTransition hmm = new TranslateTransition(Duration.seconds(10), meshView);
	
	
	waveTransition transTest = new waveTransition(meshView, mesh, endMesh, 60, 5000);
	//waveTransition transBack = new waveTransition(meshView, endMesh, mesh, 60);
	transTest.setRate(3);
	transTest.setAutoReverse(true);
	transTest.setCycleCount(Transition.INDEFINITE);

	

	AmbientLight ambience = new AmbientLight(); // default color white
    ambience.setLightOn(true); // switch it off and everything is black
    
	scene.setOnKeyPressed(event-> {
		double change = cameraSetup.cameraQuantity;
		if(event.isShiftDown()) { change = cameraSetup.cameraModifier+250;}
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
		if(key == KeyCode.O) {meshViewTwo.setScaleX(meshViewTwo.getScaleX()*2);
							  meshViewTwo.setScaleZ(meshViewTwo.getScaleZ()*2);
							  meshViewTwo.setScaleY(meshViewTwo.getScaleY()*2);
		}
		if(key == KeyCode.P) {meshViewTwo.setScaleX(meshViewTwo.getScaleX()/2);
		  meshViewTwo.setScaleZ(meshViewTwo.getScaleZ()/2);
		  meshViewTwo.setScaleY(meshViewTwo.getScaleY()/2);
}
		if(key == KeyCode.C) { sceneGroup.getChildren().add(meshViewTwo);
								sceneGroup.getChildren().remove(meshView);
		}
		if(key == KeyCode.B) { sceneGroup.getChildren().add(meshView);
								sceneGroup.getChildren().remove(meshViewTwo);
		}
		if(key == KeyCode.V) { transTest.play(); }
		if(key == KeyCode.F) {meshView.setDrawMode(DrawMode.LINE);}
		if(key == KeyCode.G) {meshView.setDrawMode(DrawMode.FILL);}

		if(key == KeyCode.Q) {System.exit(0);}
		if(key == KeyCode.W) {camera.setTranslateZ(camera.getTranslateZ() + change);}
		if(key == KeyCode.S) {camera.setTranslateZ(camera.getTranslateZ() - change);}
		if(key == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change);}
		if(key == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change);}
		if(key == KeyCode.Z) { camera.setTranslateY(camera.getTranslateY() + change);}
		if(key == KeyCode.X) { camera.setTranslateY(camera.getTranslateY() - change);}
	});
	scene.setCamera(camera);
	
	
	//this.currMesh = meshView;
	
	PointLight pointLight = new PointLight(Color.WHITE);
    pointLight.setTranslateX(0);
    pointLight.setTranslateY(-50);
    pointLight.setTranslateZ(0);
    pointLight.setRotate(90);
    sceneGroup.getChildren().add(light);
//    sceneGroup.getChildren().add(ambience);
	sceneGroup.getChildren().add(meshView);
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