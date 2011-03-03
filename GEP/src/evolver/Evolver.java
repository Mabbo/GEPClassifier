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
		conf.LoadConfigurationFile(configFile);
		
		//Load the DataSetLoader
		DataSetLoader dsl = conf.getDataSetLoaderInstance();
		
		//Get the DataSet
		DataSet ds = new DataSet();
		String datafile = conf.getDatafilelocation() + "/" + conf.getDatafilename();
		dsl.Load(datafile, ds, conf);
		
		//split into training set and testing set
		
		//Create Initial Population
		ArrayList<Unit> population = new ArrayList<Unit>();
		for( int i = 0; i < conf.getPopulationsize(); ++i){
			//Create init population
		}
		
		//Create EvolverState object 
		EvolverState es = conf.getEvolverStateInstance();
		es.Initialize(conf, population);
		
		//For each run
		for( int r = 0; r < conf.getNumruns(); ++r){
			//For each generation
			for( int g = 0; g < conf.getNumgenerations(); ++g ) {
				//TODO :Fitness test for each population member
 				
				
				
				
				
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
					Unit newUnit = cross.Cross(A, B);
					population.add(newUnit);
				}
				
				for( int m = 0; m < conf.getMutationrate() * population.size(); ++m) {
					Mutator mut = conf.getModifiers().getMutator();
					mut.Mutate(population.get(_rand.nextInt(population.size())));
				}
				
		  		//For each Generational EvolverStateProcess e
				for( EvolverStateProcess e : conf.getGenerationProcesses()){
					e.Process(es);
				}
			}
		 	//For each Run EvolverStateProcess e
			for( EvolverStateProcess e : conf.getRunProcesses()){
				e.Process(es);
			}
		}	
	}	
}
