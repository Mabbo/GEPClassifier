package HandwritingGEPClassification;

import modifiers.*;

public class HandWritingModificationSet extends BasicModificationSet {
	
	public HandWritingModificationSet(){
		super();
	}

	public void LoadModifiers(){
		super.LoadModifiers();
		AddMutator(new RandomReplacement(), 0.2);
		AddMutator(new HeadInverter(), 0.1);
		AddMutator(new MakeComplex(), 0.1);
		AddMutator(new ShiftHeadLeft(), 0.1);
		AddMutator(new ShiftHeadRight(), 0.1);
		AddCrossover(new OnePointRecombination(), 0.2);
		AddCrossover(new SwapChromosomes(), 0.3);
	}
	
	
}
