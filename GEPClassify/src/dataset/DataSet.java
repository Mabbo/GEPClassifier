package dataset;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * DataSet class
 * 
 * Responsibilities:
 * -Contain instances for classification
 * -Contain description of the data
 *  
 * @author mabbo
 *
 */

public class DataSet {
	
	private int _numClasses;
	private int _numParameters;
	private HashMap<String, Integer> name_to_number;
	private String[] classNames;
	private ArrayList<ClassificationInstance> instances;
	
	public int getNumClasses() {return _numClasses;}
	public String getClassName(int num) { return classNames[num];}
	public ClassificationInstance getInstance(int i){
		return instances.get(i);
	}
	public int getNumParameters() { return _numParameters; }
	
	public DataSet(int num_parameters, String[] classnames ) {
		classNames = classnames;
		_numClasses = classnames.length;
		_numParameters = num_parameters;
		name_to_number = new HashMap<String, Integer>();
		for( int i = 0; i < _numClasses; ++i) {
			name_to_number.put(classNames[i],i);
		}
		instances = new ArrayList<ClassificationInstance>();
	}
	
	public void AddInstance( Double[] data, String classification) {
		if( data.length == _numParameters &&
			name_to_number.containsKey(classification) ) {
			instances.add(
					new ClassificationInstance(
							this, data, getClassNumber(classification) ) );
		}
	}
	
	public int getClassNumber(String className){
		return name_to_number.get(className);
	}	
	
}
