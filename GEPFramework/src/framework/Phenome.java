package framework;
/**
 * As Genome is to Gene, Phenome is to Phene. 
 * 
 * This is the full set of expressed Phenes. In a sense, this 
 * is the Neural Network. It will take in the input vector of 
 * doubles, and return the index of the classification with the
 * highest score.  
 * 
 * Responsibilities:
 * -contain layers of nodes and an output layer of cells
 * -provide those nodes with knowledge of what functions
 *  and terminals they have available
 * -provide classifictions based on initial input vectors
 * 
 * @author mabbo
 *
 */
public class Phenome {	
	boolean _initialized = false;
	
	public Phenome(Genome genome){
		
	}
	
	public void Initialize(){
		if( _initialized ) return;
		
		
		_initialized = true;
	}
	
	public int Classify(double[] inputs) {
		

		return -1;
	}
	
	
	
	
	
	
}
