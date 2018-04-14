package terraintesting;

import javafx.scene.shape.TriangleMesh;

// Utility class that takes a heightmap and produces a triangle mesh

public class TerrainToTriangleMesh {
	public static TriangleMesh getTriangleMesh(HeightMap hm) {
		TriangleMesh tm = new TriangleMesh();
		//TODO
		return tm;
	}
	
	public static TriangleMesh getTriangleMesh(HeightMap hm, double detail) {
		TriangleMesh tm = new TriangleMesh();
		//TODO
		return tm;
	}
	
	public static TriangleMesh getTriangleMesh(HeightMap hm, int triangleWidth) {
		TriangleMesh tm = new TriangleMesh();
		for(int x=0; x<hm.xWidth(); x+=triangleWidth) {
			for(int z=0; z<hm.zWidth(); z+=triangleWidth) {
				tm.getPoints().addAll(x, (int)hm.height(x, z), z);
			}
		}
		for(int x=0; x<(hm.xWidth()-triangleWidth); x+=triangleWidth) {
			for(int z=0; z<(hm.zWidth()-triangleWidth); z+=triangleWidth) {
				tm.getFaces().addAll(
					x,0, x+1,0, 			x+1+hm.xWidth(),0, 
					x,0, x+hm.xWidth(),0,	x+1+hm.xWidth(),0
				);
			}
		}
		
		return tm;
	}
}
