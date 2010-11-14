package modifiers;

import GEPClassify.KarvaString;
import GEPClassify.KarvaUtilities;

public class OnePointRecombination implements CrossoverMechanism {

	public void Crossover(KarvaString parentA, KarvaString parentB) {
		int point = KarvaUtilities.getRand( parentA.getLength() - 2) + 1;
		
		String resultA = parentA.getTotalKarva().substring(0, point)
					  + parentB.getTotalKarva().substring(point);
		String resultB = parentB.getTotalKarva().substring(0, point)
		  			  + parentA.getTotalKarva().substring(point);
		parentA.setKarva(resultA);
		parentB.setKarva(resultB);
	}

}
