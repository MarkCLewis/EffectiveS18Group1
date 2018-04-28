package terraintesting;

import java.util.concurrent.ThreadLocalRandom;

public class FractalTerrain implements TerrainGenerationAlgorithm {
	@Override
	public void generateTerrain(double[][] heightMap, double maxElev) {
		//for use in figuring out the different stuff
		int squareWidth = heightMap.length;
		//int height = heightMap[0].length;
		int maxIter = 100;
		int lvlDetail = 241;

		//if (iter > 1) {
			//iterateTerrain(heightMap, squareWidth, maxElev);
			//iter = iter/2;
		//}

		for(int i = 0; i < lvlDetail; i++){
			if (maxIter > 2) {
				iterateTerrain(heightMap, squareWidth, maxElev);
				maxIter = (maxIter/2);
			}
		}
	}
		//count of what iteration it is (matters for roughness)
		private double iter = 0.0;

		//generates random roughness coefficient; can be changed for control
		private double randCoeff = ThreadLocalRandom.current().nextDouble(1.0);
		//private double randCoeff = 0.5 //for testing

		//generates perturbation; -roughness^n <= perturbation <= roughness^n
		//where n is the iteration
		private double perturb = (-(Math.pow(randCoeff, iter))) +
					ThreadLocalRandom.current().nextDouble(Math.pow(randCoeff, (2 * iter)));

		//holds the random value
		public double rand;

		//generates random height to assign to points
		public double generateRandom(double maxElev){
			rand = ThreadLocalRandom.current().nextDouble(maxElev);
			return rand;
			System.out.print("generated random");
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
			System.out.print("assigned heights");
		}

		//returns the averages the corners
		private double average, oAvg;
		private double avgCorners(double[][] heightMap, int x, int y, int squareWidth){
			average = ((heightMap[x][y] + heightMap[x + squareWidth][y] +
					  heightMap[x][y + squareWidth] +
					  heightMap[x + squareWidth][y + squareWidth])/4);
			oAvg = average + perturb;
			return oAvg;
			System.out.print("averaged corners");
		}

		//finds and assigns the middle point
		private void diamondStep(double[][] heightMap, int x, int y, int squareWidth, double maxElev){
			assignHeights(heightMap, x, y, squareWidth, maxElev);
			heightMap[x + (squareWidth/2)][y + (squareWidth/2)] = avgCorners(heightMap, squareWidth,x,y);
			System.out.print("Completed DiamondStep");
		}

		//calculates the diamond midpoint and assigns the heights
		private void squareStep(double[][] heightMap, int x, int y, int squareWidth, double maxElev){
			//note to self: pass in the squareWidth halved
			assignHeights(heightMap, x, y, squareWidth, maxElev);
			avgCorners(heightMap, x, y, squareWidth);
			System.out.print("Completed SquareStep");
		}

		//diamond and square step get repeated over and over
		//squareWidth should be passed in halved every time
		//for loop for looping over the different squares

		//recursiveFunction(){
			//if ()
			//diamondStep
			//squareStep //call multiple times???
			//iter = iter + 1.0;
			//square width passed in halved into recursive call
		    //else if ()
		//}

		//iterates the terrain
		private void iterateTerrain(double heightMap[][], int squareWidth, double maxElev){
			//figure out how to further implement it without it getting huge
			for(int i= 0; i < 4; i++){
				if (i == 0) {
					diamondStep(heightMap, 0, 0, (squareWidth-1), maxElev);
				} else if (i == 1) {
					diamondStep(heightMap, (squareWidth-1), 0, (squareWidth-1), maxElev);
				} else if (i == 2) {
					diamondStep(heightMap, 0, (squareWidth-1), (squareWidth-1), maxElev);
				} else if (i == 3) {
					diamondStep(heightMap, (squareWidth-1), (squareWidth-1), (squareWidth-1), maxElev);
				}
				squareWidth = (squareWidth/2);
				squareStep(heightMap, 0, 0, squareWidth, maxElev);
				iter += 1.0;
			}
		}
	}

	/*
	/*
	 * diamond square algorithm to construct terrain (works with squares only)
	 * @ param squareWidth - the width of the square you're making terrain with
	 * @ param ulX - the upperleft hand corner of the square you're making terrain with
	 * @ param ulY - the upperright hand corner of the square you're making terrain with
	 */
