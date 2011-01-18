package gepbasic;

import java.util.ArrayList;
import java.util.Collections;

import dataset.BasicDataSet;
import dataset.NumericalDataFileLoader;

import framework.*;

public class BasicEvolver implements Evolver {

	public Karva EvolveClassifier(GEPConfig conf) {
		
		//Create population of Karvas based on config specs
		ArrayList<Karva> population = new ArrayList<Karva>();
		int popSize = conf.getPopulationSize();
		for( int i = 0; i < popSize; ++i) {
			Genome g = new Genome(conf);
			g.InitializeRandom();
			Karva k = new Karva(conf, g);
			population.add(k);
		}
		
		//Load Dataset, get training and test set
		DataSet trainingSet = new BasicDataSet();
		trainingSet.Initialize(conf.getNumberOfInputs());
		DataSetLoader dsl = new NumericalDataFileLoader();
		String filename = conf.getDataFileLocation() + conf.getDataFileName();
		dsl.LoadData(filename, trainingSet);
		
		double splitPercentage = conf.getKeepPercentage();
		trainingSet.RandomlySplitDataSet(splitPercentage, trainingSet);
		
		//Run GA
		int ScoreToAdd = conf.getNumberOfClasses();
		
		//for each run
		for( int r = 0; r < conf.getNumberOfRuns(); ++r) {
			//for each generation
			for( int g = 0; g < conf.getGenerationsPerRun(); ++g){
				//for each population member
				for( int p = 0; p < population.size(); ++p) {
					//for each instance of the training set
					Karva k = population.get(p);
					for( int i = 0; i < trainingSet.size(); ++i){
						//give the karva the instance, see what class it returns
						int prediction = k.Classify(trainingSet.getInstance(i));
						//if correct, add (number-of-instances) to score
						if( prediction == trainingSet.getInstance(i).getClassValue() )
							k.addFitness(ScoreToAdd);
						else
							k.addFitness(-1);
					}
				}
				//sort the population by fitness
				Collections.sort(population);
				//use the SelectionMethod to remove part of the population
				
				
				//use the crossover methods to refill the population
			
				//use the mutation methods to introduce mutations in the population
				
			}
		}		
		
		return population.get(0);
	}

}
