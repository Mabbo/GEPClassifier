package framework;
/**
 * ModificationSet
 * 
 * Responsibilities:
 * Manage and contain mutation and crossover operator
 * objects, to be applied to Karva objects, modifying and
 * creating new Karvas.
 * 
 * Also contains weights of each modifier, indicating the 
 * probability of their use. Can be used to get a mutator
 * with a random probability of which one will be returned.
 * 
 * @author mabbo
 *
 */


public interface ModificationSet {
	void AddCrossover(Crossover crossover, double weight);
	void AddMutator(Mutator mutator, double weight);
	
	Mutator getMutator(int index);
	Crossover getCrossover(int index);
	double getMutatorWeight(int index);
	double getCrossoverWeight(int index);

	Mutator getRandomMutator();
	Crossover getRandomCrossover();
	
	
	
}
