package base;

import java.util.ArrayList;

public class DataSet {

	private ArrayList<Instance> instances;
	ArrayList<String> classes;
	
	public DataSet(){
		instances = new ArrayList<Instance>();
	}
	
	public DataSet(int size){
		instances = new ArrayList<Instance>(size);
	}
	
	public void addInstance(Instance inst){
		instances.add(inst);
	}
	
	public Instance getInstance(int index){
		return instances.get(index);
	}
	
	public int getClassNumber(String classname) {
		if(classes == null) classes = new ArrayList<String>();
		//Do we already have an instance of this class?
		//if not, add it, and return that index
		for( int i = 0; i < classes.size(); ++i) {
			if( classes.get(i).equals(classname)) return i;
		}
		classes.add(classname);
		return classes.size()-1;
	}
	
}
