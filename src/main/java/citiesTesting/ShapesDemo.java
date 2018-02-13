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

		Cylinder cyl = makeCylinder(200, 100);
		mainGroup.getChildren().add(cyl);

		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public Cylinder makeCylinder(int h, int w){
		Cylinder cylinder = new Cylinder(h,w);
		PhongMaterial blueStuff = new PhongMaterial();
		blueStuff.setDiffuseColor(Color.LIGHTBLUE);
		blueStuff.setSpecularColor(Color.BLUE);
		cylinder.setMaterial(blueStuff);
		
		cylinder.setTranslateX(350);
		cylinder.setTranslateY(300);
		cylinder.setTranslateZ(100);
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
