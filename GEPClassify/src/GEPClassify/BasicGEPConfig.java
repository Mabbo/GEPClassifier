package GEPClassify;

public class BasicGEPConfig implements GEPConfig {
	protected int _headLength = 6;
	protected int _maxGenerations = 1000;
	protected int _numRuns = 3;
	protected int _populationSize = 50;
	protected int _numNodes = 4;
	protected int _numCells = 4;
	
	protected FunctionSet _functions;
	protected ModificationSet _modifiers;
	
	public FunctionSet getFunctionSet() {
		return _functions;
	}

	public int getHeadLength() {
		return _headLength;
	}

	public int getMaxGenerations() {
		return _maxGenerations;
	}

	public ModificationSet getModifiers() {
		return _modifiers;
	}

	public int getNumCells() {
		return _numCells;
	}

	public int getNumNodes() {
		return _numNodes;
	}

	public int getNumRuns() {
		return _numRuns;
	}

	@Override
	public int getPopulationSize() {
		return _populationSize;
	}

	public void setFunctionSet(FunctionSet funcset) {
		_functions = funcset;
	}

	public void setHeadLength(int len) {
		_headLength = len;
	}

	public void setMaxGenerations(int maxgen) {
		_maxGenerations = maxgen;
	}

	@Override
	public void setModificationSet(ModificationSet modset) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNumCells(int cells) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNumNodes(int nodes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNumRuns(int numruns) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPopulationSize(int popsize) {
		// TODO Auto-generated method stub

	}

}
