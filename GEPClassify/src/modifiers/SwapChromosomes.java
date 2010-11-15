package modifiers;

import java.util.Random;

import GEPClassify.KarvaString;

public class SwapChromosomes implements CrossoverMechanism {

	static Random rand = new Random();
	
	public void Crossover(KarvaString parentA, KarvaString parentB) {
		//trade one chromosome between two karvas
		
		boolean isNode = rand.nextBoolean();
		int chromoNum = (isNode? rand.nextInt(parentA.getNumNodes()) : rand.nextInt(parentA.getNumCells()));
		int start = (isNode? 0 : parentA.getNodeLength() * parentA.getNumNodes())
				  + (isNode? chromoNum * parentA.getNodeLength() :
					  		 chromoNum * parentA.getCellLength() );
		int length = (isNode? parentA.getNodeLength() : parentA.getCellLength());
		
		String kA = parentA.getTotalKarva();
		String kB = parentB.getTotalKarva();
		
		String resultA = kA.substring(0, start) + 
						 kB.substring(start, start+length) +
						 kA.substring(start + length);
		String resultB = kB.substring(0, start) + 
		 			     kA.substring(start, start+length) +
		 			     kB.substring(start + length);				 

		parentA.setKarva(resultA);
		parentB.setKarva(resultB);
	}

}
