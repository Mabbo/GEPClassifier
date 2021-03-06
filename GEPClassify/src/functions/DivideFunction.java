package functions;

import GEPClassify.Function;
import GEPExceptions.IllegalActionException;

public class DivideFunction implements Function {

	public Double ApplyFunction(Double[] args) throws IllegalActionException {
		assert(args.length == 2);
		if( args[1] == 0 ) throw new IllegalActionException("Divide by zero");
		return args[0] / args[1];
	}
	public int getNumArgs() {
		return 2;
	}
	public String getSymbol() {
		return "/";
	}

}
