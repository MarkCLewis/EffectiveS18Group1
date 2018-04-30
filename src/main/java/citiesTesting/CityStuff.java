package citiesTesting;

import java.util.ArrayList;

import graphicsTesting.DrawFacade;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

public class CityStuff {
	
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
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		lst.add(DrawFacade.getBoxBuilder(20, 20, 20).transCoords(x, y, z).material(mat).build().get());
		
		/*
		MeshView p = Shapes.makePyramid(20, 40, roof1, roof2, x, y - 30, z);
		mg.getChildren().add(p);
		*/
		mat = new PhongMaterial();
		mat.setDiffuseColor(roof1);
		mat.setSpecularColor(roof2);
		lst.add(DrawFacade.getPyramidBuilder(20, 40).transCoords(x, y-30, z).material(mat).build().get());

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
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		lst.add(DrawFacade.getBoxBuilder(20, 20, 20).transCoords(x, y, z).material(mat).build().get());
		
		mat = new PhongMaterial();
		mat.setDiffuseColor(roof1);
		mat.setSpecularColor(roof2);
		lst.add(DrawFacade.getCylinderBuilder(20, 15).transCoords(x, y-18, z).material(mat).build().get());
		
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
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		lst.add(DrawFacade.getCylinderBuilder(10, 100).transCoords(x, y, z).material(mat).build().get());

		lst.add(DrawFacade.getCylinderBuilder(10, 100).transCoords(x+50, y, z).material(mat).build().get());
		
		//The two columns of the gate
		
		mat = new PhongMaterial();
		mat.setDiffuseColor(rColor1);
		mat.setSpecularColor(rColor2);
		lst.add(DrawFacade.getBoxBuilder(100, 20, 20).transCoords(x+25, y-50, z).material(mat).build().get());
		//top of the gate

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
	
	/*
	public static void makeCone(Group mg, Color color1, Color color2, double x, double y, double z, int w){
		
		for(int i = 0; i < 7; i++){
			mg.getChildren().add(Shapes.makeCylinder(w, 100, color1, color2, x, y, z));
			x += 15.0;
			y -= 100.0;
			w -= 50;
		}
	}
	*/
	
	public static ArrayList<Shape3D> makeObelisk(Group mg, Color color1, Color color2, double x, double y, double z){
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(color1);
		mat.setSpecularColor(color2);
		
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		lst.add((DrawFacade.getBoxBuilder(15, 150, 15).transCoords(x, y, z).material(mat).build().get()));
		//tower part^
		
		lst.add(DrawFacade.getPyramidBuilder(15, 15).transCoords(x, y-90, z).material(mat).build().get());
		
		return lst;
		/*
		Box b = Shapes.makeBox(15, 150, 15, color1, color2, x, y, z);
		mg.getChildren().add(b);

		MeshView py = Shapes.makePyramid(15, 15, color1, color2, x, y-90.0, z);
		mg.getChildren().add(py);
		*/
	}
	
	//One of the objects for the center of a town
	public static ArrayList<Shape3D> makeSpiral(Group mg, Color color1, Color color2, Color color3, Color color4, double x, double y, double z){
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(color1);
		mat.setSpecularColor(color2);
		ArrayList<Shape3D> lst = new ArrayList<Shape3D>();
		
		lst.add(DrawFacade.getCylinderBuilder(10, 150).transCoords(x, y, z).material(mat).build().get());
		
		mat = new PhongMaterial();
		mat.setDiffuseColor(color3);
		mat.setSpecularColor(color4);
		
		lst.add(DrawFacade.getPyramidBuilder(30, 60).transCoords(x, y-90, z).material(mat).build().get());
		/*
		MeshView p = Shapes.makePyramid(30, 60, color3, color4, x, y-100, z);
		mg.getChildren().add(p);
		
		Cylinder cyl = Shapes.makeCylinder(10, 150, color1,color2, x, y, z); 
		mg.getChildren().add(cyl);
		*/
		
		double temp = y-60.0;
		for(int i = 0; i < 7; i++){
			lst.add(DrawFacade.getCylinderBuilder(20, 10).transCoords(x, temp, z).material(mat).build().get());
			//mg.getChildren().add(Shapes.makeCylinder(20, 10, color3, color4, x, temp, z));
			temp += 20;
		}
		return lst;
	}
}
