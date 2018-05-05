package agua

import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.List
import java.util.Vector
import javafx.animation.Transition
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableFloatArray
import javafx.event.EventHandler
import javafx.scene.shape.MeshView
import javafx.scene.shape.TriangleMesh
import javafx.util.Duration

class waveTransition extends Transition {
	Vector<TriangleMesh> meshFrames
	ObservableFloatArray original
	int highestPoint
	MeshView mv
	TriangleMesh endMesh
	TriangleMesh startMesh
	Vector<TriangleMesh> meshVec
	TriangleMesh currMesh
	ArrayList<Float> meshDistances
	ObservableFloatArray newPoints
	int totalFrames
	int meshIndex
	double duration
	TriangleMesh[] meshArr
	int currIter = 0
	ObservableFloatArray currPoints

	// public waveTransition(MeshView tempMV, TriangleMesh start, TriangleMesh end, int frames, double duration)
	// {
	//
	// mv = tempMV;
	// this.duration = duration;
	// this.endMesh = end;
	// this.currMesh = start;
	// this.startMesh = start;
	// ArrayList<Float> distances = getDistance(this.startMesh, this.endMesh);
	// this.meshDistances = distances;
	// this.totalFrames = frames;
	// //startMesh.getNormals().setAll();
	//
	// setCycleDuration(Duration.millis(duration));
	// this.meshIndex = 0;
	// //		meshArr.addElement(startMesh);
	// //		meshArr.addElement(endMesh);
	// //this.meshFrames = wTransition(start, end, frames);
	// }
	new(MeshView tempMV, TriangleMesh[] meshes, int frames, double duration) {
		meshArr = meshes
		mv = tempMV
		this.duration = duration
		this.totalFrames = frames
		// startMesh.getNormals().setAll();
		setCycleDuration(Duration::millis(duration))
		this.meshIndex = 0 // meshArr.addElement(startMesh);
		// meshArr.addElement(endMesh);
		// this.meshFrames = wTransition(start, end, frames);
	}

	override protected void interpolate(double frame) {
		var int mr = (Math::round(frame) as int)
		if (currIter !== mr) {
			currMesh={val _rdIndx_meshArr=mr meshArr.get(_rdIndx_meshArr)} 
			currPoints=currMesh.getPoints() 
			startMesh=currMesh 
			if (mr <= meshArr.length) endMesh={val _rdIndx_meshArr=mr meshArr.get(_rdIndx_meshArr)}  else endMesh=meshArr.get(0) 
			var ArrayList<Float> meshDistances=getDistance(this.startMesh, this.endMesh) 
			System::out.println(mr) 
			var float[] newPoints=newFloatArrayOfSize(currPoints.size() + 1) 
			currPoints.toArray(newPoints) 
			var ObservableFloatArray copyPoints=currPoints 
			var int otherIter=0 
			for (var int z=1; z < meshDistances.size() * 3 - 3; z+=3) {
				otherIter++ 
				if (frame > .5) {
					if (Math::abs({val _rdIndx_newPoints=z newPoints.get(_rdIndx_newPoints)}) > 0) copyPoints.set(z, (((({val _rdIndx_newPoints=z newPoints.get(_rdIndx_newPoints)} + (meshDistances.get(otherIter)) * .03))) as float)) 
				} else if(Math::abs({
			val _rdIndx_newPoints = z
			newPoints.get(_rdIndx_newPoints)
		}) > 0) copyPoints.set(z, (((({
			val _rdIndx_newPoints = z
			newPoints.get(_rdIndx_newPoints)
		} - (meshDistances.get(otherIter)) * .03))) as float)
		) 
			}

	currPoints.setAll
	(copyPoints)}
	// System.out.println(frame);
	currIter = mr
}
	def ArrayList<Float> getDistance(TriangleMesh start, TriangleMesh end) {
		var ObservableFloatArray startPoints=start.getPoints() 
		var ObservableFloatArray endPoints=end.getPoints() 
		//Float[] distances = new Float[startPoints.size()/3 +1];
		var ArrayList<Float> startZ=new ArrayList<Float>(startPoints.size() / 3 + 1) 
		var ArrayList<Float> endZ=new ArrayList<Float>(startPoints.size() / 3 + 1) 
		var ArrayList<Float> distances=new ArrayList<Float>(startPoints.size() / 3 + 10) 
		for (var int i=1; i < startPoints.size() - 3; i+=3) {
			startZ.add(startPoints.get(i)) 
			endZ.add(endPoints.get(i)) 
			distances.add(endPoints.get(i) - startPoints.get(i)) 
		}
		return distances 
	}
}
