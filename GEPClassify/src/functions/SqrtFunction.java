package functions;

import GEPClassify.Function;
import GEPexceptions.IllegalActionException;

public class SqrtFunction implements Function<Double, Double> {

	public Double ApplyFunction(Double[] args) throws IllegalActionException {
		assert(args.length >= 1);
		if( args[0] < 0) throw new IllegalActionException("Sqrt of negative number");
		return Math.sqrt(args[0]);
	}
	public int getNumArgs() {
		return 1;
	}
	public String getSymbol() {
		return "Q";
	}

}
