package animals;

import java.util.ArrayList;

import graphicsTesting.DrawFacade;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;

public class PigBuild {

	public static ArrayList<Shape3D> makePig(double x, double y, double z) {
		ArrayList<Shape3D> list = new ArrayList<Shape3D>();
		
		//left front leg
		Cylinder c = DrawFacade.getCylinderBuilder(.15, .7).transCoords(1.5, 0, .5).build().get();
		list.add(c);
		
		//right front leg
		Cylinder c2 = DrawFacade.getCylinderBuilder(.15, .7).transCoords(1.5, 0, 1).build().get();
		list.add(c2);
	
		//right back leg
		Cylinder c3 = DrawFacade.getCylinderBuilder(.15, .7).transCoords(0, 0, 1).build().get();	
		list.add(c3);

		//left back leg
		Cylinder c4 = DrawFacade.getCylinderBuilder(.15, .7).transCoords(0, 0, .5).build().get();	
		list.add(c4);
	
		//body
		Box b = DrawFacade.getBoxBuilder(2, .8, 1).transCoords(2, -1.2, .73).build().get();
		list.add(b);
		
		//head
		Box b2 = DrawFacade.getBoxBuilder(.6, .6, .6).transCoords(2, -1.2, .73).build().get();
		list.add(b2);
		
		Box b3 = DrawFacade.getBoxBuilder(.3, .3, .3).transCoords(2.4, -1.1, .73).build().get();
		list.add(b3);
		
		return list;
	}


}