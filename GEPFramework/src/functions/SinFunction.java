package functions;

import framework.Function;

public class SinFunction implements Function {

	public Double ApplyFunction(Double[] args) {
		assert(args.length == 1);
		return Math.sin(args[0]);
	}

	public int getNumArgs() {
		return 1;
	}

	public String getSymbol() {
		return "S";
	}

}
