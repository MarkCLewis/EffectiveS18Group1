package animals;

import java.util.ArrayList;

import graphicsTesting.DrawFacade;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;

public class PigBuild {

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

}