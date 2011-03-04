package builtin;

import base.EvolverState;
import base.Instance;
import base.Unit;
import framework.EvolverStateProcess;

public class AddOneOnCorrect implements EvolverStateProcess {
	//Used for fitness tests
	
	
	public void Process(EvolverState es) {
		//For each member of the population
		for( Unit u : es.getPopulation() ){
		//set fitness to 0
			u.setFitnessScore(0);
		//For each test in the training set
			for( Instance inst : es.getTrainingSet().getInstances() ){
		//If the member correctly identifies class, add one to fitness
				int guess = u.Classify(inst);
				if( guess == inst.getClassValue() ) {
					u.incrementFitness(1);
				}
			}
		}
	}

}
