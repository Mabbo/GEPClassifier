package framework;


/**
 * Genome: the full set of genes for the NN. 
 * 
 * Must contain all the genes, organized as nodes and cells.
 * 
 * @author mabbo
 *
 */
public class Genome {
	private GEPConfig _conf;
	private String _fulldna;
	private Gene[][] _genes;
	
	public Genome(GEPConfig conf) {
		_conf = conf;	
	}
	
	public void InitializeRandom(){
		//create a full random dna string to initialize with
		String genomedna = "";
		for( int i = 0; i < _conf.getNodeLayers(); ++i){
			int numTerminals = (i==0? _conf.getNumberOfInputs()
					: _conf.getNumNodes(i-1));
			for( int j = 0; j < _conf.getNumNodes(i); ++j){
				genomedna += Utilities.CreateDNA(_conf.getNodeHeadLength(), 
					numTerminals, _conf.getNodeFunctionSet());
			}
		}
		int numTerminals = _conf.getNumNodes(_conf.getNodeLayers()-1);
		for( int j = 0; j < _conf.getNumberOfClasses(); ++j){
			genomedna += Utilities.CreateDNA(_conf.getCellHeadLength(), 
				numTerminals, _conf.getCellFunctionSet());
		}
		_fulldna = genomedna;
		_initialize();
	}
	public void Initialize(String dna){
		_fulldna = dna;
		_initialize();
	}
	
	private void _initialize() {
		//_fulldna should be filled with something valid
		int dnaitt = 0;
		int nodeLength = _conf.getNodeLength();
		int cellLength = _conf.getCellLength();
		
		_genes = new Gene[_conf.getNodeLayers()+1][];
		//initialize the Node layer genes
		for( int i = 0; i < _conf.getNodeLayers(); ++i){
			_genes[i] = new Gene[_conf.getNumNodes(i)];
			for( int j = 0; j < _genes[i].length; ++j) {
				String dna = _fulldna.substring(dnaitt, dnaitt + nodeLength);
				dnaitt += nodeLength;
				_genes[i][j] = new Gene(dna);
			}
		}
		//initialize the cell genes
		_genes[_conf.getNodeLayers()] = new Gene[_conf.getNumberOfClasses()];
		for( int k = 0; k < _genes[_conf.getNodeLayers()].length; ++k){
			String dna = _fulldna.substring(dnaitt, dnaitt + cellLength);
			dnaitt += cellLength;
			_genes[_conf.getNodeLayers()][k] = new Gene(dna);
		}
				
	}
	
	public String toString() {
		return _fulldna;
	}
	
	public Gene getGene(int layer, int index){
		return _genes[layer][index];
	}
	
	public int getNumNodeLayers() {
		return _conf.getNodeLayers();
	}
	public int getNodesInLayer(int index) {
		return _conf.getNumNodes(index);
	}
	public int getNumCells() {
		return _conf.getNumberOfClasses();
	}
	
}
