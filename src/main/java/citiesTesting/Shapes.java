package citiesTesting;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;


public class Shapes {

	
	public static Cylinder makeCylinder(int w, int h, Color color1, Color color2, double x, double y, double z){
		Cylinder cylinder = new Cylinder(w,h);
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(color1);
		material.setSpecularColor(color2);
		cylinder.setMaterial(material);
		
		cylinder.setTranslateX(x);
		cylinder.setTranslateY(y);
		cylinder.setTranslateZ(z);
		return cylinder;
		//mainGroup.getChildren().add(cylinder);
	}
	/*
	public static Shapes tallBuilding(int l, int w, int h, Color color1, Color color2, int height){
		TODO
	}
	*/
	
	public static Box makeBox(int l, int h, int w, Color color1, Color color2, double x, double y, double z){
		Box box = new Box(l, h, w);
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(color1);
		material.setSpecularColor(color2);
		
		box.setMaterial(material);
		box.setTranslateX(x);
		box.setTranslateY(y);
		box.setTranslateZ(z);
		return box;
	}
	
	public static MeshView makePyramid(int h, int s, Color color1, Color color2, double x, double y, double z){
		TriangleMesh pyramidMesh = new TriangleMesh();
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(color1);
		material.setSpecularColor(color2);
		
		pyramidMesh.getTexCoords().addAll(0,0);
		pyramidMesh.getPoints().addAll(
		        0,    0,    0,            // Point 0 - Top
		        0,    h,    -s/2,         // Point 1 - Front
		        -s/2, h,    0,            // Point 2 - Left
		        s/2,  h,    0,            // Point 3 - Back
		        0,    h,    s/2           // Point 4 - Right
		    );
		
		pyramidMesh.getFaces().addAll(
		        0,0,  2,0,  1,0,          // Front left face
		        0,0,  1,0,  3,0,          // Front right face
		        0,0,  3,0,  4,0,          // Back right face
		        0,0,  4,0,  2,0,          // Back left face
		        4,0,  1,0,  2,0,          // Bottom rear face
		        4,0,  3,0,  1,0           // Bottom front face
		    );
		MeshView pyramid = new MeshView(pyramidMesh);
		pyramid.setDrawMode(DrawMode.FILL);
		pyramid.setMaterial(material);
		pyramid.setTranslateX(x);
		pyramid.setTranslateY(y);
		pyramid.setTranslateZ(z);
		return pyramid;
	}
	
	public static Rectangle makeRectangle(int w, int h, double x, double y, Color color1){
		Rectangle rect = new Rectangle();
		//TODO-find out how to set color
		
		rect.setFill(color1);
		rect.setX(x);
		rect.setY(y);
		rect.setWidth(w);
		rect.setHeight(h);
		return rect;
	}

}
