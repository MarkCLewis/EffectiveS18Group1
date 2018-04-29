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
	public static double makeCity1(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z, Random r){
		//Random rand = new Random();
		
		int rows = r.nextInt(16)+4;
		//int cols = r.nextInt(16)+4;
		int cols = rows;
		
		//columns = rows, which makes it easier to compute size
		//TODO-make default size the size of the city including fences
		
		double tempX = x;
		//double tempY = y;
		double tempZ = z;
		
		double size = 0.0;
		
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j ++){
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, tempX, y, tempZ);
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, tempX, y, tempZ);
				}
				tempX += 50.0;
			}
			tempX = x;
			tempZ -= 50.0;
		}
				
		//Makes fences around the city
		//TODO-check to see if it works
		if(r.nextInt(3)+1 == 1) {
			BuildingTypes.makeFences(mg, rows*47, cols*47, BuildingTypes.colorAssignment(r), BuildingTypes.colorAssignment(r), x, y, tempZ);
		}
		
		size = Math.pow(((rows*50.0) + 20.0), 2);
		//This size accounts for possible fences
		
		//Makes a big gate in front of the city
		if(r.nextInt(6)+1 == 1 && rows > 5 && cols > 5){
			Color cColor1 = BuildingTypes.colorAssignment(r);
			Color rColor1 = BuildingTypes.colorAssignment(r);
			BuildingTypes.makeGate(mg, cColor1, BuildingTypes.secondaryColor(cColor1), rColor1, BuildingTypes.secondaryColor(rColor1), x + ((rows*47)/2), y, tempZ - 60);
			BuildingTypes.makeGate(mg, cColor1, BuildingTypes.secondaryColor(cColor1), rColor1, BuildingTypes.secondaryColor(rColor1), x + ((rows*47)/2), y, tempZ + (cols*47)+60);
			//might take out ^ the second gate
			//makes 2 gates as entrances for the cities
		}
		if(r.nextInt(9)+1 == 1){
			BuildingTypes.makeTemple(mg, roof2, roof1, house2, house2, x + ((rows*47)/2), y, tempZ + (cols*47)+70);
		}
		
		return size;
	}
	
	//City type 2: Circular city
	//TODO
	public static double makeCity2(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z, int num, Random r){
		//Random rand = new Random();
		
		//double tempX = x;
		//double tempY = y;
		//double tempZ = z;
		
		int layers = r.nextInt(5)+3;
		
		//Making the central object
		if(num == 1){
			makeObelisk(mg, BuildingTypes.colorAssignment(r), BuildingTypes.colorAssignment(r), x, y-60, z);
		}
		else if(num == 2){
			Color color1 = BuildingTypes.colorAssignment(r);
			Color color2 = BuildingTypes.colorAssignment(r);
			makeSpiral(mg, color1, BuildingTypes.secondaryColor(color1), color2, BuildingTypes.secondaryColor(color2), x, y-60, z);
		}
		
		double size = Math.pow(((50.0 * layers) * 2), 2);

		for (int i = 1; i < layers; i++){
			double radius = 50 * i;
			//TODO-more radius distance testing
			
			////make north house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z+radius);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z+radius);
			}

			////make NE house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z+(radius*Math.sin(45)));
			}

			
			////make east house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x+radius, y, z);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x+radius, y, z);
			}
			
			////make SE house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius * Math.sin(45)));
			}
			
			////make south house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z-radius);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z-radius);
			}
			
			////make SW house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius * Math.sin(45)));
			}
			
			////make west house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x-radius, y, z);
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x-radius, y, z);
			}
			
			/////make NW house////
			if(r.nextInt(2)+1 == 1){
				BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45)));
			}
			else{
				BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius * Math.sin(45)));
			}
			
		}
		return size;
		
	}
	
	//City Type 3: Diamond City
	//TODO
	public static double makeCity3(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z, int num, Random r){
		int layers = r.nextInt(5)+3;
		
		//Making the central object
		//TODO-make different central objects for this city type
		if(num == 1){
			makeObelisk(mg, BuildingTypes.colorAssignment(r), BuildingTypes.colorAssignment(r), x, y-60, z);
		}
		else if(num == 2){
			Color color1 = BuildingTypes.colorAssignment(r);
			Color color2 = BuildingTypes.colorAssignment(r);
			makeSpiral(mg, color1, BuildingTypes.secondaryColor(color1), color2, BuildingTypes.secondaryColor(color2), x, y-60, z);
		}
		
		double size = Math.pow(((50.0 * layers) * 2), 2);
		
		for(int i = 1; i < layers; i ++){
			double radius = 50 * i;
			if(i % 2 == 0){
				////make north house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z+radius);
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z+radius);
				}
				
				////make east house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x+radius, y, z);
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x+radius, y, z);
				}
				
				////make south house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x, y, z-radius);
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x, y, z-radius);
				}
				
				////make west house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x-radius, y, z);
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x-radius, y, z);
				}
				
			}
			else {
				////make NE house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45)));
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z+(radius*Math.sin(45)));
				}

				////make SE house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius*Math.sin(45)));
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z - (radius * Math.sin(45)));
				}
				
				////make SW house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius*Math.sin(45)));
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x - (radius*Math.cos(45)), y, z - (radius * Math.sin(45)));
				}
				
				/////make NW house////
				if(r.nextInt(2)+1 == 1){
					BuildingTypes.makeHouse1(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius*Math.sin(45)));
				}
				else{
					BuildingTypes.makeHouse2(mg, roof1, roof2, house1, house2, x + (radius*Math.cos(45)), y, z + (radius * Math.sin(45)));
				}
			}
		}
		return size;
	}
	
	public static ArrayList<Shape3D> makeHouse1(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z) {
		/*
		Box b = Shapes.makeBox(20, 20, 20, house1, house2, x, y, z);
		
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);
		*/
		
		//mainGroup.getChildren().add(DrawFacade.getCylinderBuilder(10,20).transCoords(10,0,0).material(new PhongMaterial(Color.BLUE)).build().get());
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(house1);
		mat.setSpecularColor(house2);
		Box b = (DrawFacade.getBoxBuilder(20, 20, 20).transCoords(x, y, z).material(mat).build().get());
		
		/*
		MeshView p = Shapes.makePyramid(20, 40, roof1, roof2, x, y - 30, z);
		//MeshView p = Shapes.makePyramid(100, 250, roof1, roof2, x, y - 150, z);
		mg.getChildren().add(p);
		*/
		mat = new PhongMaterial();
		mat.setDiffuseColor(roof1);
		mat.setSpecularColor(roof2);
		MeshView p = (DrawFacade.getPyramidBuilder(100, 250).transCoords(x, y-150, z).material(mat).build().get());
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		lst.add(b);
		lst.add(p);
		return lst;
		
	}


	public static ArrayList<Shape3D> makeHouse2(Group mg, Color roof1, Color roof2, Color house1, Color house2, double x, double y, double z) {
		//Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		/*
		Box b = Shapes.makeBox(20, 20, 20, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);
		*/
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(house1);
		mat.setSpecularColor(house2);
		Box b = (DrawFacade.getBoxBuilder(20, 20, 20).transCoords(x, y, z).material(mat).build().get());
		
		mat = new PhongMaterial();
		mat.setDiffuseColor(roof1);
		mat.setSpecularColor(roof2);
		Cylinder c = DrawFacade.getCylinderBuilder(20, 15).transCoords(x, y-18, z).material(mat).build().get();
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		lst.add(b);
		lst.add(c);
		/*
		Cylinder c = Shapes.makeCylinder(20, 15, roof1, roof2, x, y-18, z);
		mg.getChildren().add(c);
		*/
		return lst;
	}
	
	
	
	public static ArrayList<Shape3D> makeGate(Group mg, Color cColor1, Color cColor2, Color rColor1, Color rColor2, double x, double y, double z){

		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(cColor1);
		mat.setSpecularColor(cColor2);
		Cylinder c1 = DrawFacade.getCylinderBuilder(10, 100).transCoords(x, y, z).material(mat).build().get();

		Cylinder c2 = DrawFacade.getCylinderBuilder(10, 100).transCoords(x+50, y, z).material(mat).build().get();
		
		mat = new PhongMaterial();
		mat.setDiffuseColor(rColor1);
		mat.setSpecularColor(rColor2);
		Box b = (DrawFacade.getBoxBuilder(100, 20, 20).transCoords(x+25, y-50, z).material(mat).build().get());
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		lst.add(c1);
		lst.add(c2);
		lst.add(b);
		
		return lst;
		/*
		Cylinder cyl = Shapes.makeCylinder(10, 100, cColor1, cColor2, x, y, z); 
		mg.getChildren().add(cyl);
		Cylinder cyl2 = Shapes.makeCylinder(10, 100, cColor1, cColor2, x + 50, y, z);
		mg.getChildren().add(cyl2);

		Box b = Shapes.makeBox(100, 20, 20, rColor1, rColor2, x+25, y-50, z);
		mg.getChildren().add(b);
		*/
	}
	
	//x, y, and z should be the x, y, z values of the bottom left part of the city
	//Makes a fence around a city given the city's length and width
	public static ArrayList<Shape3D> makeFences(Group mg, double l, double w, Color color1, Color color2, double x, double y, double z){
		double newL = l + 20.0; //was 200.0
		double newW = w + 20.0; //was 200.0
			
		double newX = x - 10.0; //was x - 100.0
		double newZ = z - 10.0; //was z - 100.0
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(color1);
		mat.setSpecularColor(color2);
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		//make front fences
		while(newX <= (x-10.0) + newL){
			//mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			lst.add(DrawFacade.getCylinderBuilder(5, 50).transCoords(newX, y, newZ).material(mat).build().get());
			newX += 30.0;
		}
			
		//make right fences
		while(newZ <= (z-10.0) + newW){
			//mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			lst.add(DrawFacade.getCylinderBuilder(5, 50).transCoords(newX, y, newZ).material(mat).build().get());
			newZ += 30.0;
		}
			
		//make back fences
		while(newX >= (x-10.0)){
			//mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			lst.add(DrawFacade.getCylinderBuilder(5, 50).transCoords(newX, y, newZ).material(mat).build().get());
			newX -= 30.0;
		}
			
		//make left fences
		while(newZ >= (z-10.0)){
			//mg.getChildren().add(Shapes.makeCylinder(5, 50, color1, color2, newX, y, newZ));
			lst.add(DrawFacade.getCylinderBuilder(5, 50).transCoords(newX, y, newZ).material(mat).build().get());
			newZ -= 30.0;
		}
		
		return lst;
	}

	////////////////////////////////
	//Items outside cities
	public static ArrayList<Shape3D> makeTemple(Group mg, Color roof1, Color roof2, Color cols1, Color cols2, double x, double y, double z){
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(roof1);
		mat.setSpecularColor(roof2);
		lst.add((DrawFacade.getBoxBuilder(80, 5, 40).transCoords(x, y, z-500).material(mat).build().get()));
		//base of the temple ^
		
		/*
		Box b1 = Shapes.makeBox(80.0, 5.0, 40.0, roof1, roof2, x, y, -500);
		mg.getChildren().add(b1);
		*/
		
		
		double newX = x-25;
		double newZ = z;
		
		mat = new PhongMaterial();
		mat.setDiffuseColor(cols1);
		mat.setSpecularColor(cols2);
		for(int i = 0; i < 2; i++){
			lst.add(DrawFacade.getCylinderBuilder(5, 40).transCoords(newX, y-20, newZ).material(mat).build().get());
			lst.add(DrawFacade.getCylinderBuilder(5, 40).transCoords(newX+50, y-20, newZ).material(mat).build().get());
			/*
			Cylinder cyl = Shapes.makeCylinder(5.0, 40.0, cols1, cols2, newX, y-20, newZ); 
			mg.getChildren().add(cyl);
		
			Cylinder cyl1 = Shapes.makeCylinder(5.0, 40.0, cols1, cols2, newX+50, y-20, newZ); 
			mg.getChildren().add(cyl1);
			*/
			newZ += 80.0;
			
		}
		
		while(newZ <= z){
			newZ += 20.0;
			lst.add(DrawFacade.getCylinderBuilder(5, 40).transCoords(newX, y-20, newZ).material(mat).build().get());
			lst.add(DrawFacade.getCylinderBuilder(5, 40).transCoords(newX+50, y-20, newZ).material(mat).build().get());
			/*
			Cylinder cyl = Shapes.makeCylinder(5.0, 40.0, cols1, cols2, newX, 220, newZ); 
			mg.getChildren().add(cyl);
		
			Cylinder cyl1 = Shapes.makeCylinder(5.0, 40.0, cols1, cols2, newX+50, 220, newZ); 
			mg.getChildren().add(cyl1);
			*/
		}
		/*
		Box b2 = Shapes.makeBox(80.0, 5.0, 40.0, roof1, roof2, x, y-40, z);
		mg.getChildren().add(b2);
		*/
		mat = new PhongMaterial();
		mat.setDiffuseColor(roof1);
		mat.setSpecularColor(roof2);
		lst.add((DrawFacade.getBoxBuilder(80, 5, 40).transCoords(x, y-40, z).material(mat).build().get()));
		
		double l = 80.0;
		double w = 40.0;
		double newY = y-40;
		for(int i = 0; i < 5; i++){
			newY -= 5.0;
			l-=10.0;
			w-=10.0;
			/*
			Box b3 = Shapes.makeBox(l, 5.0, w, roof1, roof2, newX+25.0, newY, newZ);
			mg.getChildren().add(b3);
			*/
			lst.add((DrawFacade.getBoxBuilder(l, 5, w).transCoords(newX+25, newY, newZ).material(mat).build().get()));
		}
		return lst;
	}
	
	
	
	////////////////////////////////
	//Central items for cities:
	
	
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
	
	//One of the objects for the center of a town
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
	class Tuple<A, B> {
		public final A size;
		public final B lst;
		
		Tuple(A size, B lst){
			this.size = size;
			this.lst = lst;
		}
		
		A getSize(){
			return size;
		}
		B getLst(){
			return lst;
		}
	}

}
