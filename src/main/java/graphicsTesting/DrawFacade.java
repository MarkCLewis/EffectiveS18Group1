package graphicsTesting;

import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class DrawFacade {
	
	public TriangleMesh createPyramidMesh(float height, float side) {
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
		
		return newMesh;
	}
	
	public TriangleMesh createBoxMesh(float length, float height, float width) {
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
				6,0,  3,0,  4,0,          // Top front
				6,0,  5,0,  4,0,          // Top back
		        0,0,  5,0,  6,0,          // Left top
		        0,0,  1,0,  6,0,          // Left bottom
		        1,0,  6,0,  7,0,          // Front top
		        1,0,  2,0,  7,0,          // Front bottom
		        2,0,  7,0,  4,0,          // Right top
		        2,0,  3,0,  4,0,          // Right bottom
		        3,0,  4,0,  5,0,          // Back top
		        3,0,  0,0,  5,0,          // Back bottom
		        0,0,  1,0,  2,0,          // Bottom back
		        0,0,  3,0,  2,0           // Bottom front
		    );
		
		return newMesh;
	}
	
	public void addMesh(Group group, TriangleMesh tMesh, PhongMaterial material, int[] transCords) {
		group.getChildren().add(createMeshView(tMesh, material, transCords));
	}
	
	public MeshView createMeshView(TriangleMesh tMesh, PhongMaterial material, int[] transCords) {
		MeshView mv = new MeshView(tMesh);
		mv.setDrawMode(DrawMode.FILL);
		mv.setMaterial(material);
		mv.setTranslateX(transCords[0]);
		mv.setTranslateY(transCords[1]);
		mv.setTranslateZ(transCords[2]);
		
		return mv;
	}
}
