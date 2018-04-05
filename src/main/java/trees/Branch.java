package trees;

import java.util.ArrayList;
import java.util.List;


public class Branch{
    private String type = null;
    public double ix,iy,iz,fx,fy,fz;
    public String t;
    private List<Branch> children = new ArrayList<>();
    private Branch parent = null;

    public Branch() {
      
    }
    
    public  Branch(String t,double ix, double iy, double iz, double fx, double fy, double fz){
		this.t=t;
		this.ix=ix;
		this.iy=iy;
		this.iz=iz;
		this.fx=fx;
		this.fy=fy;
		this.fz=fz;
		
	}
	


    public void addChild(String t) {
        Branch newChild = new Branch(t,0,0,0,0,0,0);
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
	public void seFinalCoordinates(int fx, int fy, int fz){
		this.fx=fx;
		this.fy=fy;
		this.fz=fz;
	}
	
	public double getix(){
		return ix;
	}
	
	public double getiy(){
		return iy;
	}
	public double getiz(){
		return iz;
	}
	public double getfx(){
		return fx;
	}
	public double getfy(){
		return fy;
	}
	public double getfz(){
		return fz;
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
