import base.EvolverState;
import framework.EvolverStateProcess;


public class ModulatingMutation implements EvolverStateProcess {

	
	int bestScore = -100000;
	int timeSinceLastIncrease = 0;
	final double minMutation = 0.25;
	final double maxMutation = 10.0;
	int maxTime = 50;
	
	public void Process(EvolverState es) {
		int best = es.getPopulation().get(0).getFitnessScore();
		if( best > bestScore ){
			bestScore = best;
			timeSinceLastIncrease = 0;
		} else {
			timeSinceLastIncrease++;
		}
		
		double mutRate = minMutation + (((maxMutation-minMutation) / maxTime) * timeSinceLastIncrease);
		
		if( mutRate > maxMutation){
			timeSinceLastIncrease = 0;
		}
		es.getConfig().setMutationrate(mutRate);
		System.out.println("Mutation Rate: " + mutRate);
		
	}

}
