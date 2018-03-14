package citiesTesting;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

public class BuildingTypes {
	public static void makeHouse1(Group mg, Color roof1, Color roof2, Color house1, Color house2, int x, int y, int z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		MeshView p = Shapes.makePyramid(100, 250, roof1, roof2, x, y - 150, z);
		mg.getChildren().add(p);
	}
	
	public static void makeHouse2(Group mg, Color roof1, Color roof2, Color house1, Color house2, int x, int y, int z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		Cylinder c = Shapes.makeCylinder(100, 50, roof1, roof2, x, y-75, z);
		mg.getChildren().add(c);
	}
	
	public static void makeGate(Group mg, Color cColor1, Color cColor2, Color rColor1, Color rColor2, int x, int y, int z){
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
	public static void makeFences(Group mg, int l, int w, Color color1, Color color2, int x, int y, int z){
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
	
	
	public static void makeCentralCone(Group mg, Color color1, Color color2, int x, int y, int z, int w){
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
	public static void makeCentralSpiral(Group mg, Color color1, Color color2, Color color3, Color color4, int x, int y, int z){
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
}
