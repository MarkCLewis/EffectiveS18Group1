package trees;

import java.util.ArrayList;
import java.util.List;


public class Branch{
	
	private String t;
    private double ix,iy,iz, s;
    private List<Branch> children = new ArrayList<>();
    private Branch parent = null;

    public Branch() {
      
    }
    
    public  Branch(String t,double ix, double iy, double iz, double s){
		this.t=t;
		this.ix=ix;
		this.iy=iy;
		this.iz=iz;
		this.s=s;
		
	}
	


    public void addChild(String t) {
        Branch newChild = new Branch(t,0,0,0,0);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(List<Branch> children) {
        for(Branch t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }
    
    public Branch getChildAt(int index) {
  
    	return children.get(index);
    }


    public List<Branch> getChildren() {
    	
        return children;
    }
    
    public String getType() {
        return t;
    }
    
    public void setInitialCoordinates(double ix, double iy, double iz){
		this.ix=ix;
		this.iy=iy;
		this.iz=iz;
    }

	
	public double getIx(){
		return ix;
	}
	
	public double getIy(){
		return iy;
	}
	public double getIz(){
		return iz;
	}
	public double getS(){
		return s;
	}
	
    public void setType(String t) {
        this.t = t;
    }

    private void setParent(Branch parent) {
        this.parent = parent;
    }

    public Branch getParent() {
        return parent;
    }
}
