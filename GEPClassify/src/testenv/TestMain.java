package testenv;

import dataset.DataSet;
import modifiers.*;
import GEPClassify.*;
import HandwritingGEPClassification.*;
import IrisGEPClassification.*;
import functionsets.*;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ReadIrisDataSet rids = new ReadIrisDataSet();
		rids.LoadDataSet();
		
		//Create Config
		GEPConfig conf = new BasicGEPConfig();
		conf.setNumNodes(3);
		conf.setNumCells(3);
		conf.setHeadLength(5);
		conf.setMaxGenerations(2);
		conf.setPopulationSize(10);
		ModificationSet modset = new HandWritingModificationSet();
		modset.LoadModifiers();
		conf.setModificationSet( modset );
		System.out.println(conf);
		DataSet ds = rids.GetTrainingSet();
		System.out.println("");
		
		KarvaString k = new KarvaString(conf, ds);
		
		k.Randomize();
		//k.setKarva("+-+baccaa*acbbaD+abab**aaaaa");
		System.out.println(k.getTotalKarva());
		MutationMechanism m = new MakeComplex();
		
		m.Mutate(k);
		System.out.println(k.getTotalKarva());
		
		
		/*
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
		/*	
		GEPEvolver evolver = new BasicGEPEvolver();
		evolver.setGEPConfig(conf);
		evolver.setTrainingSet(rids.GetTrainingSet());
		evolver.setTestSet(rids.GetTestingSet());
		//Run Evolver
		evolver.RunGeneticAlgorithm();	
		*/
		//Try classifier out on test data
		
		
		
		
		System.out.println("Done");	


	}

}
