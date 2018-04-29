package terraintesting;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.TriangleMesh;

public class TerrainObject implements virtualworld.WorldObject {

	//center location 
	private final double cX;
	private final double cY;
	private final double cZ;
	private final double xWidth;
	private final double yWidth;
	private final double zWidth;
	private static final int maxScale = 1000;
	private int levelOfDetail; //Determines the value of current scale based on maxScale
	private int currentScale; //close - 100; far - 1000
	
	
	private final long seed;
	private final double noise;
	private static final long defaultSeed = 0L;
	private static final double defaultNoise = 0.5;
	private static final double renderDist = 10000; //Past this distance terrain will not render
	
	public TerrainObject(double cX, double cY, double cZ, double xW, double yW, double zW, long seed, double noise) {
		if(noise<0.0 || noise>1.0)
			throw new IllegalArgumentException("Noise must be between 0 and 1");
		if(xW!=zW)
			throw new IllegalArgumentException("Terrain must be square (equal x and z width)");
		this.cX = cX;
		this.cY = cY;
		this.cZ = cZ;
		
		xWidth = xW;
		yWidth = yW;
		zWidth = zW;
		this.seed = seed;
		this.noise = noise;
		
	}
	
	/**
	 * @return x-coordinate of the center
	 */
	public double getXLoc() {
		return cX;
	}

	/**
	 * @return y-coordinate (height) of the upper-left hand point of this terrain object -- probably should not be used
	 * 
	 */
	@Deprecated
	public double getYLoc() {
		return cY;
	}

	/**
	 * @return z-coordinate of the upper-left hand point of this terrain object
	 */
	public double getZLoc() {
		return cZ;
	}

	/**
	 * @return the length of an object
	 */
	public double getX() {
		return xWidth;
	}

	/**
	 * @return the height of an object -- probably not needed
	 */
	@Deprecated
	public double getY() {
		return yWidth;
	}

	/**
	 * @return the width of an object
	 */
	public double getZ() {
		return zWidth;
	}

	/**
	 * the two dimensional size of the bounding box (i.e. the size of the object
	 * if you were looking at it from above
	 * 
	 * @return the above described size
	 */
	public double getSize() {
		return xWidth*zWidth;
	}

	/**
	 * notifies a WorldObject that a camera is nearby, which allows the object
	 * to change its level of detail
	 * 
	 * @param x
	 *            of the camera
	 * @param z
	 *            of the camera
	 * @return 
	 */
	public boolean notifyOfCamera(double x, double z) {
		double distToCam = Math.sqrt((cX-x)*(cX-x) + (cZ-z)*(cZ-z));
		if(distToCam<renderDist) {
			
			return true;
		} 
		else {
			currentScale = maxScale;
			levelOfDetail = 0;
			return false;
		}
	}
	
	public double getHeight(double x, double z) {
		//TODO
		return 0.0;
	}
	
	public static long getDefaultSeed() {
		return defaultSeed;
	}
	
	public static double getDefaultNoise() {
		return defaultNoise;
	}
	
	
	public TerrainObject[] getChildren() {
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		
		//upper lefthand corner
		TerrainObject child1; 
		//upper righthand corner
		TerrainObject child2; 
		//lower lefthand corner
		TerrainObject child3;
		//lower righthand corner
		TerrainObject child4;
		
		double childXW, childYW, childZW;
		childXW = xWidth/4;
		childYW = yWidth;
		childZW = zWidth/4;
		
		//Common fields across all children
		bldr.setSeed(seed);
		bldr.setNoise(noise);
		bldr.setXWidth(childXW);
		bldr.setYWidth(childYW);
		bldr.setZWidth(childZW);
		
		//Building child1
		bldr.setXLoc(cX-childXW/2);
		bldr.setYLoc(cY); //TODO
		bldr.setZLoc(cZ-childZW/2);
		child1 = bldr.build();
		
		//Building child2
		bldr.setXLoc(cX+childXW/2);
		bldr.setYLoc(cY); //TODO
		bldr.setZLoc(cZ-childZW/2);
		child2 = bldr.build();
		
		//Building child3
		bldr.setXLoc(cX-childXW/2);
		bldr.setYLoc(cY); //TODO
		bldr.setZLoc(cZ+childZW/2);
		child3 = bldr.build();
		
		//Building child4
		bldr.setXLoc(cX+childXW/2);
		bldr.setYLoc(cY); //TODO
		bldr.setZLoc(cZ+childZW/2);
		child4 = bldr.build();
		
		TerrainObject[] children = {child1, child2, child3, child4};
		return children;
	}
	@Override
	public ArrayList<Shape3D> display() {
		agua.generateTerrain testPlot = new agua.generateTerrain();
		float[][] temp = testPlot.generateCoordinates((int)xWidth, (int)xWidth, (int)xWidth, (int)currentScale, (float)noise, (int) seed);
		//public float[][] generateCoordinates(int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed)
		TriangleMesh testGenerate = testPlot.generateTerrain(200, 10, temp);
		MeshView meshView = new MeshView(testGenerate);
		//PhongMaterial material = new PhongMaterial();
		//material.setDiffuseColor(Color.AQUA);
		//meshView.setDrawMode(DrawMode.LINE);
		//meshView.setMaterial(material);
		meshView.setScaleX(currentScale);
		meshView.setScaleZ(currentScale);
		meshView.setScaleY(currentScale*10);
		ArrayList<Shape3D> list = new ArrayList<Shape3D>();
		list.add(meshView);
		return list;
	}
}

