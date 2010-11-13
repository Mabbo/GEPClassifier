package functions;

import GEPClassify.Function;
import GEPExceptions.IllegalActionException;

public class AbsValueFunction implements Function {

	public Double ApplyFunction(Double[] args) throws IllegalActionException {
		assert(args.length == 1);
		return Math.abs(args[0]);
	}

	public int getNumArgs() {
		return 1;
	}

	public String getSymbol() {
		return "V";
	}

}
