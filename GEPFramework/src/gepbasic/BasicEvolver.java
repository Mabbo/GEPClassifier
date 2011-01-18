package gepbasic;

import java.util.LinkedList;

import dataset.NumericalDataFileLoader;

import framework.*;

public class BasicEvolver implements Evolver {

	public Karva EvolveClassifier(GEPConfig conf) {
		
		//Create population of Karvas based on config specs
		LinkedList<Karva> population = new LinkedList<Karva>();
		int popSize = conf.getPopulationSize();
		for( int i = 0; i < popSize; ++i) {
			Genome g = new Genome(conf);
			g.InitializeRandom();
			Karva k = new Karva(conf, g);
			population.push(k);
		}
		
		//Load Dataset, get training and test set
		
		//DataSetLoader dsl = new NumericalDataFileLoader();
		//dsl.LoadData(filename, trainingSet)
		
		//Run GA
		
		
		
		
		
		return null;
	}

}
