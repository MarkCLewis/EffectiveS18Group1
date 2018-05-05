package citiesTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import citiesTesting.CityMaker.Tuple;
import graphicsTesting.DrawFacade;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import virtualworld.WorldObject;

public class BuildingTypes{
	
	//City type 1: square city
	public static Tuple<Double, ArrayList<Shape3D>> makeCity1(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z, Random r){
		//Random rand = new Random();
		
		int rows = r.nextInt(16)+4;
		//int cols = r.nextInt(16)+4;
		int cols = rows;
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		//columns = rows, which makes it easier to compute size
		//TODO-make default size the size of the city including fences
		
		double tempX = x;
		double tempZ = z;
		
		double size = 0.0;
		
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j ++){
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, tempX, y, tempZ));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, tempX, y, tempZ));
				}
				tempX += 50.0;
			}
			tempX = x;
			tempZ -= 50.0;
		}
				
		//Makes fences around the city
		//TODO-check to see if it works
		if(r.nextInt(3)+1 == 1) {
			lst.addAll(CityStuff.makeFences(mg, rows*47, cols*47, BuildingTypes.colorAssignment(r), BuildingTypes.colorAssignment(r), x, y, tempZ));
		}
		
		size = Math.pow(((rows*50.0) + 20.0), 2);
		//This size accounts for possible fences
		
		//Makes a big gate in front of the city
		if(r.nextInt(6)+1 == 1 && rows > 5 && cols > 5){
			Color cColor1 = BuildingTypes.colorAssignment(r);
			Color rColor1 = BuildingTypes.colorAssignment(r);
			lst.addAll(CityStuff.makeGate(mg, cColor1, BuildingTypes.secondaryColor(cColor1), rColor1, BuildingTypes.secondaryColor(rColor1), x + ((rows*47)/2), y, tempZ - 60));
			lst.addAll(CityStuff.makeGate(mg, cColor1, BuildingTypes.secondaryColor(cColor1), rColor1, BuildingTypes.secondaryColor(rColor1), x + ((rows*47)/2), y, tempZ + (cols*47)+60));
			//makes 2 gates as entrances for the cities
		}
		if(r.nextInt(9)+1 == 1){
			lst.addAll(CityStuff.makeTemple(mg, roof2, roof1, house2, house2, x + ((rows*47)/2), y, tempZ + (cols*47)+70));
		}
		
		Tuple<Double, ArrayList<Shape3D>> tup = new Tuple<Double, ArrayList<Shape3D>>(size, lst);
		
		return tup;
	}
	
	//City type 2: Circular city
	//TODO
	public static Tuple<Double, ArrayList<Shape3D>> makeCity2(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z, int num, Random r){
		//Random rand = new Random();
		
		//double tempX = x;
		//double tempY = y;
		//double tempZ = z;
		
		int layers = r.nextInt(5)+3;
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		//Making the central object
		if(num == 1){
			lst.addAll(CityStuff.makeObelisk(mg, BuildingTypes.colorAssignment(r), BuildingTypes.colorAssignment(r), x, y-60, z));
		}
		else if(num == 2){
			Color color1 = BuildingTypes.colorAssignment(r);
			Color color2 = BuildingTypes.colorAssignment(r);
			lst.addAll(CityStuff.makeSpiral(mg, color1, BuildingTypes.secondaryColor(color1), color2, BuildingTypes.secondaryColor(color2), x, y-60, z));
		}
		
		double size = Math.pow(((50.0 * layers) * 2), 2);

		for (int i = 1; i < layers; i++){
			double radius = 50 * i;
			//TODO-more radius distance testing
			
			////make north house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z+radius));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z+radius));
			}

			////make NE house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45))));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z+(radius*Math.sin(45))));
			}

			
			////make east house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x+radius, y, z));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x+radius, y, z));
			}
			
			////make SE house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius*Math.sin(45))));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius * Math.sin(45))));
			}
			
			////make south house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z-radius));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z-radius));
			}
			
			////make SW house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius*Math.sin(45))));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius * Math.sin(45))));
			}
			
			////make west house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x-radius, y, z));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x-radius, y, z));
			}
			
			/////make NW house////
			if(r.nextInt(2)+1 == 1){
				lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z + (radius*Math.sin(45))));
			}
			else{
				lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z + (radius * Math.sin(45))));
			}
			
		}
		Tuple<Double, ArrayList<Shape3D>> tup = new Tuple<Double, ArrayList<Shape3D>>(size, lst);
		
		return tup;
		
	}
	
	//City Type 3: Diamond City
	//TODO
	public static Tuple<Double, ArrayList<Shape3D>> makeCity3(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z, int num, Random r){
		int layers = r.nextInt(5)+3;
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		//Making the central object
		//TODO-make different central objects for this city type
		if(num == 1){
			lst.addAll(CityStuff.makeObelisk(mg, BuildingTypes.colorAssignment(r), BuildingTypes.colorAssignment(r), x, y-60, z));
		}
		else if(num == 2){
			Color color1 = BuildingTypes.colorAssignment(r);
			Color color2 = BuildingTypes.colorAssignment(r);
			lst.addAll(CityStuff.makeSpiral(mg, color1, BuildingTypes.secondaryColor(color1), color2, BuildingTypes.secondaryColor(color2), x, y-60, z));
		}
		
		double size = Math.pow(((50.0 * layers) * 2), 2);
		
		for(int i = 1; i < layers; i ++){
			double radius = 50 * i;
			if(i % 2 == 0){
				////make north house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z+radius));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z+radius));
				}
				
				////make east house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x+radius, y, z));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x+radius, y, z));
				}
				
				////make south house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z-radius));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z-radius));
				}
				
				////make west house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x-radius, y, z));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x-radius, y, z));
				}
				
			}
			else {
				////make NE house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45))));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z+(radius*Math.sin(45))));
				}

				////make SE house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius*Math.sin(45))));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius * Math.sin(45))));
				}
				
				////make SW house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius*Math.sin(45))));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius * Math.sin(45))));
				}
				
				/////make NW house////
				if(r.nextInt(2)+1 == 1){
					lst.addAll(CityStuff.makeHouse1(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z + (radius*Math.sin(45))));
				}
				else{
					lst.addAll(CityStuff.makeHouse2(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z + (radius * Math.sin(45))));
				}
			}
		}
		Tuple<Double, ArrayList<Shape3D>> tup = new Tuple<Double, ArrayList<Shape3D>>(size, lst);
		
		return tup;
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
	static class Tuple<A, B> {
		public final A size;
		public final B lst;
		
		Tuple(A size, B lst){
			this.size = size;
			this.lst = lst;
		}
		
		A getA(){
			return size;
		}
		B getB(){
			return lst;
		}
	}

}
