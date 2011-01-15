package framework;
/**
 * A FunctionSet is a container for Function objects.
 * It can add function objects to it, and can return a 
 * string of all of their representative characters.
 * 
 * It also can tell us the maximum number of arguments
 * of any of the functions it contains. 
 * 
 * @author mabbo
 *
 */
public interface FunctionSet {
	void addFunction(Function func);
	Function getFunction(String symbol);
	String getSymbols();
	int getMaxArgs();
}
