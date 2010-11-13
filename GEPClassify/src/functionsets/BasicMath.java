package functionsets;

import functions.*;

public class BasicMath extends BasicFunctionSet {
	
	public void LoadFunctions() {
		AddNewFunction(new AddFunction());
		AddNewFunction(new SubtractFunction());
		AddNewFunction(new MultiplyFunction());
		AddNewFunction(new DivideFunction());
	}	

}
