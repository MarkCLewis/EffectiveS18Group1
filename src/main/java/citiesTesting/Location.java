package citiesTesting;

import java.util.List;

import citiesTesting.CityMaker.Tuple;

public class Location {
	public static boolean isOverlapping(double x, double z, List<Tuple<Integer, Integer, Double, Double, Double, Double>> cities){
		//checks to see if a coordinate overlaps with a city or is too close to a city
		
		double rightX = x+1000;
		double rightZ = z-1000;
		//bottom right point of the city
		
		boolean bool = true;
		for(Tuple<Integer, Integer, Double, Double, Double, Double> a : cities){
			double tempX = a.getX();
			double tempZ = a.getZ();
			Double side = Math.sqrt(a.getSize());
			
			if(a.getCity() != 1){
			//moving the x and z coordinate so they can be used correctly
				double r = Math.sqrt(a.getSize())/2;
				tempX -= r;
				tempZ += r;
				side = r;
			}
			double rightTX = tempX + side;
			double rightTZ = tempZ - side;
			
			//If one rectangle is on left side of other
			if (x > rightTX || tempX > rightX){
		        bool = false;
			}
			
		    //If one rectangle is above other
		    if (z < rightTZ || tempZ < rightZ){
		        bool = false;
		    }
			
			
		}
		return bool;
	}
	
	//TODO-make sure cities are placed on correct terrain
	
}
