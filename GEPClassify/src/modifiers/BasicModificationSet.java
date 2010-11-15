package modifiers;

import java.util.ArrayList;

public class BasicModificationSet implements ModificationSet {
	protected ArrayList<CrossoverMechanism> _crossovers;
	protected ArrayList<MutationMechanism> _mutators;
	protected ArrayList<Double> _crossoverProb;
	protected ArrayList<Double> _mutatorProb;
	
	public void AddCrossover(CrossoverMechanism m, Double probability) {
		_crossovers.add(m);
		_crossoverProb.add(probability);
	}
	public void AddMutator(MutationMechanism m, Double probability) {
		_mutators.add(m);
		_mutatorProb.add(probability);
	}
	
	public CrossoverMechanism GetCrossover(int index) {
		return _crossovers.get(index);
	}
	public Double GetCrossoverProbability(int index) {
		return _crossoverProb.get(index);
	}
	public MutationMechanism GetMutator(int index) {
		return _mutators.get(index);
	}
	public Double GetMutatorProbability(int index) {
		return _mutatorProb.get(index);
	}

	public void LoadModifiers() {
		_mutators = new ArrayList<MutationMechanism>();
		_mutatorProb = new ArrayList<Double>();
		_crossovers = new ArrayList<CrossoverMechanism>();
		_crossoverProb = new ArrayList<Double>();
	}

	public int getCrossoverCount() {
		return _crossovers.size();
	}

	public int getMutatorCount() {
		return _mutators.size();
	}
		
}
