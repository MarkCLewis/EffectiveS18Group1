package terraintesting;

public class PerlinNoise implements TerrainGenerationAlgorithm {
	//private double[][] FractHeightMap;
	private GradVector2D[][] gradField;
	@Override
	public void generateTerrain(double[][] heightMap, double maxElev) {
		int width = heightMap.length;
		int height = heightMap[0].length;
		gradField = new GradVector2D[width][height];
		defineGrid(width, height);
		
	}
	private void defineGrid(int width, int height){
		for(int x=0; x<width; x++) 
			for(int y=0; y<height; y++)
				gradField[x][y] = GradVector2D.random();
				
	}
	
	private double linearInterpolate(double a, double b, double weight) {
		return (1.0-weight)*a + weight*b;
	}
}
class Vector2D {
	private double x;
	private double y;
	public double dot(Vector2D other) {
		return x*other.getX()+y*other.getY();
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}

// Same as a Vector2D, but is a unit vector
class GradVector2D extends Vector2D {
	private double i;
	private double j;
	public GradVector2D(double x, double y) {
		double mag = Math.sqrt(x*x+y*y);
		i = x/mag;
		j = y/mag;
	}
	public static GradVector2D random() {
		return new GradVector2D(Math.random(), Math.random());
	}
	public double getX() {
		return i;
	}
	public double getY() {
		return j;
	}
}

