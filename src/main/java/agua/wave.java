package agua;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.TriangleMesh;
import virtualworld.WorldObject;

public class wave implements WorldObject
{

	private float[][] coords;
	private float[][] endCoords;
	private TriangleMesh mesh;
	private TriangleMesh endMesh;
	private MeshView meshView;
	private int dimensions;
	private waveTransition transition;
	
	public wave(int x, int y, int z, int scale, float noise, int seed)
	{
		dimensions = x;
		generateTerrain testPlot = new generateTerrain();
		float[][] coords = testPlot.generateCoordinates(x, y, z, scale, noise, seed+21);
		float[][] endCoords = testPlot.generateCoordinates(x, y, z, scale, (float)(noise+.05), seed+45);
		
		mesh = testPlot.generateTerrain(x, scale, coords);
		endMesh = testPlot.generateTerrain(z, scale, endCoords);
		
		meshView = new MeshView(mesh);
		meshView.setCullFace(CullFace.FRONT);
		
		PhongMaterial material = new PhongMaterial(Color.AQUA);
		material.setSpecularColor(Color.AQUA);
		material.setDiffuseColor(Color.AQUAMARINE);
		meshView.setDrawMode(DrawMode.FILL);
		meshView.setScaleX(scale/10);
		meshView.setScaleZ(scale/10);
		meshView.setScaleY(scale*2);
		meshView.setTranslateX(0);
		meshView.setTranslateY(0);
		meshView.setTranslateZ(-500);
		meshView.setMaterial(material);
		
		transition = new waveTransition(meshView, mesh, endMesh, 60, 5000);
		
	}
	
	public void setTransition(waveTransition wt)
	{
		transition = wt;
	}

	@Override
	public double getXLoc() {
		// TODO Auto-generated method stub
		return meshView.getTranslateX();
	}

	@Override
	public double getYLoc() {
		// TODO Auto-generated method stub
		return meshView.getTranslateY();
	}

	@Override
	public double getZLoc() {
		// TODO Auto-generated method stub
		return meshView.getTranslateZ();
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return meshView.getTranslateX();
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return meshView.getTranslateY();
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return meshView.getTranslateZ();
	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return dimensions;
	}

	@Override
	public boolean notifyOfCamera(double x, double z) {
		// TODO Auto-generated method stub
		meshView.setVisible(true);
		transition.setAutoReverse(true);
		transition.setCycleCount(transition.INDEFINITE);
		transition.play();
		return true;
	}

	@Override
	public ArrayList<Shape3D> display() {
		// TODO Auto-generated method stub
		ArrayList<Shape3D> temp = new ArrayList<Shape3D>();
		temp.add(meshView);
		return temp;
	}
}
