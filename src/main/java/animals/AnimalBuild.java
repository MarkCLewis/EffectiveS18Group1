package animals;

import java.util.ArrayList;

import graphicsTesting.DrawFacade;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;

public class AnimalBuild {
	
	public static ArrayList<Shape3D> makeSheep(double x, double y, double z) {
		ArrayList<Shape3D> list = new ArrayList<Shape3D>();
		
		//left front leg
		Cylinder c = DrawFacade.getCylinderBuilder(.2, 1).transCoords(x + 1.5, y, z + 0.5).build().get();
		list.add(c);
		
		//right front leg
		Cylinder c2 = DrawFacade.getCylinderBuilder(.2, 1).transCoords(x + 1.5, y, z + 1.5).build().get();
		list.add(c2);
	
		//right back leg
		Cylinder c3 = DrawFacade.getCylinderBuilder(.2, 1).transCoords(x, y, z + 1.5).build().get();	
		list.add(c3);

		//left back leg
		Cylinder c4 = DrawFacade.getCylinderBuilder(.2, 1).transCoords(x, y, z + 0.5).build().get();	
		list.add(c4);
	
		//body
		Box b = DrawFacade.getBoxBuilder(2, 1, 1.5).transCoords(x + 0.75, y + -1,z + 1).build().get();
		list.add(b);
		
		//head
		Box b2 = DrawFacade.getBoxBuilder(.7, .7, .7).transCoords(x + 2, y + -1.75, z + 1).build().get();
		list.add(b2);
		
		return list;
	}
	
	public static ArrayList<Shape3D> makePig(double x, double y, double z) {
		ArrayList<Shape3D> list = new ArrayList<Shape3D>();
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(Color.LIGHTPINK);
		
		//left front leg
		Cylinder c = DrawFacade.getCylinderBuilder(.15, .7).transCoords(x + 1.5, y, z + 0.5).material(mat).build().get();
		list.add(c);
		
		//right front leg
		Cylinder c2 = DrawFacade.getCylinderBuilder(.15, .7).transCoords(x + 1.5, y, z + 1).material(mat).build().get();
		list.add(c2);
	
		//right back leg
		Cylinder c3 = DrawFacade.getCylinderBuilder(.15, .7).transCoords(x, y, z + 1).material(mat).build().get();	
		list.add(c3);

		//left back leg
		Cylinder c4 = DrawFacade.getCylinderBuilder(.15, .7).transCoords(x, y, z + 0.5).material(mat).build().get();	
		list.add(c4);
	
		//body
		Box b = DrawFacade.getBoxBuilder(2, .8, 1).transCoords(x + 0.75, y + -0.7, z + 0.73).material(mat).build().get();
		list.add(b);
		
		//head
		Box b2 = DrawFacade.getBoxBuilder(.6, .6, .6).transCoords(x + 2, y + -1.2, z + 0.73).material(mat).build().get();
		list.add(b2);
		
		//snout
		Box b3 = DrawFacade.getBoxBuilder(.3, .3, .3).transCoords(x + 2.3, y + -1.1, z + 0.73).material(mat).build().get();
		list.add(b3);
		
		return list;
	}
	
