package base;

import java.util.HashMap;
import framework.Function;

public class FunctionSet {

	private HashMap<Byte, Function> functions;
	
	public FunctionSet(){
		functions = new HashMap<Byte, Function>();
	}
	
	public boolean addFunction(Function f){
		if( functions.containsKey(f.getSymbol())) {
			System.err.println("FunctionSet: Attempted to add function '" + f.getSymbol() + "', but symbol was already in use.");
			return false;
		} else {
			functions.put(f.getSymbol(), f);
			return true;
		}
	}

	public Function getFunction(Byte s){
		if( functions.containsKey(s)) {
			return functions.get(s);
		} else {
			System.err.println("FunctionSet: Attempted to load function '" + s + "', but no such function exists.");
			return null;
		}
	}
	
}
