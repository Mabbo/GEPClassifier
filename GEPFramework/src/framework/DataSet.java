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
}





