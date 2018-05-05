package terraintesting;

import agua.generateTerrain;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class TerrainObjectBasic {
	//int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed
	private final int width; //1000 //this is the width in all 3 dimensions
	private final int x; //corner coord; not center
	private final int z;
	private int scale; //10
	private float noise;
	private int seed;
	
	public TerrainObjectBasic(int x, int z, int w, int scale, float noise, int seed) {
		this.x = x;
		this.z = z; 
		width = w;
		this.setScale(scale);
		this.setNoise(noise);
		this.setSeed(seed); 
	}

	public MeshView getMeshview() {
		generateTerrain testPlot = new generateTerrain();
		float[][] coords = testPlot.generateCoordinates(width/scale, width/scale, width/scale, scale, noise, seed);
		//for(float[] a : coords) 
		//	for(float y : a)
		//		System.out.println(y);
		TriangleMesh mesh = testPlot.generateTerrain(width/scale, scale, coords);
		MeshView mv = new MeshView(mesh);
		mv.setTranslateX(x);
		mv.setTranslateZ(z);
		mv.setCullFace(CullFace.FRONT);
		PhongMaterial pm = new PhongMaterial(Color.GREEN);
		mv.setDrawMode(DrawMode.FILL);
		mv.setMaterial(pm);
		return mv;
	}
	
	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public float getNoise() {
		return noise;
	}

	public void setNoise(float noise) {
		this.noise = noise;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}
	 
}
