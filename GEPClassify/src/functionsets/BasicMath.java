package functionsets;

import java.util.HashMap;

import functions.*;

import GEPClassify.Function;
import GEPClassify.FunctionSet;

@SuppressWarnings("unchecked")
public class BasicMath implements FunctionSet {
	private HashMap<String, Function> functions;
	
	public String GetSymbols() {
		// TODO Auto-generated method stub
		return null;
	}
	public void LoadFunctions() {
		AddNewFunction(new AddFunction());
		AddNewFunction(new SubtractFunction());
		AddNewFunction(new MultiplyFunction());
		AddNewFunction(new DivideFunction());
	}
	public Function getFunction(String symbol) {
		return functions.get(symbol);
	}	
	
	protected void AddNewFunction(Function func){
		functions.put(func.getSymbol(), func);
	}
}
