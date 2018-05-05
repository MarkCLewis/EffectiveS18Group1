package terraintesting;

import java.util.ArrayList;
import java.util.Optional;

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
	private int levelOfDetail = 0; //Determines the value of current scale based on maxScale
	private int scale = maxScale; //close - 100; far - 1000
	
	
	private final long seed;
	private final double noise;
	private static final long defaultSeed = 0L;
	private static final double defaultNoise = 0.5;
	private static final double renderDist = 50;//1000; //Width of the box around the camera that's rendered
	
	public TerrainObject(double cX, double cY, double cZ, double xW, double yW, double zW, int scale, long seed, double noise) {
		if(noise<0.0 || noise>1.0)
			throw new IllegalArgumentException("Noise must be between 0 and 1");
		if(xW!=zW)
			throw new IllegalArgumentException("Terrain must be square (equal x and z width)");
		if(xW<=0.0 || zW<=0.0)
			throw new IllegalArgumentException("Terrain width cannot be less than or equal to zero");
		this.cX = cX;
		this.cY = cY;
		this.cZ = cZ;
		
		this.scale = scale;
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
		// Idea behind this algorithm -
			// Given the square patch of Terrain to generate (centered at the point passed in and with a side length of renderDist)
			// subdivide TerrainObject into quadrants of children until you get terrain objects that are entirely contained within (or coincident with)
			// The patch of terrain around the camera to render. These are the only TerrainObjects to render
		double minTerrainWidth = 10; //TODO - don't let terrain tiles get too small
		if(strictSquareCompare(cX, cZ, xWidth, x, z, renderDist)) {
			// Case where we the terrain object is entirely contained within the render distance
			// Don't split further; render this terrain
			// Do something with the tree here?
			return true;
		}
		else {
			// split terrain further
			TerrainObject[] children = getChildren();
			for(TerrainObject child:children) {
				if(squareCompare(child.cX, child.cZ, child.xWidth, x, z, renderDist)) {
					// Do something with the tree here?
					child.notifyOfCamera(x, z);
				}
			}
			//children.length;
			return false;
		}
	}
	
	public ArrayList<TerrainObject> notifyAndGiveTerrain(double x, double z) {
		// Idea behind this algorithm -
			// Given the square patch of Terrain to generate (centered at the point passed in and with a side length of renderDist)
			// subdivide TerrainObject into quadrants of children until you get terrain objects that are entirely contained within (or coincident with)
			// The patch of terrain around the camera to render. These are the only TerrainObjects to render
		double minTerrainWidth = 10; //TODO - don't let terrain tiles get too small
		ArrayList<TerrainObject> ret = new ArrayList<TerrainObject>();
		
		if(strictSquareCompare(cX, cZ, xWidth, x, z, renderDist)) {
			// Case where we the terrain object is entirely contained within the render distance
			// Don't split further; render this terrain
			// Do something with the tree here?
			System.out.println("Adding terrain (1)");
			ret.add(this);
		}
		else {
			// split terrain further
			TerrainObject[] children = getChildren();
			for(TerrainObject child:children) {
				if(squareCompare(child.cX, child.cZ, child.xWidth, x, z, renderDist)) {
					// Do something with the tree here?
					System.out.println("Adding terrain (2)");
					ret.add(child);
					child.notifyOfCamera(x, z);
				}
			}
			//children.length;
		}
		return ret;
	}
	
	/**
	 * Utility method that tells whether or not the first square is contained within the second
	 * 
	 * @param x1 center X coordinate of the first square
	 * @param z1 center Z coordinate of the first square
	 * @param s1 side length of the first square
	 * @param x2 center X coordinate of the second square 
	 * @param z2 center Z coordinate of the second square
	 * @param s2 side length of the second square
	 * @return
	 */
	private boolean strictSquareCompare(double x1, double z1, double s1, double x2, double z2, double s2) {
		if(z1+s1/2 > z2+s2/2)
			return false;
		if(z1-s1/2 < z2-s2/2)
			return false;
		if(x1+s1/2 > x2+s2/2)
			return false;
		if(x1-s1/2 < x2-s2/2)
			return false;
		else 
			return true;
	}
	
	/**
	 * Utility method that tells whether or not two squares overlap at all
	 * 
	 * @param x1 center X coordinate of the first square
	 * @param z1 center Z coordinate of the first square
	 * @param s1 side length of the first square
	 * @param x2 center X coordinate of the second square 
	 * @param z2 center Z coordinate of the second square
	 * @param s2 side length of the second square
	 * @return
	 */
	private boolean squareCompare(double x1, double z1, double s1, double x2, double z2, double s2) {
		
		if(x1 == x2 && z1 == z2) {
			return true;
		}
		
		double x1lo = x1-s1/2;
		double x1hi = x1+s1/2;
		double z1lo = z1-s1/2;
		double z1hi = z1+s1/2;
		
		double x2lo = x2-s2/2;
		double x2hi = x2+s2/2;
		double z2lo = z2-s2/2;
		double z2hi = z2+s2/2;
		
		if(x2lo < x1hi && x1hi < x2hi && ((z2lo < z1hi && z1hi < z2hi) || (z2lo < z1lo && z1lo < z2hi))) {
			return true;
		}
		if(x2lo < x1lo && x1lo < x2hi && ((z2lo < z1hi && z1hi < z2hi) || (z2lo < z1lo && z1lo < z2hi))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	// Compare doubles to 0.01 accuracy
	private boolean doubleCompare(double d1, double d2) {
		return Math.abs(d1-d2) < 0.01;
	}
	
	public double getHeight(double x, double z) {
		//TODO
		return 0.0;
	}
	
	public static int getDefaultScale() {
		return maxScale;
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
		childXW = xWidth/2;
		childYW = yWidth;
		childZW = zWidth/2;
		//int childLOD = levelOfDetail+1;
		
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
		
		float[][] temp = testPlot.generateCoordinates((int)xWidth, (int)yWidth, (int)zWidth, scale, (float)noise, (int) seed);
		//float[][] temp = testPlot.generateCoordinates(200, 200, 200, 1000, (float)0.60, 3838);
		
		//public float[][] generateCoordinates(int xRes, int yRes, int zRes, int scale, float noiseLevel, int seed)
		TriangleMesh testGenerate = testPlot.generateTerrain(200, 10, temp);
		MeshView meshView = new MeshView(testGenerate);
		//PhongMaterial material = new PhongMaterial();
		//material.setDiffuseColor(Color.AQUA);
		//meshView.setDrawMode(DrawMode.LINE);
		//meshView.setMaterial(material);
		meshView.setScaleX(scale);
		meshView.setScaleZ(scale);
		meshView.setScaleY(scale*10);
		meshView.setTranslateX(cX);
		meshView.setTranslateZ(cZ);
		ArrayList<Shape3D> list = new ArrayList<Shape3D>();
		list.add(meshView);
		return list;
	}
}

