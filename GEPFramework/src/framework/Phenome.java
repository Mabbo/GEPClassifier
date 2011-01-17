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
	Genome _genome;
	Phene[][] _nodephenes;
	Phene[] _cellphenes;
	
	public Phenome(Genome genome){
		_genome = genome;
	}
	
	public void Initialize(){
		if( _initialized ) return;
		
		//Need to create Phenes for each Gene, arrange them correctly
		//Create phene layers
		_nodephenes = new Phene[_genome.getNumNodeLayers()][];
		for( int i = 0; i < _genome.getNumNodeLayers(); ++i) {
			_nodephenes[i] = new Phene[_genome.getNodesInLayer(i)];
			
			for( int j = 0; j < _genome.getNodesInLayer(i); ++j) {
				_nodephenes[i][j] = new Phene(_genome.getGene(i, j), this,
						_genome.getNodeFunctionSet(), 
						(i==0? _genome.getNumberOfInputs() 
							 : _genome.getNodesInLayer(i-1)) );
			}
		}
		_cellphenes = new Phene[_genome.getNumCells()];
		for( int j = 0; j < _genome.getNumCells(); ++j) {
			_cellphenes[j] = 
				new Phene(_genome.getGene(_genome.getNumNodeLayers(), j), 
				this, _genome.getCellFunctionSet(), 
				_genome.getNodesInLayer(_genome.getNumNodeLayers()-1));
		}
		
		_initialized = true;
	}
	
	public int Classify(double[] inputVec) {
		if(!_initialized) return -1;

		double[] inputs = inputVec;
		double[] outputs;
		//for each layer
		for( int i = 0; i < _genome.getNumNodeLayers(); ++i) {
			//for each phene
			outputs = new double[_genome.getNodesInLayer(i)];
			for( int p = 0; p < _genome.getNodesInLayer(i); ++p){
				//give it the current inputs
				outputs[p] = _nodephenes[i][p].getOutput(inputs);
				//put it's result into an output layer
			}
			//set the input layer to output
			inputs = outputs;
		}
		//for each cell-phene
		outputs = new double[_genome.getNumCells()];
		for( int c = 0; c < _genome.getNumCells(); ++c) {
			//give the input vector to the phene
			outputs[c] = _cellphenes[c].getOutput(inputs);
		}
		//find which class has highest value, return that class
		int maxClass = -1;
		double maxScore = -999999999.9;
		for( int i = 0; i < _genome.getNumCells(); ++i){
			if( outputs[i] > maxScore ) {
				maxClass = i; maxScore = outputs[i];
			}
		}
		
		return maxClass;
	}
	
	
	
	
	
	
}
