package RunGEPClassification;

import modifiers.BasicModificationSet;
import GEPClassify.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Create Training Set
		ReadIrisDataSet rids = new ReadIrisDataSet();
		rids.LoadDataSet();
		
		//Create Config
		GEPConfig conf = new BasicGEPConfig();
		conf.setNumNodes(2);
		conf.setNumCells(3);
		conf.setHeadLength(5);
		conf.setMaxGenerations(10);
		conf.setPopulationSize(50);
		conf.setModificationSet( new BasicModificationSet() );
		System.out.println(conf);
		
		System.out.println("");
		/*
		KarvaString k = new KarvaString(conf, ds);
		
		k.Randomize();
		//k.setKarva("+-+baccaa*acbbaD+abab**aaaaa");
		System.out.println(k.getTotalKarva());
		MutationMechanism m = new RandomReplacement();
		
		KarvaString mutated = m.Mutate(k);
		mutated = m.Mutate(mutated);
		mutated = m.Mutate(mutated);
		mutated = m.Mutate(mutated);
		System.out.println(mutated.getTotalKarva());
		
		ExpressedKarva ek = new ExpressedKarva(k);
		
		Double[] values = {4.0, 3.0, 2.0, 1.0};

		Double[] outputs = ek.getResults(values);
		
		
		/*
		for( int i = 0; i < outputs.length; ++i) {
			System.out.print(outputs[i] + ", ");
		}
		System.out.println("");
		*/
		//Create Evolver
			
		GEPEvolver evolver = new BasicGEPEvolver();
		evolver.setGEPConfig(conf);
		evolver.setTrainingSet(rids.GetTrainingSet());
		evolver.setTestSet(rids.GetTestingSet());
		//Run Evolver
		evolver.RunGeneticAlgorithm();	
		
		//Try classifier out on test data
		
		
		
		
		System.out.println("Done");
	}

}
