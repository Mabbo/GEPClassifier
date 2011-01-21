package functions;

import framework.Function;

public class SigmoidFunction implements Function {

	public Double ApplyFunction(Double[] args) {
		assert(args.length == 1);
		return 1.0/ (1+ Math.pow(Math.E, (-1*args[0])));
	}

	public int getNumArgs() {
		return 1;
	}

	public String getSymbol() {
		return "Z";
	}

}
