package builtin;

import base.EvolverState;
import framework.EvolverStateProcess;

public class OutputBestScore implements EvolverStateProcess {

	public void Process(EvolverState es) {
		System.out.println("" + es.getGenerationNumber()
				+ ": " + es.getPopulation().get(0).getFitnessScore()
				+ " / " + es.getTrainingSet().size() );
	}

	@Override
	public void Initialize(String parameters) {
		// TODO Auto-generated method stub
		
	}

}
