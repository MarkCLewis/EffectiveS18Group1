package terraintesting;

import java.util.concurrent.ThreadLocalRandom;

public class FractalTerrain implements TerrainGenerationAlgorithm {
	@Override
	public void generateTerrain(double[][] heightMap, double maxElev) {
		//for use in figuring out the different stuff
		int squareWidth = heightMap.length;
		//int height = heightMap[0].length;
		int maxIter = 1000;
		int lvlDetail = 241;
		//System.out.println("generated SquareWidth: " + squareWidth);
		//if (iter > 1) {
			//iterateTerrain(heightMap, squareWidth, maxElev);
			//iter = iter/2;
		//}

		for(int i = 0; i < lvlDetail; i++){
			if (maxIter > 2 && squareWidth > 0) {
				iterateTerrain(heightMap, squareWidth, maxElev);
				maxIter = (maxIter/2);
				squareWidth = squareWidth/2;
				//System.out.println("ITER squareWidth" + squareWidth);
			}
		}
		
		int x, y;
		x = ThreadLocalRandom.current().nextInt(599);
		y = ThreadLocalRandom.current().nextInt(599);
		
		System.out.println("Height at " + x + ", " + y + " is " + heightMap[x][y]);
		System.out.println("Height at the middle is: " + heightMap[299][299]);
		System.out.println("Height at 299 599 is: " + heightMap[299][599]);
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
			//System.out.println("generated random");
			return rand;
			
		}

		//assigns the random heights from 0 to the maxElev
		private void assignHeights(double[][] heightMap, int x, int y, int squareWidth, double maxElev){
			//figure out a way to not use if statements
			if (squareWidth >= 0) {
				if (heightMap[x][y] == 0.0){
					heightMap[x][y] = generateRandom(maxElev); //x1
					//System.out.println("SquareWidth: " + squareWidth);
				}
				if (heightMap[x + squareWidth][y] == 0.0){
					heightMap[x + squareWidth][y] = generateRandom(maxElev); //x2
					//System.out.println("SquareWidth: " + squareWidth);
				}
				if (heightMap[x][y + squareWidth] == 0.0){
					heightMap[x][y + squareWidth] = generateRandom(maxElev); //x3
					//System.out.println("SquareWidth: " + squareWidth);
				}
				if (heightMap[x + squareWidth][y + squareWidth] == 0.0){
					//System.out.println("reached");
					heightMap[x + squareWidth][y + squareWidth] = generateRandom(maxElev); //x4
					//System.out.println("AssignedHeights SquareWidth: " + squareWidth);
				}
				//System.out.println("assigned heights");
			}
		}

		//returns the averages the corners
		private double average, oAvg;
		private double avgCorners(double[][] heightMap, int x, int y, int aSquareWidth){
			if (aSquareWidth >= 0){
				average = ((heightMap[x][y] + heightMap[x + aSquareWidth][y] +
						heightMap[x][y + aSquareWidth] +
						heightMap[x + aSquareWidth][y + aSquareWidth])/4);
				//System.out.println(areSame(heightMap, aSquareWidth, x, y));
				oAvg = average + perturb;
			} else {
				oAvg = 0.0;
			}
			//System.out.println("aSquareWidth: " + aSquareWidth);
			//System.out.println("averaged corners");
			return oAvg;
			
		}
		
		/*for testing purposes; says if the 2 places in the heightMap are the same
		private boolean areSame(double heightMap[][], int aSquareWidth, int x, int y){
			if (heightMap[x][y] == heightMap[x + aSquareWidth][y + aSquareWidth]) { 
				return true; 
			} else {
				return false;
			}
		}*/

		//finds and assigns the middle point
		private void diamondStep(double[][] heightMap, int x, int y, int squareWidth, double maxElev){
			//System.out.println("SquareWidth divided by 2: " + (squareWidth/2));
			if (squareWidth >= 0) {
				assignHeights(heightMap, x, y, squareWidth, maxElev);
				double avgdCorners = avgCorners(heightMap, x, y, squareWidth);
				heightMap[x + (squareWidth/2)][y + (squareWidth/2)] = avgdCorners;
				//System.out.println("HeightMap at: " + (x + (squareWidth/2)) + ", " + (y + (squareWidth/2)) + " is: " + avgdCorners);
				//System.out.println("center point: " + heightMap[x + (squareWidth/2)][y + (squareWidth/2)]);
				//System.out.println("avg corners: " + avgCorners(heightMap, squareWidth,x,y));
			}
			//System.out.println("Completed DiamondStep");
		}

		//calculates the diamond midpoint and assigns the heights
		private void squareStep(double[][] heightMap, int x, int y, int squareWidth, double maxElev){
			if (squareWidth >= 0) {
				//note to self: pass in the squareWidth halved
				assignHeights(heightMap, x, y, squareWidth, maxElev);
				avgCorners(heightMap, x, y, squareWidth);
			}
			//System.out.println("Completed SquareStep");
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

		//an attempt at abstraction, gave weird out of bounds error
		/*private void decideDiamondStep(double heightMap[][], int x, int y, int squareWidth, double maxElev){
			for (int i=0; i < 4; i++){
				if (squareWidth > 0) {
					if (i == 0) {
						diamondStep(heightMap, x, y, (squareWidth-1), maxElev);
					} else if (i == 1) {
						diamondStep(heightMap, (squareWidth-1), y, (squareWidth-1), maxElev);
					} else if (i == 2) {
						diamondStep(heightMap, x, (squareWidth-1), (squareWidth-1), maxElev);
					} else if (i == 3) {
						diamondStep(heightMap, (squareWidth -1), (squareWidth-1), (squareWidth-1), maxElev);
					}
				}
			}
		}*/
		
		//iterates the terrain
		private void iterateTerrain(double heightMap[][], int squareWidth, double maxElev){
			//figure out how to further implement it without it getting huge
			for(int i= 0; i < 4; i++){
				if (squareWidth > 0) {
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
					squareStep(heightMap, 0, 0, (squareWidth-1), maxElev);
				} else if (squareWidth == 0) {
					if (i == 0) {
						diamondStep(heightMap, 0, 0, (squareWidth), maxElev);
					} else if (i == 1) {
						diamondStep(heightMap, (squareWidth), 0, (squareWidth), maxElev);
					} else if (i == 2) {
						diamondStep(heightMap, 0, (squareWidth), (squareWidth), maxElev);
					} else if (i == 3) {
						diamondStep(heightMap, (squareWidth), (squareWidth), (squareWidth), maxElev);
					}
				}
			for (int j = 0; i < 4; i++){
				if (squareWidth > 0){
					if (j == 0) {
						diamondStep(heightMap, 0, 299, (squareWidth-1), maxElev);
					} else if (j == 1) {
						diamondStep(heightMap, ((squareWidth/2)-1), 299, ((squareWidth/2)-1), maxElev);
					} else if (j == 2) {
						diamondStep(heightMap, 0, ((squareWidth/2)-1), ((squareWidth/2)-1), maxElev);
					} else if (j == 3) {
						diamondStep(heightMap, ((squareWidth/2)-1), ((squareWidth/2)-1), ((squareWidth/2)-1), maxElev);
					}
				}
			}
				iter += 1.0;
				//System.out.println("iterated terrain");
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
