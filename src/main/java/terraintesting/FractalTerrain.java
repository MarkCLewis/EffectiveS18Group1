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
		private void assignHeights(double[][] heightMap, int x, int y, int squareWidth, double maxElev){
			//figure out a way to not use if statements
			if (heightMap[x][y] == 0.0){
				heightMap[x][y] = generateRandom(maxElev); //x1
			}
			if (heightMap[x + squareWidth][y] == 0.0){
				heightMap[x + squareWidth][y] = generateRandom(maxElev); //x2
			}
			if (heightMap[x][y + squareWidth] == 0.0){
				heightMap[x][y + squareWidth] = generateRandom(maxElev); //x3
			}
			if (heightMap[x + squareWidth][y + squareWidth] == 0.0){
				heightMap[x + squareWidth][y + squareWidth] = generateRandom(maxElev); //x4
			}
		}

		private double average, oAvg;
		private double avgCorners(double[][] heightMap, int x, int y, int squareWidth){
			average = ((heightMap[x][y] + heightMap[x + squareWidth][y] +
					  heightMap[x][y + squareWidth] +
					  heightMap[x + squareWidth][y + squareWidth])/4);
			oAvg = average + peturb;
			return oAvg;
		}

		private void diamondStep(double[][] heightMap, int x, int y, int squareWidth){
			heightMap[x + (squareWidth/2)][y + (squareWidth/2)] = avgCorners(heightMap, squareWidth,x,y);
		}

		private void squareStep(double[][] heightMap, int x, int y, int squareWidth, double maxElev){
			//note to self: pass in the squareWidth halved
			assignHeights(heightMap, x, y, squareWidth, maxElev);
			avgCorners(heightMap, x, y, squareWidth);
		}

		//diamond and square step get repeated over and over
		//squareWidth should be passed in halved every time
		//for loop for looping over the different squares
		//talk to Lewis

		//recursiveFunction(){
			//if ()
			//diamondStep
			//squareStep //call multiple times???
			//iter = iter + 1.0;
			//square width passed in halved into recursive call
            //else if ()
		//}
	}

	/*
	/*
	 * diamond square algorithm to construct terrain (works with squares only)
	 * @ param squareWidth - the width of the square you're making terrain with
	 * @ param ulX - the upperleft hand corner of the square you're making terrain with
	 * @ param ulY - the upperright hand corner of the square you're making terrain with
	 */

