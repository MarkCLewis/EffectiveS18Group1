package citiesTesting;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

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
		Cylinder cyl = Shapes.makeCylinder(200, 100, Color.LIGHTBLUE, Color.BLUE, 350, 300, 200);
		mainGroup.getChildren().add(cyl);

		Cylinder cyl2 = Shapes.makeCylinder(200, 100, Color.RED, Color.DARKRED, 250, 350, 0);
		mainGroup.getChildren().add(cyl2);
*/	
		/*
		//TODO
		//Demo for cylinder houses
		int myX = 1000;
		int myY = 100;
		int myZ = 600;
		for(int i = 0; i < 6; i++){
				//Cylinder cyl = Shapes.makeCylinder(200, 100, Color.SANDYBROWN, Color.BROWN, myX, myY, myZ);
				Cylinder cyl = Shapes.makeCylinder(200, 100, Color.LIGHTBLUE, Color.BLUE, myX, myY, myZ);
				mainGroup.getChildren().add(cyl);
				myX -= 50;
				myY += 50;
				myZ -= 200;
		}
	
		myX = 500;
		myY = 100;
		myZ = 600;
		for(int i = 0; i < 6; i++){
				//Cylinder cyl = Shapes.makeCylinder(200, 100, Color.SANDYBROWN, Color.BROWN, myX, myY, myZ);
				Cylinder cyl = Shapes.makeCylinder(200, 100, Color.LIGHTBLUE, Color.BLUE, myX, myY, myZ);
				mainGroup.getChildren().add(cyl);
				myX -= 50;
				myY += 50;
				myZ -= 200;
		}
		*/
		
		/*
		Rectangle r = Shapes.makeRectangle(100, 200, 500, 100, Color.LIGHTBLUE, Color.BLUE);
		mainGroup.getChildren().add(r);
		*/

		/*
		Box b = Shapes.makeBox(900, 900, 900, Color.LIGHTBLUE, Color.BLUE, 200, 250, 200);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mainGroup.getChildren().add(b);
		
		
		MeshView p = Shapes.makePyramid(100, 250, Color.PALEVIOLETRED, Color.RED, 200, 100, 200);
		mainGroup.getChildren().add(p);
		*/
		
		/*
		makeHouse(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, 200, 250, 200); //Makes a house structure
		makeHouse(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, 450, 250, 200);
		*/
		/*
		int temp = 200;
		for(int i = 0; i < 4; i++){
			makeHouse(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, temp, 250, 200);
			temp += 250;
		}
		*/
		
		//TODO
		//Demo for basic houses in neighborhood
		
		int tempX = 450;
		int tempY = 200;
		int tempZ = 300;
		for(int i = 0; i < 4; i++){
			makeHouse(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, tempX, tempY, tempZ);
			tempX -= 100;
			tempY += 100;
			tempZ -= 200;
		}
		tempX = 850;
		tempY = 200;
		tempZ = 300;
		for(int i = 0; i < 4; i++){
			makeHouse(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, tempX, tempY, tempZ);
			tempX -= 100;
			tempY += 100;
			tempZ -= 200;
		}
		
		/*
		Cylinder cyl = Shapes.makeCylinder(200, 100, Color.LIGHTBLUE, Color.BLUE, 350, 300, 200);
		mainGroup.getChildren().add(cyl);
		*/
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public void makeHouse(Group mg, Color roof1, Color roof2, Color house1, Color house2, int x, int y, int z){
		Box b = Shapes.makeBox(900, 900, 900, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);
		
		MeshView p = Shapes.makePyramid(100, 250, roof1, roof2, x, y-150, z);
		mg.getChildren().add(p);
		//return temp;
		
	}
	
}
