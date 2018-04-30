package quad;

//Receives a pointer or reference to the abstract base class of the Visitor hierarchy
public interface Element {
	void accept (ElementVisitor visitor);
}