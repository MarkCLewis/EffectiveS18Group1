package citiesTesting;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import virtualworld.WorldObject;

public class BuildingTypes implements WorldObject {
	//City type 1: square city
	public static void makeCity1(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z){
		Random rand = new Random();
		
		int rows = rand.nextInt(16)+4;
		int cols = rand.nextInt(16)+4;
		
		double tempX = x;
		//double tempY = y;
		double tempZ = z;
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j ++){
				if(rand.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, tempX, y, tempZ);
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, tempX, y, tempZ);
				}
				tempX += 50.0; //was 200.0
			}
			tempX = x;
			tempZ -= 50.0; //was 200.0
		}
		
		//Makes fences around the city
		//TODO-check to see if it works
		if(rand.nextInt(3)+1 == 1) {
			BuildingTypes.makeFences(mg, rows*47, cols*47, BuildingTypes.colorAssignment(rand), BuildingTypes.colorAssignment(rand), x, y, tempZ);
		}
		
		//Makes a big gate in front of the city
		if(rand.nextInt(6)+1 == 1){
			Color cColor1 = BuildingTypes.colorAssignment(rand);
			Color rColor1 = BuildingTypes.colorAssignment(rand);
			BuildingTypes.makeGate(mg, cColor1, BuildingTypes.secondaryColor(cColor1), rColor1, BuildingTypes.secondaryColor(rColor1), x + ((rows*47)/2), y, tempZ - 60);
			BuildingTypes.makeGate(mg, cColor1, BuildingTypes.secondaryColor(cColor1), rColor1, BuildingTypes.secondaryColor(rColor1), x + ((rows*47)/2), y, tempZ + (cols*47)+60);
			//might take out ^ the second gate
			//makes 2 gates as entrances for the cities
		}
	}
	
	//City type 2: Circular city
	//TODO
	public static void makeCity2(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z){
		Random rand = new Random();
		
		//double tempX = x;
		//double tempY = y;
		//double tempZ = z;
		
		int layers = rand.nextInt(5)+3;
		
		//Making the central object
		if(rand.nextInt(2)+1 == 1){
			makeObelisk(mg, BuildingTypes.colorAssignment(rand), BuildingTypes.colorAssignment(rand), x, y-60, z);
			//TODO-check why the y value is messed up
		}
		else {
			if(rand.nextInt(2)+1 == 1){
				Color color1 = BuildingTypes.colorAssignment(rand);
				Color color2 = BuildingTypes.colorAssignment(rand);
				makeSpiral(mg, color1, BuildingTypes.secondaryColor(color1), color2, BuildingTypes.secondaryColor(color2), x, y-60, z);
				//TODO-check why the y value is messed up
			}
		}

		for (int i = 0; i < layers; i++){
			double radius = 40 * i;
			//TODO-more radius distance testing
			
			////make north house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z+radius);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z+radius);
			}

			////make NE house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z+(radius*Math.sin(45)));
			}

			
			////make east house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x+radius, y, z);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x+radius, y, z);
			}
			
			////make SE house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius * Math.sin(45)));
			}
			
			////make south house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z-radius);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z-radius);
			}
			
			////make SW house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius * Math.sin(45)));
			}
			
			////make west house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x-radius, y, z);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x-radius, y, z);
			}
			
			/////make NW house////
			if(rand.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius * Math.sin(45)));
			}
			
		}
		
		
	}
	
	//City Type 3: Diamond City
	//TODO
	
	public static void makeHouse1(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z) {
		Box b = Shapes.makeBox(28, 28, 28, house1, house2, x, y, z);
		//Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);
		
		MeshView p = Shapes.makePyramid(28, 55, roof1, roof2, x, y - 40, z);
		//MeshView p = Shapes.makePyramid(100, 250, roof1, roof2, x, y - 150, z);
		mg.getChildren().add(p);
	}
	
	public static void makeHouse2(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z) {
		//Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		Box b = Shapes.makeBox(28, 28, 28, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		Cylinder c = Shapes.makeCylinder(30, 15, roof1, roof2, x, y-20, z);
		//Cylinder c = Shapes.makeCylinder(100, 50, roof1, roof2, x, y-75, z);
		mg.getChildren().add(c);
	}
	
	
	
	public static void makeGate(Group mg, Color cColor1, Color cColor2, Color rColor1, Color rColor2, double x, double y, double z){

		Cylinder cyl = Shapes.makeCylinder(10, 100, cColor1, cColor2, x, y, z); 
		//Cylinder cyl = Shapes.makeCylinder(50, 500, cColor1, cColor2, x, y, z); 
		mg.getChildren().add(cyl);
		Cylinder cyl2 = Shapes.makeCylinder(10, 100, cColor1, cColor2, x + 50, y, z);
		//Cylinder cyl2 = Shapes.makeCylinder(50, 500, cColor1, cColor2, x + 350, y, z);
		mg.getChildren().add(cyl2);

		Box b = Shapes.makeBox(100, 20, 20, rColor1, rColor2, x+25, y-50, z);
		//Box b = Shapes.makeBox(500, 75, 75, rColor1, rColor2, x+175, y-285, z);
		mg.getChildren().add(b);
		
	}
	
	//TODO-fix fences
	//x, y, and z should be the x, y, z values of the bottom left part of the city
	//Makes a fence around a city given the city's length and width
	public static void makeFences(Group mg, double l, double w, Color color1, Color color2, double x, double y, double z){
		double newL = l + 20.0; //was 200.0
		double newW = w + 20.0; //was 200.0
			
		double newX = x - 10.0; //was x - 100.0
		double newZ = z - 10.0; //was z - 100.0
		
		
		//make front fences
		while(newX <= (x-10.0) + newL){
			mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			newX += 30.0;
		}
			
		//make right fences
		while(newZ <= (z-10.0) + newW){
			mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			newZ += 30.0;
		}
			
		//make back fences
		while(newX >= (x-10.0)){
			mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			newX -= 30.0;
		}
			
		//make left fences
		while(newZ >= (z-10.0)){
			mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			newZ -= 30.0;
		}
	}
	/*
	public static void makeRect(Group mg, double l, double h, Color color1, Color color2, double x, double y, double z){
		double tmpX = x;
		double tmpY = y;
		if(l > h){
			double len = l / h;
			for(int i = 0; i < len; i++){
				mg.getChildren().add(Shapes.makeBox(h, h, h, color1, color2, tmpX, y, z));
				tmpX += 100;
			}
		}
		else if (l < h){
			double height = h / l;
			for(int i = 0; i < height; i ++){
				mg.getChildren().add(Shapes.makeBox(l, l, l, color1, color2, x, tmpY, z));
				tmpY -= 100;
			}
		}
	}
	*/
	
	////////////////////////////////
	//TODO-Central items for cities:
	
	
	public static void makeCone(Group mg, Color color1, Color color2, double x, double y, double z, int w){
		for(int i = 0; i < 7; i++){
			mg.getChildren().add(Shapes.makeCylinder(w, 100, color1, color2, x, y, z));
			x += 15.0;
			y -= 100.0;
			w -= 50;
		}
	}
	
	public static void makeObelisk(Group mg, Color color1, Color color2, double x, double y, double z){
		//makeRect(mg, 15, 100, color1, color1, x, y, z);
		Box b = Shapes.makeBox(15, 150, 15, color1, color2, x, y, z);
		mg.getChildren().add(b);
		//makeRect(mg, 100, 500, color1, color1, x, y, z);
		MeshView py = Shapes.makePyramid(15, 15, color1, color2, x, y-90.0, z);
		mg.getChildren().add(py);
	}
	
	//TODO-One of the objects for the center of a town
	public static void makeSpiral(Group mg, Color color1, Color color2, Color color3, Color color4, double x, double y, double z){
		MeshView p = Shapes.makePyramid(30, 60, color3, color4, x, y-100, z);
		mg.getChildren().add(p);
		
		Cylinder cyl = Shapes.makeCylinder(10, 150, color1,color2, x, y, z); 
		mg.getChildren().add(cyl);
		
		double temp = y-60.0;
		for(int i = 0; i < 7; i++){
			mg.getChildren().add(Shapes.makeCylinder(20, 10, color3, color4, x, temp, z));
			temp += 20;
		}
	}


	//Generates a random color
	public static Color colorAssignment(Random rand) {
		int color = rand.nextInt(7)+1;
		//Color item = null;
		
		if(color == 1){
			return Color.RED;
		}
		else if(color == 2){
			return Color.ORANGE;
		}
		else if(color == 3){
			return Color.GOLD;
		}
		else if(color == 4){
			return Color.LIMEGREEN;
		}
		else if(color == 5){
			return Color.LIGHTBLUE;
		}
		else if(color == 6){
			return Color.LIGHTPINK;
		}
		else{
			return Color.BLUEVIOLET;
		}
		//return item;
		
	}

	//Gives a secondary color based on the first color
	public static Color secondaryColor(Color item){
		if(item == Color.RED){
			return Color.DARKRED;
		}
		else if(item == Color.ORANGE){
			return Color.ORANGERED;
		}
		else if(item == Color.GOLD){
			return Color.DARKGOLDENROD;
		}
		else if(item == Color.LIMEGREEN){
			return Color.SEAGREEN;
		}
		else if(item == Color.LIGHTBLUE){
			return Color.CADETBLUE;
		}
		else if(item == Color.LIGHTPINK){
			return Color.DEEPPINK;
		}
		else{
			return Color.PURPLE;
		}
		//return item;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
