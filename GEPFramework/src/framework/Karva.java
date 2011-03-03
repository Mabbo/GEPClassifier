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
@SuppressWarnings("unchecked")
public class Karva implements Comparable {
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
		_genome = new Genome(other.getConfig());
		_conf = other.getConfig();
		_genome.Initialize(other._genome.getDNA());
		_fitness = other.getFitness();
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
	
	public int Classify(Instance inst){
		_phenome.Initialize();
		double[] inputs = inst.getValues();
		return _phenome.Classify(inputs);
	}
	
	public void setFitness(int value) {
		_fitness = value;
	}
	public void addFitness(int value) {
		_fitness+= value;
	}
	public int getFitness() {
		return _fitness;
	}

	public String getDNA(){
		return _genome.getDNA();
	}
	
	public String toString(){
		return "" + _fitness + ": " + _genome.getDNA();
	}
	
	public GEPConfig getConfig() {
		return _conf;
	}

	public int compareTo(Object arg0) {		
		Karva other = (Karva)arg0;
		assert(other != null);
		if( other.getFitness() < this.getFitness())
			return -1;
		else 
			return 1;	
	}
}
