package functions;

import GEPClassify.Function;
import GEPexceptions.IllegalActionException;

/**
 * Safe divide is used to help prevent divide by zero errors
 * if ever the function would have divided by 0, instead it
 * divides by a small epsilon value. Mathematically, incorrect.
 * But, for the purpose of having things work, it can help
 * prevent problems.
 * 
 * @author mabbo
 *
 */

public class SafeDivideFunction implements Function {

	static final double epsilon = 0.0001;
	
	public Double ApplyFunction(Double[] args) throws IllegalActionException {
		assert(args.length == 2);
		if( args[1] == 0)
			return args[0] / epsilon;
		else return args[0] / args[1];
	}

	public int getNumArgs() {
		return 2;
	}
	public String getSymbol() {
		return "D";
	}	
	
}
