package functionsets;

import java.util.HashMap;

import GEPClassify.Function;
import GEPClassify.FunctionSet;

public abstract class BasicFunctionSet implements FunctionSet {
	private HashMap<String, Function> functions;
	private String symbolString = "";
	private int maxArgs = 0;
	
	public BasicFunctionSet(){
		functions = new HashMap<String, Function>();
		LoadFunctions();		
	}
	
	public String GetSymbols() {
		return symbolString;
	}

	public Function getFunction(String symbol) {
		return functions.get(symbol);
	}	
	
	protected void AddNewFunction(Function func){
		assert (symbolString.indexOf(func.getSymbol()) < 0);
		maxArgs = Math.max(maxArgs, func.getNumArgs());
		symbolString += func.getSymbol();
		functions.put(func.getSymbol(), func);
	}
	
	public int getMaxArgs(){
		return maxArgs;
	}
}
