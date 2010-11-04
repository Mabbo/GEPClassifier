package GEPClassify;

import GEPexceptions.IllegalActionException;

/*
 * A function will encapsulate one function used in the GEP
 * The function will have a unique symbol, take a number of 
 * arguments, and return a value 
 */

public interface Function<A, E> {
	E ApplyFunction(A[] args) throws IllegalActionException;
	String getSymbol();
	int getNumArgs();
}
