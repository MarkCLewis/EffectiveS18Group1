package citiesTesting;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import java.util.Set;

public class BuildingTypes {
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
				tempX += 200.0;
			}
			tempX = x;
			tempZ -= 200.0;
		}
		
		//Makes fences around the city
		//TODO-check to see if it works
		if(rand.nextInt(4)+1 == 1) {
			BuildingTypes.makeFences(mg, rows*200, cols*200, BuildingTypes.colorAssignment(rand), BuildingTypes.colorAssignment(rand), x-100.0, y, tempZ-100.0);
		}
		
		//Makes a big gate in front of the city
		if(rand.nextInt(10)+1 == 1){
			Color cColor1 = BuildingTypes.colorAssignment(rand);
			Color rColor1 = BuildingTypes.colorAssignment(rand);
			BuildingTypes.makeGate(mg, cColor1, BuildingTypes.secondaryColod(cColor1), rColor1, BuildingTypes.secondaryColod(rColor1), x + ((cols*200)/2), y, tempZ - 400);
		}
	}
	
	//City type 2: Circular city
	//TODO
	public static void makeCity2(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z){
		Random rand = new Random();
		
		double tempX = x;
		double tempY = y;
		double tempZ = z;
		
		if(rand.nextInt(2)+1 == 1){
			
		}
	}
	
	
	public static void makeHouse1(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		MeshView p = Shapes.makePyramid(100, 250, roof1, roof2, x, y - 150, z);
		mg.getChildren().add(p);
	}
	
	public static void makeHouse2(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		Cylinder c = Shapes.makeCylinder(100, 50, roof1, roof2, x, y-75, z);
		mg.getChildren().add(c);
	}
	
	public static void makeGate(Group mg, Color cColor1, Color cColor2, Color rColor1, Color rColor2, double x, double y, double z){
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
	public static void makeFences(Group mg, int l, int w, Color color1, Color color2, double x, double y, double z){
		int newL = l + 200;
		int newW = w + 200;
			
		double newX = x - 100.0;
		double newZ = z - 100.0;
		
		//make front fences
		while(newX <= (x-100.0) + newL){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newX += 100.0;
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
	
	public static void makeRect(Group mg, int l, int h, Color color1, Color color2, double x, double y, double z){
		double tmpX = x;
		double tmpY = y;
		if(l > h){
			int len = l / h;
			for(int i = 0; i < len; i++){
				mg.getChildren().add(Shapes.makeBox(h, h, h, color1, color2, tmpX, y, z));
				tmpX += 100;
			}
		}
		else if (l < h){
			int height = h / l;
			for(int i = 0; i < height; i ++){
				mg.getChildren().add(Shapes.makeBox(l, l, l, color1, color2, x, tmpY, z));
				tmpY -= 100;
			}
		}
	}
	
	////////////////////////////////
	//TODO-Central items for cities:
	
	
	public static void makeCone(Group mg, Color color1, Color color2, double x, double y, double z, int w){
		/*
		int tempX = 500;
		int tempY = 600;
		int tempW = 400;
		*/
		for(int i = 0; i < 7; i++){
			mg.getChildren().add(Shapes.makeCylinder(w, 100, color1, color2, x, y, z));
			x += 15.0;
			y -= 100.0;
			w -= 50;
		}
	}
	
	public static void makeObelisk(Group mg, Color color1, Color color2, double x, double y, double z){
		makeRect(mg, 100, 500, color1, color1, x, y, z);
		MeshView py = Shapes.makePyramid(200, 100, color2, color2, x, y-650.0, z);
		mg.getChildren().add(py);
	}
	
	//TODO-One of the objects for the center of a town
	public static void makeSpiral(Group mg, Color color1, Color color2, Color color3, Color color4, double x, double y, double z){
		MeshView p = Shapes.makePyramid(100, 250, color3, color4, x, y-350, z);
		mg.getChildren().add(p);
		
		Cylinder cyl = Shapes.makeCylinder(50, 500, color1,color2, x, y, z); 
		mg.getChildren().add(cyl);
		
		double temp = y - 200.0;
		for(int i = 0; i < 9; i++){
			mg.getChildren().add(Shapes.makeCylinder(100, 25, color3, color4, x, temp, z));
			temp += 50.0;
		}
	}


	//Generates a random color
	public static Color colorAssignment(Random rand) {
		int color = rand.nextInt(7)+1;
		Color item = null;
		
		if(color == 1){
			item = Color.RED;
		}
		else if(color == 2){
			item = Color.ORANGE;
		}
		else if(color == 3){
			item = Color.GOLD;
		}
		else if(color == 4){
			item = Color.LIMEGREEN;
		}
		else if(color == 5){
			item = Color.LIGHTBLUE;
		}
		else if(color == 6){
			item = Color.LIGHTPINK;
		}
		else{
			item = Color.BLUEVIOLET;
		}
		return item;
		
	}

	//Gives a secondary color based on the first color
	public static Color secondaryColod(Color item){
		if(item == Color.RED){
			item = Color.DARKRED;
		}
		else if(item == Color.ORANGE){
			item = Color.ORANGERED;
		}
		else if(item == Color.GOLD){
			item = Color.DARKGOLDENROD;
		}
		else if(item == Color.LIMEGREEN){
			item = Color.SEAGREEN;
		}
		else if(item == Color.LIGHTBLUE){
			item = Color.CADETBLUE;
		}
		else if(item == Color.LIGHTPINK){
			item = Color.DEEPPINK;
		}
		else{
			item = Color.PURPLE;
		}
		return item;
	}
	
	/*
	public static void multColorAssignment(Color item1, Color item2, Random rand) {
		// TODO Auto-generated method stub
			int color = rand.nextInt(7)+1;
			
			if(color == 1){
				item1 = Color.RED;
				item2 = Color.DARKRED;
			}
			else if(color == 2){
				item1 = Color.ORANGE;
				item2 = Color.ORANGERED;
			}
			else if(color == 3){
				item1 = Color.GOLD;
				item2 = Color.DARKGOLDENROD;
			}
			else if(color == 4){
				item1 = Color.LIMEGREEN;
				item2 = Color.SEAGREEN;
			}
			else if(color == 5){
				item1 = Color.LIGHTBLUE;
				item2 = Color.CADETBLUE;
			}
			else if(color == 6){
				item1 = Color.LIGHTPINK;
				item2 = Color.DEEPPINK;
			}
			else{
				item1 = Color.BLUEVIOLET;
				item2 = Color.PURPLE;
			}
			
	}
	*/

}
