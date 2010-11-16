package modifiers;

import GEPClassify.KarvaString;
import GEPClassify.KarvaUtilities;

public class ShiftHeadRight implements MutationMechanism {


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
		
		String newKarva = head.substring(head.length()-1) + head.substring(0,head.length()-1);
		
		kstring = kstring.substring(0, headStart) + newKarva + kstring.substring(headEnd);
		karva.setKarva(kstring);
	}


}
