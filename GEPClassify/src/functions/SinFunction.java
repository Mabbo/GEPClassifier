package functions;

import GEPClassify.Function;

public class SinFunction implements Function<Double, Double> {
	public Double ApplyFunction(Double[] args) {
		assert(args.length >= 1);
		return Math.sin(args[0]);
	}
	public int getNumArgs() {
		return 1;
	}
	public String getSymbol() {
		return "S";
	}

}
