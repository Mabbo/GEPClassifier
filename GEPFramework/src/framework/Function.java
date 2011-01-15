package framework;
/**
 * A Function is an object that can take an array of variables
 * and produce an output. 
 * 
 * @author mabbo
 *
 */

public interface Function {
	Double ApplyAction(Double[] args);
	String getSymbol();
	int getNumArgs();
}
