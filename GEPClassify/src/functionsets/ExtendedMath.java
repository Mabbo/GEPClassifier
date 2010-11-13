package functionsets;

import functions.*;

public class ExtendedMath extends BasicMath {
	public void LoadFunctions() {
		super.LoadFunctions();
		AddNewFunction(new SqrtFunction());
		AddNewFunction(new CosFunction());
		AddNewFunction(new SinFunction());
	}
}
