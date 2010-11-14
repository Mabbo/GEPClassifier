package GEPClassify;

public interface GEPConfig {
	int getPopulationSize();
	int getNumRuns();
	int getMaxGenerations();
	FunctionSet getFunctionSet();
	Function getFunction(String a);
	ModificationSet getModifiers();
	int getHeadLength();
	int getTailLength();
	int getNumNodes();
	int getNumCells();
	int getTotalKarvaSize();
	
	void setPopulationSize(int popsize);
	void setNumRuns(int numruns);
	void setMaxGenerations(int maxgen);
	void setFunctionSet(FunctionSet funcset);
	void setModificationSet(ModificationSet modset);
	void setHeadLength(int len);
	void setNumNodes(int nodes);
	void setNumCells(int cells);
	
	//Later, add variable fitness function
	
}
