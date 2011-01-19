package framework;

/**
 * GEPConfig
 * 
 * Responsibilities:
 * Given the config xml file, loads the appropriate data values, 
 * and gives access to their values for the rest of the process.
 * 
 * Must be able to load from an appropriate xml file, handle bad
 * file problems, and provide access to all details.
 * 
 * @author mabbo
 *
 */

public interface GEPConfig {
	void LoadConfig(String filename);
	boolean getIsConfigured();
	
	String getDataFileType();
	String getDataFileName();
	String getDataFileLocation();
	int getNumberOfClasses();
	int getClassIndex();
	int getNumberOfInputs();
	int getNumberOfUsedInputs();
	int[] getIgnoreColumns();
	String getTitle();
	double getTrainingPercentage();
	
	int getGenerationsPerRun();
	int getNumberOfRuns();
	long getMaxTimePerRunMs();
	int getPopulationSize();

	int getNodeLayers();
	int getNumNodes(int layerIndex);
	
	int getNodeHeadLength();
	int getNodeTailLength();
	int getNodeLength();
	FunctionSet getNodeFunctionSet();
	
	int getCellHeadLength();
	int getCellTailLength();
	int getCellLength();
	FunctionSet getCellFunctionSet();
	
	ModificationSet getModifiers();
	double getMutationRate();
	
	SelectionMethod getSelectionMethod();
	double getKeepPercentage();
}
