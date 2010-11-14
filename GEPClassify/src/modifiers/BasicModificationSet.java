package modifiers;

public class BasicModificationSet implements ModificationSet {
	private CrossoverMechanism[] _crossovers;
	private MutationMechanism[] _mutators;
	
	public CrossoverMechanism[] GetCrossovers() {
		return _crossovers;
	}

	public MutationMechanism[] GetMutators() {
		return _mutators;
	}

	public void LoadModifiers() {
		_mutators = new MutationMechanism[1];
		_mutators[0] = new RandomReplacement();
		
		_crossovers = new CrossoverMechanism[0];		
	}

	public BasicModificationSet() {
		LoadModifiers();
	}
	
}
