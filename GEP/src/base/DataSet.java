package base;

import java.util.ArrayList;

public class DataSet {

	private ArrayList<Instance> instances;

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
	
}
