package framework;
/**
 * Instance
 * 
 * Represents an instance, a numerical vector of data, with or 
 * without a class label. 
 * 
 * @author mabbo
 *
 */
public interface Instance {
	int size();
	int getClassValue();
	double[] getValues();
}
