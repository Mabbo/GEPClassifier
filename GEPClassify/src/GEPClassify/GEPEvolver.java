package GEPClassify;

public interface GEPEvolver {

	void setGEPConfig(GEPConfig conf);
	void setTrainingSet(TrainingSet tset);
	
	//The actual genetic algorithm
	void CreatePopulation();
	void RankByFitness();
	void CullWeak();
	void RefillPopulation();
	boolean CheckForFinished();
	
	void RunGeneticAlgorithm();
	
}
