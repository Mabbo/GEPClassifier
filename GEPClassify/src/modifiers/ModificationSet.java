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
	MutationMechanism[] GetMutators();
	CrossoverMechanism[] GetCrossovers();
	void LoadModifiers();
}
