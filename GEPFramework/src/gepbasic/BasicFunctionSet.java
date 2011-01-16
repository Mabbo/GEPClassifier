package gepbasic;

import java.util.HashMap;
import java.util.Map;

import framework.Function;

public class BasicFunctionSet implements framework.FunctionSet {

	//Given a character, return the function
	
	private Map<String, Function> _functions = new HashMap<String, Function>();
	private String _allCharacters = "";
	private int _maxArgs = 0;
	
	public void addFunction(Function func) {
		//Is a function with this symbol already loaded?
		assert(func.getSymbol().length() == 1);
		if (_allCharacters.indexOf(func.getSymbol()) >= 0) {
			System.err.println("Attempted to add function '" 
					+ func.getClass().getName() + "', using symbol '"
					+ func.getSymbol() + "' which was already in use.");
			return;
		}
		_allCharacters += func.getSymbol();
		_functions.put(func.getSymbol(), func);
		_maxArgs = (func.getNumArgs() > _maxArgs? func.getNumArgs() : _maxArgs);
	}

	public Function getFunction(String symbol) {
		if( _functions.containsKey(symbol)) {
			return _functions.get(symbol);
		} else{
			System.err.println("Attempted to retrieve function with symbol '" 
					+ symbol + "', which did not exist.");
			return null;
		}
	}

	public int getMaxArgs() {
		return _maxArgs;
	}

	public String getSymbols() {
		return _allCharacters;
	}

}
