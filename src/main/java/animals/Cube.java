package animals;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Cube extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create a MeshView
		MeshView meshView = this.createMeshView();
		meshView.setTranslateX(250);
		meshView.setTranslateY(100);
		meshView.setTranslateZ(400);

		// Scale the Meshview to make it look bigger
		meshView.setScaleX(10.0);
		meshView.setScaleY(10.0);
		meshView.setScaleZ(10.0);

		// Create a Camera to view the 3D Shapes
		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(100);
		camera.setTranslateY(-50);
		camera.setTranslateZ(300);

		// Create the red Front Light
		PointLight redLight = new PointLight();
		redLight.setColor(Color.RED);
		redLight.setTranslateX(250);
		redLight.setTranslateY(150);
		redLight.setTranslateZ(300);

		// Create the green Back Light
		PointLight greenLight = new PointLight();
		greenLight.setColor(Color.GREEN);
		greenLight.setTranslateX(200);
		greenLight.setTranslateY(150);
		greenLight.setTranslateZ(450);

		// Add the Shapes and the Light to the Group
		Group root = new Group(meshView, redLight, greenLight);
		// Rotate the triangle with its lights to 90 degrees
		root.setRotationAxis(Rotate.Y_AXIS);
		root.setRotate(90);

		// Create a Scene with depth buffer enabled
		Scene scene = new Scene(root, 600, 600, true);

        scene.setCamera(camera);
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("An Example using a TriangleMesh");
		//Camera Movement
				double camSpeed = 10.0;
				scene.setOnKeyPressed(event ->{
					KeyCode key = event.getCode();
					 if(key == KeyCode.Q) {camera.setTranslateZ(camera.getTranslateZ() + camSpeed);}
					 if(key == KeyCode.E) {camera.setTranslateZ(camera.getTranslateZ() - camSpeed);}
					 if(key == KeyCode.W) {camera.setTranslateY(camera.getTranslateY() + camSpeed);}
					 if(key == KeyCode.S) {camera.setTranslateY(camera.getTranslateY() - camSpeed);}
					 if(key == KeyCode.A) {camera.setTranslateX(camera.getTranslateX() + camSpeed);}
					 if(key == KeyCode.D) {camera.setTranslateX(camera.getTranslateX() - camSpeed);}
					 if(key == KeyCode.C) {camera.setRotate(camera.getRotate() + camSpeed);}
					 if(key == KeyCode.Z) {camera.setRotate(camera.getRotate() - camSpeed);}
				});
		// Display the Stage
		stage.show();
	}

	public MeshView createMeshView()
	{
		float h = 150;                    // Height
		float s = 300; 					 // Side
		float[] points =
		{
			 0,    0,    0,            // Point 0 - Top
		     0,    h,    -s/2,         // Point 1 - Front
		     -s/2, h,    0,            // Point 2 - Left
		     s/2,  h,    0,            // Point 3 - Back
		     0,    h,    s/2           // Point 4 - Right
		};

		float[] texCoords =
		{
		   10,0
		};

		int[] faces =
		{
	        0,0,  2,0,  1,0,          // Front left face
	        0,0,  1,0,  3,0,          // Front right face
	        0,0,  3,0,  4,0,          // Back right face
	        0,0,  4,0,  2,0,          // Back left face
	        4,0,  1,0,  2,0,          // Bottom rear face
	        4,0,  3,0,  1,0           // Bottom front face
		};

		// Create a TriangleMesh
		TriangleMesh mesh = new TriangleMesh();
		mesh.getPoints().addAll(points);
		mesh.getTexCoords().addAll(texCoords);
		mesh.getFaces().addAll(faces);

		// Create a MeshView
		MeshView meshView = new MeshView();
		meshView.setMesh(mesh);

		return meshView;
	}
}