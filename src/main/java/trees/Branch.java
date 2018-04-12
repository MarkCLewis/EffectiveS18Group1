package trees;

import java.util.ArrayList;
import java.util.List;


public class Branch{
	
	private String t;
    private double h,w,d,px,py,pz,a,s;
    private List<Branch> children = new ArrayList<>();
    private Branch parent = null;

    public Branch() {
      
    }
    
    public  Branch(String t,double h, double w, double d, double px, double py, double pz, double a, double s){
		this.t=t;
		this.h=h;
		this.w=w;
		this.d=d;
		this.px=px;
		this.py=py;
		this.pz=pz;
		this.a=a;
		this.s=s;
		
	}

    public void addChild(String t) {
        Branch newChild = new Branch(t,0,0,0,0,0,0,0,0);
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
    
    public void setInitialCoordinates(double px, double py, double pz){
		this.px=px;
		this.py=py;
		this.pz=pz;
    }

    public double getH(){
		return h;
	}
    
    public double getW(){
		return w;
	}
   
	public double getPx(){
		return px;
	}
	
	public double getPy(){
		return py;
	}
	public double getPz(){
		return pz;
	}
	public double getS(){
		return s;
	}
	
	public double getAngle(){
		return a;
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
