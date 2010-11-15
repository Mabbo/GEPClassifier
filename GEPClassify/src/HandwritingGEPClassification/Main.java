package HandwritingGEPClassification;

import modifiers.ModificationSet;
import GEPClassify.BasicGEPConfig;
import GEPClassify.BasicGEPEvolver;
import GEPClassify.GEPConfig;
import GEPClassify.GEPEvolver;
import IrisGEPClassification.IrisModificationSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Create Training Set
		ReadHandwritingDataSet rhwds = new ReadHandwritingDataSet();
		rhwds.LoadDataSet();
		
		//Create Config
		GEPConfig conf = new BasicGEPConfig();
		conf.setNumNodes(10);
		conf.setNumCells(26);
		conf.setHeadLength(5);
		conf.setMaxGenerations(1000);
		conf.setPopulationSize(50);
		ModificationSet modset = new IrisModificationSet();
		modset.LoadModifiers();
		conf.setModificationSet( modset );
		System.out.println(conf);
		
		System.out.println("");
	

		GEPEvolver evolver = new BasicGEPEvolver();
		evolver.setGEPConfig(conf);
		evolver.setTrainingSet(rhwds.GetTrainingSet());
		evolver.setTestSet(rhwds.GetTestingSet());
		//Run Evolver
		evolver.RunGeneticAlgorithm();	
		
		//Try classifier out on test data
		
		
		
	}
}
	
