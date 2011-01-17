package modifiers;

import framework.Crossover;
import framework.Genome;
import framework.Karva;
import framework.Utilities;

public class OnePointRecombination implements Crossover {

	//pick a single point in the DNA string, use that as the
	//swap point. Everything before it from parentA, everything
	//after it from parent B.
	public Karva Cross(Karva parentA, Karva parentB) {
		int totalGeneLength = parentA.getDNA().length();
		int swappoint = Utilities.getRandomInt(totalGeneLength);
		String newDNA = parentA.getDNA().substring(0, swappoint)
					  + parentA.getDNA().substring(swappoint);
		
		Genome genome = new Genome(parentA.getConfig());
		genome.Initialize(newDNA);
		Karva child = new Karva(parentA.getConfig(), genome);
		
		return child;
	}

}
