package terraintesting;

import virtualworld.WorldObject;

public interface Visitor {

	public boolean visit(QuadTree.Node n);
	public boolean visit(WorldObject item);
	
}
