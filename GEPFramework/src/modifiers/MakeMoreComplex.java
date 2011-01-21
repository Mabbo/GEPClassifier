package modifiers;

import framework.Gene;
import framework.Genome;
import framework.Karva;
import framework.Mutator;
import framework.Utilities;

public class MakeMoreComplex implements Mutator {

	//Mutate the head to have more functions in it
	public void Mutate(Karva karva) {

		Genome g = karva.getGenome();
		int selectedLayer = Utilities.getRandomInt(g.getNumNodeLayers() + 1);
		int geneAIndex = Utilities.getRandomInt(g.getNodesInLayer(selectedLayer));

		Gene gene = g.getGene(selectedLayer, geneAIndex);
		boolean isNode = selectedLayer < g.getNumNodeLayers();
		String symbols = (isNode? g.getNodeFunctionSet().getSymbols()
								: g.getCellFunctionSet().getSymbols());
		int headSize = (isNode? g.getNodeHeadLength() 
							  : g.getCellHeadLength());
		int pick = Utilities.getRandomInt(headSize);
		int symbolpick = Utilities.getRandomInt(symbols.length());
		String pickedSymbol = "" + symbols.charAt(symbolpick);
		
		String newDNA = gene.getDNA().substring(0, pick)
					  + pickedSymbol 
					  + gene.getDNA().substring(pick+1);
		gene.setDNA(newDNA);
		
		g.RecheckDNA();
	}
}
