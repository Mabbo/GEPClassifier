package functionsets;

import functions.*;

public class ExtendedSafeMath extends SafeMath {
	public void LoadFunctions() {
		super.LoadFunctions();
		AddNewFunction(new SafeSqrtFunction());
		AddNewFunction(new CosFunction());
		AddNewFunction(new SinFunction());
		AddNewFunction(new AverageFunction());
	}
}
