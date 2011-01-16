package functions;

import framework.Function;

public class DivideFunction implements Function {

	static final double epsilon = 0.0001;
	
	public Double ApplyFunction(Double[] args){
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
