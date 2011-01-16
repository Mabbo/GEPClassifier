package functions;

import framework.Function;

public abstract class MathFunction implements Function {
	
	protected abstract Double applyFunction(Double a, Double b);
	
	public Double ApplyFunction(Double[] args) {
		assert(args.length == getNumArgs());
		return applyFunction(args[0], args[1]);
	}
	
	public int getNumArgs() {
		return 2;
	}
}

