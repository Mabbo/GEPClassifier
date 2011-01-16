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
	
	public void Initialize(){
		//create Randomized DNA, call parameterized init
		_genes = new Gene[_conf.getNodeLayers()+1][];
		//initialize the Node layer genes
		for( int i = 0; i < _conf.getNodeLayers(); ++i){
			_genes[i] = new Gene[_conf.getNumNodes(i)];
		}
		//initialize the cell genes
		_genes[_conf.getNodeLayers()] = new Gene[_conf.getNumberOfClasses()];
		
		
		
		
		
	}
	public void Initialize(String dna){
		_fulldna = dna;
		
	}
	
	
	
	
}