	public static ArrayList<Shape3D> makeGiraffe(double x, double y, double z) {
		ArrayList<Shape3D> list = new ArrayList<Shape3D>();
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(Color.YELLOW);
		
		//left front leg
		Cylinder c = DrawFacade.getCylinderBuilder(.18, 2.2).transCoords(x + 1.25, y, z + 0.5).material(mat).build().get();
		list.add(c);
		
		//right front leg
		Cylinder c2 = DrawFacade.getCylinderBuilder(.18, 2.2).transCoords(x + 1.25, y, z + 1).material(mat).build().get();
		list.add(c2);
	
		//right back leg
		Cylinder c3 = DrawFacade.getCylinderBuilder(.18, 2.2).transCoords(x, y, z + 1).material(mat).build().get();	
		list.add(c3);

		//left back leg
		Cylinder c4 = DrawFacade.getCylinderBuilder(.18, 2.2).transCoords(x, y, z + 0.5).material(mat).build().get();	
		list.add(c4);
		
		//left antennae
		Cylinder c5 = DrawFacade.getCylinderBuilder(.06, 0.2).transCoords(x + 1.25, y - 3.3, z + 0.65).material(mat).build().get();	
		list.add(c5);
				
		//right antennae
		Cylinder c6 = DrawFacade.getCylinderBuilder(.06, 0.2).transCoords(x + 1.25, y - 3.3, z + 0.84).material(mat).build().get();	
		list.add(c6);
	
		//body
		Box b = DrawFacade.getBoxBuilder(1.75, 0.8, 1).transCoords(x + 0.63, y - 0.7, z + 0.73).material(mat).build().get();
		list.add(b);
		
		//neck
		Box b2 = DrawFacade.getBoxBuilder(0.3, 1.75, 0.3).transCoords(x + 1.25, y - 2, z + 0.73).material(mat).build().get();
		list.add(b2);
		
		//head
		Box b3 = DrawFacade.getBoxBuilder(0.7, 0.4, 0.4).transCoords(x + 1.4, y - 3, z + 0.73).material(mat).build().get();
		list.add(b3);
		
		return list;

	}
	
	public static ArrayList<Shape3D> makeDuck(double x, double y, double z) {
		ArrayList<Shape3D> list = new ArrayList<Shape3D>();
		
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseColor(Color.YELLOW);
		
		PhongMaterial beak = new PhongMaterial();
		beak.setDiffuseColor(Color.ORANGE);
		
		//right back leg
		Cylinder c = DrawFacade.getCylinderBuilder(.07, .4).transCoords(x, y, z + 0.85).material(mat).build().get();	
		list.add(c);

		//left back leg
		Cylinder c1 = DrawFacade.getCylinderBuilder(.07, .4).transCoords(x, y, z + 0.55).material(mat).build().get();	
		list.add(c1);
		
		//body
		Box b = DrawFacade.getBoxBuilder(0.5, 0.5, 0.5).transCoords(x + 0.1, y - 0.4, z + 0.7).material(mat).build().get();
		list.add(b);
		
		//head
		Box b2 = DrawFacade.getBoxBuilder(0.3, 0.5, 0.3).transCoords(x + 0.2, y - 0.8, z + 0.7).material(mat).build().get();
		list.add(b2);
	
		//left foot
		Box b3 = DrawFacade.getBoxBuilder(0.3, 0.1, 0.2).transCoords(x + 0.06, y + 0.2, z + 0.55).material(mat).build().get();
		list.add(b3);
		
		//right foot
		Box b4 = DrawFacade.getBoxBuilder(0.3, 0.1, 0.2).transCoords(x + 0.06, y + 0.2, z + 0.85).material(mat).build().get();
		list.add(b4);
		
		//top beak
		Box b5 = DrawFacade.getBoxBuilder(0.25, 0.08, 0.2).transCoords(x + 0.45, y - 0.75, z + 0.71).material(beak).build().get();
		list.add(b5);
		
		//bottom beak
		Box b6 = DrawFacade.getBoxBuilder(0.25, 0.08, 0.2).transCoords(x + 0.45, y - 0.85, z + 0.71).material(beak).build().get();
		list.add(b6);
		
		//right wing
		Box b7 = DrawFacade.getBoxBuilder(0.4, 0.3, 0.08).transCoords(x - 0.1, y - 0.35, z + 1).material(mat).build().get();
		list.add(b7);
		
		//left wing
		Box b8 = DrawFacade.getBoxBuilder(0.4, 0.3, 0.08).transCoords(x - 0.1, y - 0.35, z + 0.4).material(mat).build().get();
		list.add(b8);
		
		return list;
	
	}
}