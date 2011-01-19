package gepbasic;

import java.util.ArrayList;
import java.util.Collections;

import dataset.BasicDataSet;
import dataset.NumericalDataFileLoader;

import framework.*;

public class BasicEvolver implements Evolver {

	@SuppressWarnings("unchecked")
	public Karva EvolveClassifier(GEPConfig conf) {
		
		final int MAX_OVERFIT = 10;
		
		//Create population of Karvas based on config specs
		ArrayList<Karva> population = new ArrayList<Karva>();
		int popSize = conf.getPopulationSize();
		for( int i = 0; i < popSize; ++i) {
			Genome g = new Genome(conf);
			g.InitializeRandom();
			Karva k = new Karva(conf, g);
			k.Initialize();
			population.add(k);
		}
		Karva bestFound = null;
		
		//These will be used to check if we're overfitting to the training set
		//If we improve 3 times against the training set, but not against
		// the test set, we quit, and return the best found against the test set
		Karva 	bestTested = null;
		int 	numSinceBestTested = 0;
		
		//Load Dataset, get training and test set
		DataSet trainingSet = new BasicDataSet();
		DataSet testingSet = new BasicDataSet();
		trainingSet.Initialize(conf.getNumberOfUsedInputs());
		DataSetLoader dsl = new NumericalDataFileLoader();
		String filename = conf.getDataFileLocation() + conf.getDataFileName();
		dsl.LoadData(filename, trainingSet, conf);
		
		double splitPercentage = conf.getTrainingPercentage();
		trainingSet.RandomlySplitDataSet(splitPercentage, testingSet);
		
		//Run GA
		//for each run
		for( int r = 0; r < conf.getNumberOfRuns(); ++r) {
			//for each generation
			for( int g = 0; g < conf.getGenerationsPerRun(); ++g){
				//set fitness to 0
				for( int p = 0; p < population.size(); ++p) {
					population.get(p).setFitness(0);
				}
				//for each population member
				for( int p = 0; p < population.size(); ++p) {
					//for each instance of the training set
					Karva k = population.get(p);
					k.setFitness( getScoreAgainstDataSet(k, trainingSet) );
				}
				//sort the population by fitness
				Collections.sort(population);
				
				//Check if we've improved against the best found
				Karva bestThisRun = population.get(0);
				if( bestFound == null || 
						bestThisRun.getFitness() > bestFound.getFitness() ) {
					bestFound = new Karva(bestThisRun);
					bestFound.Initialize();
					
					int scoreAgainstTestSet = 
						getScoreAgainstDataSet(bestFound, testingSet);
					
					if( bestTested == null ||
							scoreAgainstTestSet >= bestTested.getFitness() ) {
						//the new best has improved the Test score as well
						bestTested = new Karva(bestFound);
						bestTested.setFitness(scoreAgainstTestSet);
						numSinceBestTested = 0;
					} else {
						//Our bestFound has improved, but not the best against
						//the test set. We may be overfitting
						numSinceBestTested++;
						if( numSinceBestTested > MAX_OVERFIT ){
							System.out.println("Overfitting, quitting run.");
							break;
						}
					}
					
				}
				
				//use the SelectionMethod to remove part of the population
				double keepPercentage = conf.getKeepPercentage();
				SelectionMethod selMeth = conf.getSelectionMethod();
				selMeth.RemovePopulation(keepPercentage, population);
				
				//use the crossover methods to refill the population
				ModificationSet ms = conf.getModifiers();
				int startSize = population.size();
				while( population.size() < conf.getPopulationSize() ) {
					Crossover cross = ms.getRandomCrossover();
					int pickA = Utilities.getRandomInt(startSize);
					int pickB = -1;
					while( pickB < 0 ){
						pickB = Utilities.getRandomInt(startSize);
						if( pickA == pickB ) pickB = -1;
					}
					Karva parentA = population.get(pickA);
					Karva parentB = population.get(pickB);
					Karva newKarva = cross.Cross(parentA, parentB);
					newKarva.Initialize();
					population.add(newKarva);
				}
				//use the mutation methods to introduce mutations in the population
				
				int numMutated = (int)(population.size() * conf.getMutationRate());
				for( int m = 0; m < numMutated; ++m){ 
					Mutator mut = ms.getRandomMutator();
					int mutPick = Utilities.getRandomInt(population.size()-1) + 1;
					mut.Mutate(population.get(mutPick));
				}
				System.out.println("Generation: " + g);
				System.out.println(population.get(0).toString());				
			}
		}		
		
		return bestTested;
	}

	private int getScoreAgainstDataSet(Karva k, DataSet ds){
		int score = 0;
		for( int i = 0; i < ds.size(); ++i){
			//give the karva the instance, see what class it returns
			int prediction = k.Classify(ds.getInstance(i));
			//if correct, add (number-of-instances) to score
			if( prediction == ds.getInstance(i).getClassValue() )
				score++;
		}
		return score;
	}
	
	
	
	
	
	
}
