package quad;

/*
 * accept() is defined to receive a single argument 
 * a pointer or reference to the abstract base class of the Visitor hierarchy
 */
public interface Element {
	void accept (ElementVisitor visitor);
}
