package agua;
import java.awt.AWTException;

import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

// pass a triangle mesh for animation?
@SuppressWarnings("restriction")
public class movingWater extends Application {

	private int seed = 2121;
	private int scale = 100;  // the highest y point on the mesh
	private int[] cds = {200,scale,200};
	private float noiseLevel = (float) .28;

	
	@Override
	public void start(Stage mainStage) throws Exception {
	createScene sceneSetup = new createScene();
	cameraSettings cameraSetup = new cameraSettings();
	cameraSetup.cameraY = 150;
	cameraSetup.cameraX = -150;
	PerspectiveCamera camera = cameraSetup.cameraSettings();

	
	Group sceneGroup = new Group();
	sceneGroup.getChildren().add(camera);
	Scene scene = new Scene(sceneGroup, 1280, 720);
	scene.setCamera(camera);
	scene.setFill(Color.IVORY);
	
	generateTerrain testPlot = new generateTerrain();
	float[][] coords = testPlot.generateCoordinates(cds[0], cds[1], cds[2], scale, noiseLevel, seed); //int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed
	TriangleMesh mesh = testPlot.generateTerrain(cds[0], scale, coords);

	// Now we have everything setup to test animation
	// using the simplex noise algorithm and math to create animation (?)
	// http://staffwww.itn.liu.se/~stegu/simplexnoise/simplexnoise.pdf
	
	
	// modify points and a face (which would draw them in real time)
	Interpolator test = Interpolator.EASE_BOTH;
	for(int i= 0; i < cds[0]; i++)
	{
		for(int ii = 0; i < cds[0]; i++)
		{
			test.interpolate(coords[i][ii], , fraction) // I have no idea
		}
	}
	
	
	// the drawing part
	
	PhongMaterial material = new PhongMaterial();
    material.setDiffuseColor(Color.GAINSBORO); // gainsboro
    MeshView meshView = new MeshView(mesh);
	meshView.setDrawMode(DrawMode.LINE);
	meshView.setScaleX(scale/10);
	meshView.setScaleZ(scale/10);
	meshView.setScaleY(scale);
	meshView.setTranslateX(0);
	meshView.setTranslateY(0);
	meshView.setTranslateZ(0);
	meshView.setMaterial(material);
	
	sceneGroup.getChildren().add(meshView);
	mainStage.setScene(scene);
	mainStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}