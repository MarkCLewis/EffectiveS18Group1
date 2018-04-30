package quad;

import virtualworld.WorldObject;

//Defines a pure virtual visit() method in the abstract base class for each concrete derived class
public interface ElementVisitor {
	public void visit(Node n);
	public void visit(WorldObject item);
	public boolean cares(Node n);
}