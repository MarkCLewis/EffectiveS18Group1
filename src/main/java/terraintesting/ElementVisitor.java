package terraintesting;

import virtualworld.WorldObject;

/*
 * Defines a pure virtual visit() method in the abstract base class for each 
 * concrete derived class in the aggregate node hierarchy. 
 * Each visit() method accepts a single argument - a pointer or reference to an original Element derived class.
 */
public interface ElementVisitor {
	public boolean visit(QuadTree.Node n);
	public boolean visit(WorldObject item);
}
