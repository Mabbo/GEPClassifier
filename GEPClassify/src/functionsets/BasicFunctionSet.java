package functionsets;

import java.util.HashMap;

import GEPClassify.Function;
import GEPClassify.FunctionSet;

public abstract class BasicFunctionSet implements FunctionSet {
	private HashMap<String, Function> functions;
	private String symbolString = "";
	
	public String GetSymbols() {
		return symbolString;
	}

	public Function getFunction(String symbol) {
		return functions.get(symbol);
	}	
	
	protected void AddNewFunction(Function func){
		assert (symbolString.indexOf(func.getSymbol()) < 0);
		symbolString += func.getSymbol();
		functions.put(func.getSymbol(), func);
	}
	
}
