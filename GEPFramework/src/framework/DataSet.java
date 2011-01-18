package framework;


/**
 * DataSet
 * 
 * Responsibilities:
 * -provide access to a series of instances of numerical data vectors.
 * 
 * @author mabbo
 *
 */

public interface DataSet {
	int size();
	Instance getInstance(int index);
	void AddInstance(Instance i);
	int getNumberOfInputs();
	int getNumberOfClasses();
	String getClassName(int classnum);
	int getClassNumber(String classname);
	//Get a new training set, removing the instances from this one 
	void RandomlySplitDataSet(double splitPercentage, DataSet targetDS);
	void Initialize(int numberOfInputs);
}





