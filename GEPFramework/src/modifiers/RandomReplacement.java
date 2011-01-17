package modifiers;

import framework.*;

public class RandomReplacement implements Mutator {

	final static int MAX_MUTATIONS = 10;
	
	//changes 1-MAX_MUTATIONS random letters in the genome
	
	public void Mutate(Karva karva) {
		Genome g = karva.getGenome();
		
		int mutations = Utilities.getRandomInt(MAX_MUTATIONS) + 1;
		for( int x = 0; x < mutations; ++x) {
			//pick which gene
			int layer = Utilities.getRandomInt(g.getNumNodeLayers() + 1);
			int pick = -1;
			boolean isNode = layer < g.getNumNodeLayers();
			if( isNode ) {
				pick = Utilities.getRandomInt(g.getNodesInLayer(layer));
			} else {
				pick = Utilities.getRandomInt(g.getNumCells());
			}
			
			Gene gene = g.getGene(layer, pick);
			
			//pick which letter in the gene
			int letter = Utilities.getRandomInt(gene.getDNA().length());
			//We have a gene, a letter in it picked. Is it in the head or tail?
			//How many possible terminals are there?
			int numTerminals = -1;
			if( layer == 0 ) { numTerminals = karva.getConfig().getNumberOfInputs(); }
			else {
				numTerminals = karva.getConfig().getNumNodes(layer-1);
			}
			String letterOptions = Utilities.getTerminals(numTerminals);
			
			//Now, is this in the head, or tail? Depends on if it's a cell or node
			boolean isHead = ( isNode? letter < karva.getConfig().getNodeHeadLength()
									 : letter < karva.getConfig().getCellHeadLength() );
			if( isHead ) {
				//must add function symbols
				letterOptions += (isNode? karva.getConfig().getNodeFunctionSet()
										: karva.getConfig().getCellFunctionSet())
										.getSymbols();			
			}
			//We have all the possible options. Pick one randomly, edit the gene
			int newCharIndex = Utilities.getRandomInt(letterOptions.length());
			String newChar = letterOptions.substring(newCharIndex, newCharIndex+1);
			String newDNA = gene.getDNA();
			newDNA = newDNA.substring(0, letter) + newChar 
				   + newDNA.substring(letter+1);
			gene.setDNA(newDNA);
		}
		g.RecheckDNA();
	}

}
