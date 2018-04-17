package terraintesting;

public class DoubleSineWave implements TerrainGenerationAlgorithm {
	@Override
	public void generateTerrain(double[][] heightMap, double maxElev) {
		int width = heightMap.length;
		int height = heightMap[0].length;
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				heightMap[x][y] = simpleHillAndValley(x, y, width, height, maxElev);
				
	}
	
	public static double simpleHillAndValley(int x, int y, int width, double height, double maxElevation) {
		return ((maxElevation/4*Math.sin(x/width*12)+maxElevation/4) + 
		 (maxElevation/4*Math.sin(y/height*12)+maxElevation/4));
	}
}
