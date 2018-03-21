package quad;

import virtualworld.WorldObject;

/*
 * Defines a pure virtual visit() method in the abstract base class for each 
 * concrete derived class in the aggregate node hierarchy. 
 * Each visit() method accepts a single argument - a pointer or reference to an original Element derived class.
 */
public interface ElementVisitor {
	public void visit(QuadTree.Node n);
	public void visit(WorldObject item);
	public boolean cares(QuadTree.Node n);
}
