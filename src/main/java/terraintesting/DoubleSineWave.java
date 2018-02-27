package terraintesting;

public class DoubleSineWave implements TerrainGenerationAlgorithm {
	@Override
	public void generateTerrain(int[][] heightMap, int maxElev) {
		int width = heightMap.length;
		int height = heightMap[0].length;
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				heightMap[x][y] = simpleHillAndValley(x, y, width, height, maxElev);
				
	}
	
	public static int simpleHillAndValley(int x, int y, int width, int height, int maxElevation) {
		return (int) ((maxElevation/4*Math.sin((double)x/width*12)+maxElevation/4) + 
		 (maxElevation/4*Math.sin((double)y/height*12)+maxElevation/4));
	}
}
