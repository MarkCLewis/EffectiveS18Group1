package agua;

import java.lang.reflect.Array;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import agua.distantRendering;


import java.util.Random;

import agua.SimplexNoise_octave;

// Testing with creating large wave pools.
// Also implementing a new Array based system for animation, to make it look better (and hopefully run better)

public class largeWavePool 
{
	
	private int seed;
	private int scale = 120;  
	private int xDim;
	private int yDim;
	private int zDim;
	//private int[] cds = {50,scale,50};
	private float noiseLevel = (float) .30;
	private TriangleMesh[] meshes;
	
	public TriangleMesh[] largeWavePool(int size, int numVariations, int scale, int genSeed, int noise)
	{
		generateTerrain terrainGen = new generateTerrain();
		TriangleMesh[] meshArray = new TriangleMesh[size];
		this.xDim = size;
		this.yDim = scale;
		this.zDim = size;
		this.seed = genSeed;
		this.noiseLevel = noise;
		
		for(int i = 0; i < numVariations; i++)
		{
			float[][] coords = terrainGen.generateCoordinates(size, scale, size, scale, noiseLevel, seed+(i+23));
			TriangleMesh mesh = terrainGen.generateTerrain(size, scale, coords);
			meshArray[i] = mesh;
		}
		this.meshes = meshArray;
		return meshArray;
	}
	
	//public TriangleMesh[] spaceWaves(int )
	
}
