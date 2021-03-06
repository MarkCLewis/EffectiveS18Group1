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
    
    public  Branch(String t,double w, double h, double d, double px, double py, double pz, double a, double s){
		this.t=t; //type
		this.h=h; //height
		this.w=w; //width
		this.d=d; //depth
		this.px=px; //pos X
		this.py=py; //pos y
		this.pz=pz; //pos Z
		this.a=a; //angle
		this.s=s; //size
		
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
    public void setInitialSize(double w, double h, double d){
		this.w=w;
		this.h=h;
		this.d=d;
    }

    public double getHeight(){
		return h;
	}
    
    public double getWidth(){
		return w;
	}
    public double getDepth(){
		return d;
	}
   
	public double getPositionX(){
		return px;
	}
	
	public double getPositionY(){
		return py;
	}
	public double getPositionZ(){
		return pz;
	}
	public double getS(){
		return s;
	}
	
	public double getAngle(){
		return a;
	}
	
	public void setAngle(double a){
		this.a=a;
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
