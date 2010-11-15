package functions;

public class AverageFunction extends MathFunction {

	@Override
	protected Double applyFunction(Double a, Double b) {
		return (a + b) / 2;
	}

	@Override
	public String getSymbol() {
		return "A";
	}

}
