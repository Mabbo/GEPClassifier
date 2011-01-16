package functions;

public class MultiplyFunction extends MathFunction {
	protected Double applyFunction(Double a, Double b) {
		return a * b;
	}
	public String getSymbol() {
		return "*";
	}
}
