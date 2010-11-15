package IrisGEPClassification;

import modifiers.BasicModificationSet;
import modifiers.OnePointRecombination;
import modifiers.RandomReplacement;

public class IrisModificationSet extends BasicModificationSet {

	public IrisModificationSet(){
		super();
	}
	
	public void LoadModifiers(){
		super.LoadModifiers();
		AddMutator(new RandomReplacement(), 0.3);
		AddCrossover(new OnePointRecombination(), 0.4);
	}
	
}
