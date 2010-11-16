package modifiers;

import java.util.Random;

import GEPClassify.KarvaString;
import GEPClassify.KarvaUtilities;

public class RandomReplacement implements MutationMechanism {

	private static Random rand = new Random();
	private final int FACTOR = 25;
	
	public void Mutate(KarvaString karva) {
		//Take a randomly chosen piece of the karva, and 
		//change it into a randomly chosen character 
		//taken from valid values
		//do this as a function of how many letters are in the string
		
		String kstring = karva.getTotalKarva();
		
		int numberOfMutations = rand.nextInt( kstring.length() / FACTOR );
		
		for( int i = 0; i < numberOfMutations; ++i) {
			//pick where to mutate
			int where = rand.nextInt(kstring.length());
			//what are the valid options for this character?
			int cellStart = (karva.getNodeLength() * karva.getNumNodes());
			boolean isNode = (where < cellStart);
			//it's either in the node or the cell section
		
			//which node or cell is it?
			int sectionNum = -1;
			int startOfSection = -1;
			if( isNode ) {
				sectionNum = where / karva.getNodeLength();
				startOfSection = sectionNum * karva.getNodeLength();
			} else {
				sectionNum = (where - cellStart) / karva.getCellLength();
				startOfSection = (sectionNum * karva.getCellLength())
								 + cellStart;
			}
			boolean isHead = (where - startOfSection) < karva.getHeadLength();		
			String options = "";
			if( isHead ){
				options += karva.getSymbols();
			}
			if( isNode ) {
				options += karva.getNodeTerminals();
			} else{
				options += karva.getCellTerminals();
			}
			
			kstring = kstring.substring(0, where) +
					  KarvaUtilities.getRandChar(options) +
					  kstring.substring(where+1);
			/*
			System.out.println(karva.getTotalKarva() + " -> " + kstring);
			System.out.println("where: " + where + ", isNode: " + isNode + ", isHead: " + isHead);
			System.out.println("cellStart: " + cellStart + ", sectionNum: " + sectionNum + ", startOfSection: " + startOfSection );
			*/
			
		}
		karva.setKarva(kstring);
	}

}
