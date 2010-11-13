package GEPClassify;

import dataset.DataSet;

public interface GEPEvolver {

	void setGEPConfig(GEPConfig conf);
	void setTrainingSet(DataSet tset);
	
	//The actual genetic algorithm
	void CreatePopulation();
	void RankByFitness();
	void CullWeak();
	void RefillPopulation();
	boolean CheckForFinished();
	
	void RunGeneticAlgorithm();
	
}
