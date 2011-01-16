package framework;
/**
 * Karva Class
 * 
 * Responsibilities:
 * Contain the Genome and Phenome, and represent a member 
 * of the population. 
 * 
 * @author mabbo
 *
 */
public class Karva {
	private Genome _genome;
	private Phenome _phenome;
	private double _fitness;
	public Karva(GEPConfig conf, Genome genome) {
		_genome = genome;
		_phenome = new Phenome(_genome);
	}
	
	int Classify(Instance inst){
		_phenome.Initialize();
		double[] inputs = inst.getValues();
		return _phenome.Classify(inputs);
	}
	
	void setFitness(double value) {
		_fitness = value;
	}
	double getFitness() {
		return _fitness;
	}

}
