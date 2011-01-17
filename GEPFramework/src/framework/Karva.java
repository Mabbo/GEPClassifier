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
	private GEPConfig _conf;
	private int _fitness;
	public Karva(GEPConfig conf, Genome genome) {
		_conf = conf;
		_genome = genome;
	}
	
	//copy constructor
	public Karva(Karva other) {
		_genome = new Genome(_conf);
		_genome.Initialize(other._genome.getDNA());
	}
	
	public void setGenome(Genome g){
		_genome = g;
	}
	public Genome getGenome(){
		return _genome;
	}
	
	public void Initialize() {
		_phenome = new Phenome(_genome);	
	}
	
	int Classify(Instance inst){
		_phenome.Initialize();
		double[] inputs = inst.getValues();
		return _phenome.Classify(inputs);
	}
	
	void setFitness(int value) {
		_fitness = value;
	}
	double getFitness() {
		return _fitness;
	}

	public String getDNA(){
		return _genome.getDNA();
	}
	
	public GEPConfig getConfig() {
		return _conf;
	}
}
