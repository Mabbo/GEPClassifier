package dataset;

import java.util.ArrayList;

import framework.DataSet;
import framework.Instance;
import framework.Utilities;

public class BasicDataSet implements DataSet {

	ArrayList<Instance> _instances;
	ArrayList<String> _classes;
	int _numInputs = -1;
	//Given a String, return it's index number
	//If we havn't seen it before, add it to the list
	//give it the new index number
	
	public void Initialize(int numberOfInputs){
		_instances = new ArrayList<Instance>();
		_classes = new ArrayList<String>();
		_numInputs = numberOfInputs;
	}
	
	public void AddInstance(Instance i) {
		_instances.add(i);
	}

	public Instance getInstance(int index) {
		return _instances.get(index);
	}

	public int size() {
		return _instances.size();
	}

	public void RandomlySplitDataSet(double splitPercentage, DataSet targetDS) {
		targetDS.Initialize(_numInputs);
		
		for( int i = _instances.size()-1; i >= 0; --i) {
			if( Utilities.getRandomDouble(1.0) > splitPercentage) {
				targetDS.AddInstance(_instances.get(i));
				_instances.remove(i);
			}
		}
	}

	public String getClassName(int classnum) {
		return _classes.get(classnum);
	}

	public int getClassNumber(String classname) {
		//Do we already have an instance of this class?
		//if not, add it, and return that index
		for( int i = 0; i < _classes.size(); ++i) {
			if( _classes.get(i).equals(classname)) return i;
		}
		_classes.add(classname);
		return _classes.size()-1;
	}

	public int getNumberOfClasses() {
		return _classes.size();
	}

	public int getNumberOfInputs() {
		return _numInputs;
	}

}
