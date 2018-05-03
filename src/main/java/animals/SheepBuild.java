package animals;

import java.util.ArrayList;

import graphicsTesting.DrawFacade;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;

public class SheepBuild {
	
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


}
