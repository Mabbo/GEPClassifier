package GEPClassify;

/**
 * A FunctionSet is an object that, when loaded
 * will load all the functions it will contain. 
 * It is not a container. A given class implementing
 * FunctionSet will always have the same set of
 * functions offered.
 * 
 * @author Morley Abbott
 *
 */

public interface FunctionSet {
	void LoadFunctions();
	String GetSymbols();
	Function getFunction(String symbol);
	int getMaxArgs();
}

