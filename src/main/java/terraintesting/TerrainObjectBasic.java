package terraintesting;

import java.util.ArrayList;

import agua.generateTerrain;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.TriangleMesh;

public class TerrainObjectBasic implements virtualworld.WorldObject {
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

	public int getCornerX() {
		return x;
	}

	public int getCronerZ() {
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

	@Override
	public double getXLoc() {
		// TODO Auto-generated method stub
		return x+width/2;
	}

	@Override
	public double getYLoc() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZLoc() {
		// TODO Auto-generated method stub
		return z+width/2;
	}

	@Override
	public double getX() {
		return width;
	}

	@Override
	public double getY() {
		return width;
	}

	@Override
	public double getZ() {
		return width;
	}

	@Override
	public double getSize() {
		return width;
	}

	@Override
	public boolean notifyOfCamera(double x, double z) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ArrayList<Shape3D> display() {
		ArrayList<Shape3D> ret = new ArrayList<Shape3D>();
		ret.add(getMeshview());
		return ret;
	}
	 
}