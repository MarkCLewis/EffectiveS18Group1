package terraintesting;

public class NeighborAverageSmoothing implements TerrainGenerationAlgorithm {
	@Override
	public void generateTerrain(double[][] heightMap, double maxElev) {
		int width = heightMap.length;
		int height = heightMap[0].length;
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				heightMap[x][y]= randomElevation(maxElev);
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++) 
				heightMap[x][y]= getNeighborAvg(width, height, x, y, heightMap);
	}
	private static double getNeighborAvg(int w, int h, int x, int y, double[][] elev) {
		int sum = 0;
		int count = 0;
		double[] points = new double[8];
		points[0] = getNeighborElev(w, h, x, y, -1, -1, elev);
		points[1] = getNeighborElev(w, h, x, y, -1, 0, elev);
		points[2] = getNeighborElev(w, h, x, y, -1, 1, elev);
		points[3] = getNeighborElev(w, h, x, y, 0, -1, elev);
		points[4] = getNeighborElev(w, h, x, y, 0, 1, elev);
		points[5] = getNeighborElev(w, h, x, y, 1, -1, elev);
		points[6] = getNeighborElev(w, h, x, y, 1, 0, elev);
		points[7] = getNeighborElev(w, h, x, y, 1, 1, elev);
		
		for(int i=0; i<points.length; i++) {
			if(points[i]!=-1) {
				sum+=points[i];
				count++;
			}
		}
		
		return sum/count;
	}
	private static double getNeighborElev(int w, int h, int x, int y, int deltaX, int deltaY, double[][] elev) {
		int x2 = x+deltaX;
		int y2 = y+deltaY;
		if(x2<0 || y2<0 || x2>=w || y2>=h)
			return -1;
		else
			return elev[x2][y2];
	}
	public static double randomElevation(double maxElevation) {
		return (maxElevation * Math.random());
	}
}
