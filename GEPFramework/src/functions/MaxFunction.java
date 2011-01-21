package functions;

import framework.Function;

public class MaxFunction implements Function {

	public Double ApplyFunction(Double[] args) {
		assert(args.length == 1);
		return (args[0] > args[1]? args[0] : args[1]);
	}

	public int getNumArgs() {
		return 2;
	}

	public String getSymbol() {
		return "M";
	}

}
