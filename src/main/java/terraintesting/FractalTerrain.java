package terraintesting;

import java.util.concurrent.ThreadLocalRandom;

public class FractalTerrain implements TerrainGenerationAlgorithm {
	@Override
	public void generateTerrain(/*double*/int[][] heightMap, /*double*/ int maxElev) {
	}
		//count of what iteration it is (matters for roughness)
		private double iter = 0.0;

		//generates random roughness coefficient; can be changed for control
		private double randCoeff = ThreadLocalRandom.current().nextDouble(1.0);
		//private double randCoeff = 0.5 //for testing

		//generates peturbation; -roughness^n <= peturbation <= roughness^n
		//where n is the iteration
		private double peturb = (-(Math.pow(randCoeff, iter))) +
					ThreadLocalRandom.current().nextDouble(Math.pow(randCoeff, (2 * iter)));
		
		public double rand;
		
		//generates random height to assign to points
		public double generateRandom(double maxElev){
			rand = ThreadLocalRandom.current().nextDouble(maxElev);
			return rand;
		}
		
		//assigns the random heights from 0 to the maxElev
		private void assignHeights(double[][] heightMap, int x, int y, int squareWidth){
			heightMap[x][y] = generateRandom(0.5); //x1
			heightMap[x + squareWidth][y] = generateRandom(0.5); //x2
			heightMap[x][y + squareWidth] = generateRandom(0.5); //x3
			heightMap[x + squareWidth][y + squareWidth] = generateRandom(0.5); //x4
		}

		private double average, oAvg;
		private double avgCorners(double[][] heightMap, int squareWidth, int x, int y){
			average = ((heightMap[x][y] + heightMap[x + squareWidth][y] +
					  heightMap[x][y + squareWidth] +
					  heightMap[x + squareWidth][y + squareWidth])/4);
			oAvg = average + peturb;
			return oAvg;
		}

		private void diamondStep(double[][] heightMap, int x, int y, int squareWidth){
			heightMap[x + (squareWidth/2)][y + (squareWidth/2)] = avgCorners(heightMap, squareWidth,x,y);
		}

		//squareStep(double x, double y, squareWidth){
			//assignHeights(passed in with squareWidth divided by 2);
			//avgCorners(passed in with squareWidth divided by 2);
		//}

		//diamond and square step get repeated over and over
		//squareWidth should be passed in halved every time

		//recursiveFunction(){
			//if ()
			//diamondStep
			//squareStep //call multiple times???
			//iter = iter + 1;
			//square width passed in halved into recursive call
		//}
	}

	//Old code we might be able to salvage:
	/*
	/*
	 * diamond square algorithm to construct terrain (works with squares only)
	 * @ param squareWidth - the width of the square you're making terrain with
	 * @ param ulX - the upperleft hand corner of the square you're making terrain with
	 * @ param ulY - the upperright hand corner of the square you're making terrain with

	public static void buildFractalElevation(int squareWidth, int ulX, int ulY) {
		if (squareWidth >= 2) {
			diamondStep(squareWidth, ulX, ulY);
		}
	}

	private static void diamondStep(int squareWidth, int ulX, int ulY) {
		elevation[ulX][ulY] = randomElevation();
		elevation[ulX][ulY + squareWidth] = randomElevation();
		elevation[ulX + squareWidth][ulY] = randomElevation();
		elevation[ulX + squareWidth][ulY + squareWidth] = randomElevation();

		int averageElevation = (elevation[ulX][ulY] +
								elevation[ulX][ulY + squareWidth] +
								elevation[ulX + squareWidth][ulY] +
								elevation[ulX + squareWidth][ulY + squareWidth]) / 4;
		int peturbation = 0;
		elevation[ulX + squareWidth/2][ulY + squareWidth/2] = averageElevation + peturbation;
	}

	public static void squareStep(int squareWidth, int ulX, int ulY) {
	}


     * tesselates the square domain into a triangular grid because triangles are always coplaner
     * @param

    public static void triangleRender(int x1, int y1, int x2, int y2, int x3, int y3) {
    	//randomly set heights of initial triangle corner
    	if(elevation[x1][y1] == 0)
    		elevation[x1][y1] = randomElevation();
    	if(elevation[x2][y2] == 0)
    		elevation[x2][y2] = randomElevation();
    	if(elevation[x3][y3] == 0)
    		elevation[x3][y3] = randomElevation();

    	if(Math.abs(x1-x2)>1 && Math.abs(x2-x3)>1) {
    		//take midpoints
        	int x1_2 = (x1+x2)/2;
        	int y1_2 = (y1+y2)/2;
        	int x1_3 = (x1+x3)/2;
        	int y1_3 = (y1+y3)/2;
        	int x2_3 = (x2+x3)/2;
        	int y2_3 = (y2+y3)/2;

        	//set heights of midpoints
        	int avg = average(elevation[x1][y1], elevation[x2][y2], elevation[x3][y3]);

        	elevation[x1_2][y1_2] = maxElevation % (avg + randomElevation()/2);
        	elevation[x1_3][y1_3] = maxElevation % (avg + randomElevation()/2);
        	elevation[x2_3][y2_3] = maxElevation % (avg + randomElevation()/2);

    		triangleRender(x1, y1, x1_2, y1_2, x1_3, y1_3);
	    	triangleRender(x1, y1, x1_2, y1_2, x1_3, y1_3);
	    	triangleRender(x1, y1, x1_2, y1_2, x1_3, y1_3);
    	}
    }

    private static int average(int ... nums) {
    	int sum = 0;
    	for(int i:nums)
    		sum+=i;
    	return sum/nums.length;
    }


	 *  returns a random elevation between 0 and the maxElevation
	 *  @ return an elevation

	public static int randomElevation() {
		return (int) (maxElevation * Math.random());
	}
	*/

