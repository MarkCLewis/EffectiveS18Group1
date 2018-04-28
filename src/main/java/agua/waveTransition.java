package agua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableFloatArray;
import javafx.event.EventHandler;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.util.Duration;


public class waveTransition extends Transition
{
	private Vector<TriangleMesh> meshFrames;
	private ObservableFloatArray original;
	private int highestPoint;
	private MeshView mv;
	private TriangleMesh endMesh;
	private TriangleMesh startMesh;
	private Vector<TriangleMesh> meshArr;
	private TriangleMesh currMesh;
	private ArrayList<Float> meshDistances;
	private ObservableFloatArray newPoints;
	private int totalFrames;
	private int meshIndex;
	private double duration;
	
	
	
	public waveTransition(MeshView tempMV, TriangleMesh start, TriangleMesh end, int frames, double duration)
	{
		
		mv = tempMV;
		this.duration = duration;
		this.endMesh = end;
		this.currMesh = start;
		this.startMesh = start;
		ArrayList<Float> distances = getDistance(this.startMesh, this.endMesh);
		this.meshDistances = distances;
		this.totalFrames = frames;
		//startMesh.getNormals().setAll();
		
		setCycleDuration(Duration.millis(duration));
		this.meshIndex = 0;
//		meshArr.addElement(startMesh);
//		meshArr.addElement(endMesh);
		//this.meshFrames = wTransition(start, end, frames);
	}
	
	@Override
	protected void interpolate(double frame) 
	{
		ObservableFloatArray currPoints = currMesh.getPoints();
		float[] newPoints = new float[currPoints.size() + 1];
		currPoints.toArray(newPoints);
		ObservableFloatArray copyPoints = currPoints;
		int otherIter = 0;
		for(int z = 1; z < meshDistances.size()*3 - 3 ; z+=3)
		{
			otherIter++;
			if(frame > .5)
			{
				if(Math.abs(newPoints[z]) > 0)
					copyPoints.set(z, (float) (((newPoints[z] + (meshDistances.get(otherIter))*.03))));
			}
			else
				if(Math.abs(newPoints[z]) > 0)
					copyPoints.set(z, (float) (((newPoints[z] - (meshDistances.get(otherIter))*.03))));

		}
		//System.out.println(frame);
		currPoints.setAll(copyPoints);
		
	}
	
	public ArrayList<Float> getDistance(TriangleMesh start, TriangleMesh end)
	{
		ObservableFloatArray startPoints = start.getPoints();
		ObservableFloatArray endPoints = end.getPoints();
		//Float[] distances = new Float[startPoints.size()/3 +1];
		ArrayList<Float> startZ = new ArrayList<Float>(startPoints.size()/3 + 1);
		ArrayList<Float> endZ = new ArrayList<Float>(startPoints.size()/3 + 1);
		ArrayList<Float> distances = new ArrayList<Float>(startPoints.size()/3 + 10);
		for(int i = 1; i < startPoints.size() - 3; i += 3)
		{
			startZ.add(startPoints.get(i));
			endZ.add(endPoints.get(i));
			distances.add(endPoints.get(i) - startPoints.get(i));
		}
		return distances;
	}

	
}