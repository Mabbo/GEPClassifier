package IrisGEPClassification;

import modifiers.BasicModificationSet;
import modifiers.HeadInverter;
import modifiers.MakeComplex;
import modifiers.OnePointRecombination;
import modifiers.RandomReplacement;
import modifiers.SwapChromosomes;

public class IrisModificationSet extends BasicModificationSet {

	public IrisModificationSet(){
		super();
	}
	
	public void LoadModifiers(){
		super.LoadModifiers();
		/*AddMutator(new RandomReplacement(), 0.3);
		AddCrossover(new OnePointRecombination(), 0.4);
		*/
		AddMutator(new RandomReplacement(), 0.3);
		AddMutator(new HeadInverter(), 0.1);
		AddMutator(new MakeComplex(), 0.15);
		AddCrossover(new OnePointRecombination(), 0.25);
		AddCrossover(new SwapChromosomes(), 0.2);
	}
	
}
