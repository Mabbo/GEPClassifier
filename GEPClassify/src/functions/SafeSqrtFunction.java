package functions;

import GEPClassify.Function;
import GEPExceptions.IllegalActionException;

public class SafeSqrtFunction implements Function {

	public Double ApplyFunction(Double[] args) throws IllegalActionException {
		assert(args.length == 1);
		if( args[0] < 0) return 0.0;
		return Math.sqrt(args[0]);
	}
	public int getNumArgs() {
		return 1;
	}
	public String getSymbol() {
		return "P";
	}
}
