package GEPClassify;

import GEPExceptions.IllegalActionException;

/*
 * A function will encapsulate one function used in the GEP
 * The function will have a unique symbol, take a number of 
 * arguments, and return a value 
 */

public interface Function {
	Double ApplyFunction(Double[] args) throws IllegalActionException;
	String getSymbol();
	int getNumArgs();
}
