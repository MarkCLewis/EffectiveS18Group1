package agua;

import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.TriangleMesh;
import agua.distantRendering;
import java.util.Random;

import agua.SimplexNoise_octave;

public class generateTerrain {
	
	public float[][] generateCoordinates(int xRes, int yRes, int zRes)
	{
		double xStart = 0;
		double xEnd = 500;
		double yStart = 0;
		double yEnd = 500;
		double zStart = 0;
		double zEnd = 500;
		
		SimplexNoise simplexNoise = new SimplexNoise(10000,0.60,3838); //.5 = a bit rough, .35 = choppy water, .70 is rocky mountains
		float[][] generatedCoordinates = new float[xRes][zRes];
		
		for(int x=0;x<xRes;x++){
			for(int z=0; z<zRes; z++) {
				for(int y=0;y<yRes;y++)
	        	{
	        		int xx=(int)(xStart+x*((xEnd-xStart)/xRes));
	                int yy=(int)(yStart+y*((yEnd-yStart)/yRes));
	                int zz=(int)(zStart+z*((zEnd-zStart)/zRes));
	        		generatedCoordinates[x][z] = (float)(1*(simplexNoise.getNoise(xx, yy, zz)));
	        	}
	        }
		}
	        	
		return generatedCoordinates;
		
	}

	public TriangleMesh generateTerrain(int dimension, float scale, float[][] generatedCoordinates)
	{
		
		int ctr = 0;
		ObservableFloatArray points = FXCollections.observableFloatArray();
		ObservableIntegerArray faces = FXCollections.observableIntegerArray();
		Integer[][] vertexID = new Integer[dimension][dimension];
		// the dimensions are a square plot (dimension x dimension) in size
		for (int x = 0; x < dimension; x++) // the x coordinate iterator
		{
			for (int z = 0; z < dimension; z++)  // the z coordinate iterator
			{

				float tmpX = x * scale;
				float tmpY = generatedCoordinates[x][z] * scale;
				float tmpZ = z * scale;
				
				if (z + 1 < dimension && x + 1 < dimension)
				{
					Integer vCurrent = vertexID[x][z];
					Integer vDown = vertexID[x][z + 1]; // the vertex down from the current one
					Integer vRight = vertexID[x + 1][z]; // the vertex to the right of the current one 
					
					// if the current vector is null, then add the XYZ to the points (add new vertex, basically)
					if (vCurrent == null)
					{
						points.addAll(tmpX);
						points.addAll(tmpY);
						points.addAll(tmpZ);
						vertexID[x][z] = ctr++;
						vCurrent = vertexID[x][z];
					}
					// if the down vector is null (but current wont also be null, due to order of operations)
					if (vDown == null) 
					{
						// point above
						points.addAll(tmpX);
						points.addAll(generatedCoordinates[x][z + 1] * scale);
						points.addAll(tmpZ + scale);
						vertexID[x][z + 1] = ctr++;
						vDown = vertexID[x][z + 1];
					}
					// the right vector and its points
					if (vRight == null) 
					{
						points.addAll(tmpX + scale);
						points.addAll(generatedCoordinates[x + 1][z] * scale);
						points.addAll(tmpZ);
						vertexID[x + 1][z] = ctr++;
						vRight = vertexID[x + 1][z];
					}
					// Now we add the faces (after adding the points)
					faces.addAll(vCurrent);
					faces.addAll(0);
					faces.addAll(vDown);
					faces.addAll(0);
					faces.addAll(vRight);
					faces.addAll(0);
				}
				
				// if there is elevation going upwards
				// Now we do the same thing (basically) but with the Up and Left Vertices
				// Then we add their faces 
				
				if (z - 1 >= 0 && x - 1 >= 0) {
					Integer vCurrent = vertexID[x][z];
					Integer vUp = vertexID[x][z - 1];
					Integer vLeft = vertexID[x - 1][z];
					if (vCurrent == null) {
						// current
						points.addAll(tmpX);
						points.addAll(tmpY);
						points.addAll(tmpZ);
						vertexID[x][z] = ctr++;
						vCurrent = vertexID[x][z];
					}
					if (vUp == null) {
						// point to the left
						points.addAll(tmpX - scale);
						points.addAll(generatedCoordinates[x - 1][z] * scale);
						points.addAll(tmpZ);
						vertexID[x][z - 1] = ctr++;
						vUp = vertexID[x][z - 1];
					}
					if (vLeft == null) {

						points.addAll(tmpX);
						points.addAll(generatedCoordinates[x][z - 1] * scale);
						points.addAll(tmpZ - scale);
						vertexID[x - 1][z] = ctr++;
						vLeft = vertexID[x - 1][z];
					}
					faces.addAll(vCurrent);
					faces.addAll(0);
					faces.addAll(vUp);
					faces.addAll(0);
					faces.addAll(vLeft);
					faces.addAll(0);
				}
				
			}
			
		}
		
		TriangleMesh mesh = new TriangleMesh();

		mesh.getTexCoords().addAll(0, 0);
		mesh.getPoints().addAll(points);
		mesh.getFaces().addAll(faces);
		return mesh;
	}
	
}
