package modifiers;

/**
 * Just as FunctionSet is a static set of functions,
 * the ModificationSet is a static set of modifiers,
 * both mutators and crossovers
 * 
 * @author Morley Abbott
 *
 */

public interface ModificationSet {
	void LoadModifiers();
	void AddMutator(MutationMechanism m, Double probability);
	void AddCrossover(CrossoverMechanism m, Double probability);
	
	MutationMechanism GetMutator(int index);
	Double GetMutatorProbability(int index);
	int getMutatorCount();
	CrossoverMechanism GetCrossover(int index);
	Double GetCrossoverProbability(int index);
	int getCrossoverCount();
	
}



