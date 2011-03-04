package base;

import java.util.HashMap;
import framework.Function;

public class FunctionSet {

	private HashMap<Byte, Function> functions;
	private int maxArgs = 0;
	private int size = 0;
	public FunctionSet(){
		functions = new HashMap<Byte, Function>();
	}
	
	public boolean addFunction(Function f){
		if( functions.containsKey(f.getSymbol())) {
			System.err.println("FunctionSet: Attempted to add function '" + f.getSymbol() + "', but symbol was already in use.");
			return false;
		} else {
			functions.put(f.getSymbol(), f);
			if( f.getNumArgs() > maxArgs){
				maxArgs = f.getNumArgs();
			}
			size++;
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
	
	public int getMaxArgs(){
		return maxArgs;
	}

	public int size() {
		return size;
	}
	
}
