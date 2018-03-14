package citiesTesting;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class CityMaker extends Application{
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		
		Random rand = new Random();
		int a = rand.nextInt(10)+1;
		//int b = rand.nextInt(10)+1;
		//TODO-use random int values to create random cities
		//one for how many buildings, and one for what type of building
		
		//TODO-n.b. do not change y-values
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public void makeHouse1(Group mg, Color roof1, Color roof2, Color house1, Color house2, int x, int y, int z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		MeshView p = Shapes.makePyramid(100, 250, roof1, roof2, x, y - 150, z);
		mg.getChildren().add(p);
	}
	
	public void makeHouse2(Group mg, Color roof1, Color roof2, Color house1, Color house2, int x, int y, int z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		Cylinder c = Shapes.makeCylinder(100, 50, roof1, roof2, x, y-75, z);
		mg.getChildren().add(c);
	}
	
	public void makeGate(Group mg, Color cColor1, Color cColor2, Color rColor1, Color rColor2, int x, int y, int z){
		//height = 500;
		Cylinder cyl = Shapes.makeCylinder(50, 500, cColor1, cColor2, x, y, z); 
		mg.getChildren().add(cyl);
		Cylinder cyl2 = Shapes.makeCylinder(50, 500, cColor1, cColor2, x + 350, y, z);
		mg.getChildren().add(cyl2);
		
		//TODO-need to make 3d rectangle
		//This one is a placeholder
		Box b = Shapes.makeBox(500, 75, 75, rColor1, rColor2, x+175, y-285, z);
		mg.getChildren().add(b);
		
	}
	
	//TODO-fix fences
	//x, y, and z should be the x, y, z values of the bottom left part of the city
	//Makes a fence around a city given the city's length and width
	public void makeFences(Group mg, int l, int w, Color color1, Color color2, int x, int y, int z){
		int newL = l + 200;
		int newW = w + 200;
			
		int newX = x - 100;
		int newZ = z - 100;
		
		//make front fences
		while(newX <= (x-100) + newL){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newX += 100;
		}
			
		//make left fences
		while(newZ <= (z-100) + newW){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newZ += 100;
		}
			
		//make back fences
		while(newX >= (x-100)){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newX -= 100;
		}
			
		//make right fences
		while(newZ >= (z-100)){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newZ -= 100;
		}
	}
	
	////////////////////////////////
	//TODO-Central items for cities:
	
	
	public void makeCentralCone(Group mg, Color color1, Color color2, int x, int y, int z, int w){
		/*
		int tempX = 500;
		int tempY = 600;
		int tempW = 400;
		*/
		for(int i = 0; i < 7; i++){
			mg.getChildren().add(Shapes.makeCylinder(w, 100, color1, color2, x, y, z));
			x += 15;
			y -= 100;
			w -= 50;
		}
	}
	
	//TODO-One of the objects for the center of a town
	public void makeCentralSpiral(Group mg, Color color1, Color color2, Color color3, Color color4, int x, int y, int z){
		MeshView p = Shapes.makePyramid(100, 250, color3, color4, x, y-350, z);
		mg.getChildren().add(p);
		
		Cylinder cyl = Shapes.makeCylinder(50, 500, color1,color2, x, y, z); 
		mg.getChildren().add(cyl);
		
		int temp = y - 200;
		for(int i = 0; i < 9; i++){
			mg.getChildren().add(Shapes.makeCylinder(100, 25, color3, color4, x, temp, z));
			temp += 50;
		}
	}
	
	
	protected boolean isInWater(Box box, float posX, float posY){		
		return false;
	}
	
}
