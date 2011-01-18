package gepbasic;

import java.util.ArrayList;

import framework.Crossover;
import framework.ModificationSet;
import framework.Mutator;
import framework.Utilities;

public class BasicModificationSet implements ModificationSet {

	ArrayList<Mutator> _mutators;
	ArrayList<Double> _mutator_weights;
	double _total_mutator_weight = 0;
	ArrayList<Crossover> _crossovers;
	ArrayList<Double> _crossover_weights;
	double _total_crossover_weight = 0;
	
	public BasicModificationSet() {
		_mutators = new ArrayList<Mutator>();
		_mutator_weights = new ArrayList<Double>();
		_crossovers = new ArrayList<Crossover>();
		_crossover_weights = new ArrayList<Double>();
	}
	
	public void AddCrossover(Crossover crossover, double weight) {
		_crossovers.add(crossover); 
		_crossover_weights.add(weight);
		_total_crossover_weight += weight;
	}

	public void AddMutator(Mutator mutator, double weight) {
		_mutators.add(mutator);
		_mutator_weights.add(weight);
		_total_mutator_weight += weight;
	}

	public Crossover getCrossover(int index) {
		return _crossovers.get(index);
	}

	public double getCrossoverWeight(int index) {
		return _crossover_weights.get(index);
	}

	public Mutator getMutator(int index) {
		return _mutators.get(index);
	}

	public double getMutatorWeight(int index) {
		return _mutator_weights.get(index);
	}

	public Crossover getRandomCrossover() {
		double pick = Utilities.getRandomDouble(_total_crossover_weight);
		for( int i = 0; i < _crossover_weights.size(); ++i) {
			pick -= _crossover_weights.get(i);
			if( pick <= 0) {
				return _crossovers.get(i);
			}
		}
		return null;
	}

	public Mutator getRandomMutator() {
		double pick = Utilities.getRandomDouble(_total_mutator_weight);
		for( int i = 0; i < _mutator_weights.size(); ++i) {
			pick -= _mutator_weights.get(i);
			if( pick <= 0) {
				return _mutators.get(i);
			}
		}
		return null;
	}
	
	public String toString(){
		String retVal = "";
		retVal += "Mutators: ";
		for( int i = 0; i < _mutators.size(); ++i){
			retVal += _mutators.get(i).getClass().getSimpleName();
			retVal += ": " + _mutator_weights.get(i) + ", ";
		}
		retVal += "\n\t\t\tCrossovers: ";
		for( int i = 0; i < _crossovers.size(); ++i){
			retVal += _crossovers.get(i).getClass().getSimpleName();
			retVal += ": " + _crossover_weights.get(i) + ", ";
		}
		return retVal;
	}
	
}
