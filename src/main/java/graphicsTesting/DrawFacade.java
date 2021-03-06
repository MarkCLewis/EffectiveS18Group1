package graphicsTesting;

import graphicsTesting.BoxMaker.BoxBuilder;
import graphicsTesting.CustomMeshViewMaker.CMVBuilder;
import graphicsTesting.CylinderMaker.CylinderBuilder;
import graphicsTesting.PyramidMaker.PyramidBuilder;

/**
 * The DrawFacade class contains methods that return ShapeBuilders for different ShapeMaker implementations.
 * It is intended to simplify the process of making Shape3D objects by providing access to all the ShapeMaker classes in one interface.
 * @author jfisher1
 */
public class DrawFacade {
	
	/**
	 * Returns a BoxBuilder that can be used to create a new Box product
	 * @param w represents the width of the Box
	 * @param h represents the height of the Box
	 * @param d represents the depth of the Box
	 * @return a BoxBuilder created with the above parameters
	 */
	public static BoxBuilder getBoxBuilder(double w, double h, double d) {
		return new BoxMaker.BoxBuilder(w, h, d);
	}
	
	/**
	 * Returns a CylinderBuilder that can be used to create a new Cylinder product
	 * @param w represents the width of the Cylinder
	 * @param h represents the height of the Cylinder
	 * @return a CylinderBuilder created with the above parameters
	 */
	public static CylinderBuilder getCylinderBuilder(double w, double h) {
		return new CylinderMaker.CylinderBuilder(w, h);
	}
	
	/**
	 * Returns a PyramidBuilder that can be used to create a new MeshView product
	 * @param h represents the height of the Pyramid
	 * @param s represents the side width of the Pyramid
	 * @return a PyramidBuilder created with the above parameters
	 */
	public static PyramidBuilder getPyramidBuilder(float h, float s) {
		return new PyramidMaker.PyramidBuilder(h, s);
	}
	
	/**
	 * Returns a CustomMeshViewBuilder that can be used to create a new MeshView product
	 * @param points represents a float array of the MeshView's points
	 * @param faces represents an int array of the MeshView's faces
	 * @return a CustomMeshViewBuilder created with the above parameters
	 */
	public static CMVBuilder getCustomMeshViewBuilder(float[] points, int[] faces) {
		return new CustomMeshViewMaker.CMVBuilder(points, faces);
	}
	
	/*
	public static MeshView createPyramidMeshView(PhongMaterial mat, Color c1, Color c2, float height, float side, double x, double y, double z) {
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
		
		return createMeshView(newMesh, mat, c1, c2, x, y, z);
	}
	
	public static MeshView createBoxMeshView(PhongMaterial mat, Color c1, Color c2, float length, float height, float width, double x, double y, double z) {
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
		
		return createMeshView(newMesh, mat, c1, c2, x, y, z);
	}
	
	public static MeshView createCustomMeshView(PhongMaterial mat, Color c1, Color c2, float[] points, int[] faces, double x, double y, double z) {
		TriangleMesh newMesh = new TriangleMesh();
		
		newMesh.getTexCoords().addAll(0,0);
		newMesh.getPoints().addAll(points);
		newMesh.getFaces().addAll(faces);
		
		return createMeshView(newMesh, mat, c1, c2, x, y, z);
	}
	
	public static void addMesh(Group group, TriangleMesh tMesh, PhongMaterial mat, Color c1, Color c2, double x, double y, double z) {
		group.getChildren().add(createMeshView(tMesh, mat, c1, c2, x, y, z));
	}
	
	private static MeshView createMeshView(TriangleMesh tMesh, PhongMaterial mat, Color c1, Color c2, double x, double y, double z) {
		MeshView mv = new MeshView(tMesh);
		mv.setDrawMode(DrawMode.FILL);
		mat.setDiffuseColor(c1);
		mat.setSpecularColor(c2);
		mv.setMaterial(mat);
		mv.setTranslateX(x);
		mv.setTranslateY(y);
		mv.setTranslateZ(z);
		
		return mv;
	}
	*/
}
