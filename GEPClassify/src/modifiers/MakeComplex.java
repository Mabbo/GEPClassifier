package modifiers;

import GEPClassify.KarvaString;
import GEPClassify.KarvaUtilities;

public class MakeComplex implements MutationMechanism {

	/*
	 * Purpose: Make things more complex by replacing a terminal
	 * in the head with a symbol instead
	 * 
	 * */

	public void Mutate(KarvaString karva) {
		String kstring = karva.getTotalKarva();
		//Find a node or cell
		int options = karva.getNumCells() + karva.getNumNodes();
		int pick = KarvaUtilities.getRand(options);
		boolean isNode = pick < karva.getNumNodes();
		//find the head
		
		int headStart = -1;
		int headEnd = -1;
		if( isNode ) {
			headStart = karva.getNodeLength() * pick;
			headEnd = headStart + karva.getHeadLength();
		} else{
			int cellStart = karva.getNodeLength() * karva.getNumNodes();
			headStart = karva.getCellLength() * (pick - karva.getNumNodes())
					  + cellStart;
			headEnd = headStart + karva.getHeadLength();
		}
		
		String head = kstring.substring(headStart, headEnd);
		
		int position = KarvaUtilities.getRand(head.length());
		String terminals = (isNode? karva.getNodeTerminals() : 
									karva.getCellTerminals());
		String symbols = karva.getSymbols();
		//is this a terminal?
		
		if( terminals.indexOf(head.charAt(position) ) > -1 ) {
			head = head.substring(0, position) +
				   KarvaUtilities.getRandChar(symbols) +
				   (position < head.length()? head.substring(position+1) : "");
		}

		kstring = kstring.substring(0, headStart) + head + kstring.substring(headEnd);
		karva.setKarva(kstring);	
	}

}
