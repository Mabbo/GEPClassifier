package dataset;

import framework.Instance;

public class BasicInstance implements Instance {

	double[]	_values = new double[0];
	int 		_classvalue = -1;
	
	public int getClassValue() {
		return _classvalue;
	}

	public double[] getValues() {
		return _values;
	}

	public void setClassValue(int classnum) {
		_classvalue = classnum;
	}

	public void setValues(double[] values) {
		_values = values;
	}

	public int size() {
		return _values.length;
	}
}
