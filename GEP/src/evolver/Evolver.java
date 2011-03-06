package evolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import base.*;
import framework.*;

public class Evolver {

	Random _rand = new Random();	
	public void EvolveClassifier(String configFile) {

			Config conf = new Config();
			try{
				conf.LoadConfigurationFile(configFile);
			}catch(Exception ex){
				System.err.println("Error loading config file: ");
				ex.printStackTrace();
				return;
			}
			
			//Load the DataSetLoader
			DataSetLoader dsl = conf.getDataSetLoader();

			//Get the DataSet
			DataSet trainSet = new DataSet();
			DataSet testSet = new DataSet();
			String datafile = conf.getDatafilelocation() + "/" + conf.getDatafilename();
			dsl.Load(datafile, trainSet, conf);

			//split into training set and testing set
			trainSet.SplitDataSet(testSet, 1.0-conf.getTrainpercentage());

			//Create Initial Population
			ArrayList<Unit> population = new ArrayList<Unit>();
			for( int i = 0; i < conf.getPopulationsize(); ++i){
				Genome genome = new Genome(conf);
				genome.InitializeRandom();
				Unit unit = new Unit(conf, genome);
				population.add(unit);
			}

			//Create EvolverState object 
			EvolverState es = new EvolverState(population, trainSet, testSet, conf);
			
			//For each run
			for( int r = 0; r < conf.getNumruns(); ++r){
				
				for( EvolverStateProcess e : conf.getBeforeRunProcesses()){
					e.Process(es);
				}				
				//For each generation
				for( int g = 0; g < conf.getNumgenerations(); ++g ) {
					//Fitness test for each population member
					conf.getFitnessTest().Process(es);

					//Sort by fitness
					Collections.sort(population);
					//Select using selection method
					SelectionMethod sel = conf.getSelectionMethod();
					double keepFraction = conf.getKeeppercentage();
					sel.RemovePopulation(keepFraction, es);
					//Breed and Mutate
					int popSize = population.size();
					while( population.size() < conf.getPopulationsize() ) {
						Crossover cross = conf.getModifiers().getCrossover();
						Unit A = population.get(_rand.nextInt(popSize));
						Unit B = population.get(_rand.nextInt(popSize));
						Unit newUnit = cross.Cross(A, B, conf);
						population.add(newUnit);
					}

					for( int m = 0; m < conf.getMutationrate() * population.size(); ++m) {
						Mutator mut = conf.getModifiers().getMutator();
						Unit u = population.get(_rand.nextInt(population.size()-1)+1);
						mut.Mutate(u, conf);
						u.Initialize();						
					}

					//For each Generational EvolverStateProcess e
					for( EvolverStateProcess e : conf.getGenerationProcesses()){
						e.Process(es);
					}
					es.IncrementGeneration();
				}
				//For each Run EvolverStateProcess e
				for( EvolverStateProcess e : conf.getRunProcesses()){
					e.Process(es);
				}
				es.IncrementRun();
			}
	}	
}
