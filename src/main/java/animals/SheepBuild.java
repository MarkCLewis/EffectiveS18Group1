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
		Cylinder c = DrawFacade.getCylinderBuilder(2, 15).transCoords(20, 0, 10).build().get();
		list.add(c);
		
		//right front leg
		Cylinder c2 = DrawFacade.getCylinderBuilder(2, 15).transCoords(20, 0, 10).build().get();
		list.add(c2);
	
		//right back leg
		Cylinder c3 = DrawFacade.getCylinderBuilder(2, 15).transCoords(0, 0, 10).build().get();	
		list.add(c3);

		//left back leg
		Cylinder c4 = DrawFacade.getCylinderBuilder(2, 15).transCoords(0, 0, 0).build().get();	
		list.add(c4);
	
		//body
		Box b = DrawFacade.getBoxBuilder(25, 8, 15).transCoords(10.25, -10, 0).build().get();
		list.add(b);
		
		//head
		Box b2 = DrawFacade.getBoxBuilder(6, 6, 6).transCoords(23, -16, 0).build().get();
		list.add(b2);
		
		return list;
	}


}
