package graphicsTesting;

import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class DrawFacade {
	
	
	
	public static Box createBox(PhongMaterial mat, double length, double height, double width, double x, double y, double z) {
		Box box = new Box(length, height, width);
		box.setDrawMode(DrawMode.FILL);
		box.setMaterial(mat);
		box.setTranslateX(x);
		box.setTranslateY(y);
		box.setTranslateZ(z);
		
		return box;
	}
	
	public static Cylinder createCylinder(PhongMaterial mat, double height, double width, double x, double y, double z) {
		Cylinder cyl = new Cylinder(height, width);
		cyl.setDrawMode(DrawMode.FILL);
		cyl.setMaterial(mat);
		cyl.setTranslateX(x);
		cyl.setTranslateY(y);
		cyl.setTranslateZ(z);
		
		return cyl;
	}
	
	public static MeshView createPyramidMeshView(PhongMaterial mat, float height, float side, double x, double y, double z) {
		TriangleMesh newMesh = new TriangleMesh();

		newMesh.getTexCoords().addAll(0,0);
		
		float h = height;                 // Height
		float s = side;                   // Side
		newMesh.getPoints().addAll(
		        0,    0,    0,            // Point 0 - Top
		        0,    h,    -s/2,         // Point 1 - Front
		        -s/2, h,    0,            // Point 2 - Left
		        s/2,  h,    0,            // Point 3 - Back
		        0,    h,    s/2           // Point 4 - Right
		    );
		
		newMesh.getFaces().addAll(
		        0,0,  2,0,  1,0,          // Front left face
		        0,0,  1,0,  3,0,          // Front right face
		        0,0,  3,0,  4,0,          // Back right face
		        0,0,  4,0,  2,0,          // Back left face
		        4,0,  1,0,  2,0,          // Bottom rear face
		        4,0,  3,0,  1,0           // Bottom front face
		    );
		
		return createMeshView(newMesh, mat, x, y, z);
	}
	
	public static MeshView createBoxMeshView(PhongMaterial mat, float length, float height, float width, double x, double y, double z) {
		TriangleMesh newMesh = new TriangleMesh();

		newMesh.getTexCoords().addAll(0,0);
		
		float l = length;
		float h = height;                 // Height
		float w = width;                   // Side
		newMesh.getPoints().addAll(
		        0,    0,    0,            // Point 0 - 000
		        l,    0,    0,            // Point 1 - l00
		        l,    0,    w,            // Point 2 - l0w
		        0,    0,    w,            // Point 3 - 00w
		        0,    h,    w,            // Point 4 - 0hw
		        0,    h,    0,            // Point 5 - 0h0
		        l,    h,    0,            // Point 6 - lh0
		        l,    h,    w             // Point 4 - lhw
		    );
		
		newMesh.getFaces().addAll(
				
				4,0,  7,0,  6,0,          // Top front
				5,0,  4,0,  6,0,          // Top back
		        5,0,  6,0,  0,0,          // Left top
		        6,0,  1,0,  0,0,          // Left bottom
		        6,0,  7,0,  1,0,          // Front top
		        7,0,  2,0,  1,0,          // Front bottom
		        7,0,  4,0,  2,0,          // Right top
		        4,0,  3,0,  2,0,          // Right bottom
		        4,0,  5,0,  3,0,          // Back top
		        5,0,  0,0,  3,0,          // Back bottom
		        1,0,  2,0,  0,0,          // Bottom back
		        2,0,  3,0,  0,0           // Bottom front
		    );
		
		return createMeshView(newMesh, mat, x, y, z);
	}
	
	public static MeshView createCustomMeshView(PhongMaterial mat, float[] points, int[] faces, double x, double y, double z) {
		TriangleMesh newMesh = new TriangleMesh();
		
		newMesh.getTexCoords().addAll(0,0);
		newMesh.getPoints().addAll(points);
		newMesh.getFaces().addAll(faces);
		
		return createMeshView(newMesh, mat, x, y, z);
	}
	
	public static void addMesh(Group group, TriangleMesh tMesh, PhongMaterial material, double x, double y, double z) {
		group.getChildren().add(createMeshView(tMesh, material, x, y, z));
	}
	
	private static MeshView createMeshView(TriangleMesh tMesh, PhongMaterial material, double x, double y, double z) {
		MeshView mv = new MeshView(tMesh);
		mv.setDrawMode(DrawMode.FILL);
		mv.setMaterial(material);
		mv.setTranslateX(x);
		mv.setTranslateY(y);
		mv.setTranslateZ(z);
		
		return mv;
	}
}
