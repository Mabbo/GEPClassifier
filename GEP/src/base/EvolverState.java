package base;

import java.util.ArrayList;


public class EvolverState {
	
	private ArrayList<Unit> population;
	private Config config;
	private DataSet trainSet;
	private DataSet testSet;
	
	public EvolverState(ArrayList<Unit> pop, DataSet train, DataSet test, Config conf) {
		population = pop;
		trainSet = train;
		testSet = test;
		config = conf;
	}
	
	public ArrayList<Unit> getPopulation() {
		return population;
	}
	public void setPopulation(ArrayList<Unit> pop){
		population = pop;
	}
	public Config getConfig() {
		return config;
	}
	public DataSet getTrainingSet(){
		return trainSet;
	}
	public DataSet getTestingSet() {
		return testSet;
	}
	
	
	
}
