package functionsets;

import functions.*;

public class SafeMath  extends BasicFunctionSet {
		
	public void LoadFunctions() {
		AddNewFunction(new AddFunction());
		AddNewFunction(new SubtractFunction());
		AddNewFunction(new MultiplyFunction());
		AddNewFunction(new SafeDivideFunction());
	}
	
}
