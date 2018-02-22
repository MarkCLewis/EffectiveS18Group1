package citiesTesting;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.Cylinder;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;

public class ShapesDemo extends Application{
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		/*
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(camera);
		mainGroup.getChildren().add(cameraGroup);
		*/
/*
		Cylinder cyl = makeCylinder(200, 100, Color.LIGHTBLUE, Color.BLUE, 350, 300, 200);
		mainGroup.getChildren().add(cyl);

		//Cylinder cyl2 = makeCylinder(200, 100, Color.RED, Color.DARKRED, 900, 300, 200);
		//mainGroup.getChildren().add(cyl2);

		Cylinder cyl2 = makeCylinder(200, 100, Color.RED, Color.DARKRED, 250, 350, 0);
		mainGroup.getChildren().add(cyl2);
*/	
		int myX = 1000;
		int myY = 100;
		int myZ = 600;
		for(int i = 0; i < 6; i++){
				//Cylinder cyl = makeCylinder(200, 100, Color.SANDYBROWN, Color.BROWN, myX, myY, myZ);
				Cylinder cyl = makeCylinder(200, 100, Color.LIGHTBLUE, Color.BLUE, myX, myY, myZ);
				mainGroup.getChildren().add(cyl);
				myX -= 50;
				myY += 50;
				myZ -= 200;
		}
	
		myX = 500;
		myY = 100;
		myZ = 600;
		for(int i = 0; i < 6; i++){
				//Cylinder cyl = makeCylinder(200, 100, Color.SANDYBROWN, Color.BROWN, myX, myY, myZ);
				Cylinder cyl = makeCylinder(200, 100, Color.LIGHTBLUE, Color.BLUE, myX, myY, myZ);
				mainGroup.getChildren().add(cyl);
				myX -= 50;
				myY += 50;
				myZ -= 200;
		}
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	
	public Cylinder makeCylinder(int w, int h, Color color1, Color color2, int x, int y, int z){
		Cylinder cylinder = new Cylinder(w,h);
		PhongMaterial blueStuff = new PhongMaterial();
		blueStuff.setDiffuseColor(color1);
		blueStuff.setSpecularColor(color2);
		cylinder.setMaterial(blueStuff);
		
		cylinder.setTranslateX(x);
		cylinder.setTranslateY(y);
		cylinder.setTranslateZ(z);
		return cylinder;
		//mainGroup.getChildren().add(cylinder);
	}
	
	/*
	public Group createBuildings(Group grp){
		//Group bg = new Group();
		
		return bg;
		
	}
	*/

}
