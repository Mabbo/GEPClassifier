package modifiers;

import framework.Gene;
import framework.Genome;
import framework.Karva;
import framework.Mutator;
import framework.Utilities;

public class GeneSwap implements Mutator {

	//Swap two randomly chosen genes from the same layer
	public void Mutate(Karva karva) {
		
		Genome g = karva.getGenome();
		int selectedLayer = Utilities.getRandomInt(g.getNumNodeLayers() + 1);
		int geneAIndex = Utilities.getRandomInt(g.getNodesInLayer(selectedLayer));
		int geneBIndex = geneAIndex;
		while( geneBIndex == geneAIndex ) {
			geneBIndex = Utilities.getRandomInt(g.getNodesInLayer(selectedLayer));
		}
		//geneA and geneB are now different values
		int first = (geneAIndex < geneBIndex? geneAIndex : geneBIndex);
		int second = (geneAIndex < geneBIndex? geneBIndex : geneAIndex);
		geneAIndex = first;
		geneBIndex = second;

		Gene geneA = g.getGene(selectedLayer, geneAIndex);
		Gene geneB = g.getGene(selectedLayer, geneBIndex);
		String newGeneADNA = geneA.getDNA();
		geneA.setDNA(geneB.getDNA());
		geneB.setDNA(newGeneADNA);
		g.RecheckDNA();
	}

}
