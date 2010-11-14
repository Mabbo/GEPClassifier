package GEPClassify;

import dataset.DataSet;

/**
 * ExpressedKarva
 * 
 * Given a karva string, makes all the nodes
 * and cells needed for it.
 * When given the inputs, gives all nodes the
 * appropriate inputs, gets their values, and
 * gives them to the cells. Returns an array
 * of doubles, to be used for classification.
 * 
 * @author mabbo
 *
 */
public class ExpressedKarva {
	private KarvaString _karva;
	private PhenoTree[] _nodes;
	private PhenoTree[] _cells;
	private DataSet _dataset;
	
	public ExpressedKarva(KarvaString karva){
		_karva = karva;
		_dataset = karva.getDataSet();
		_nodes = new PhenoTree[_karva.getNumNodes()];
		_cells = new PhenoTree[_karva.getNumCells()];
		Express();
	}
	
	public void Express() {
		String nodeTerminals = 
			KarvaUtilities.getTerminalSet(_dataset.getNumParameters());
		for( int i = 0; i < _nodes.length; ++i){
			_nodes[i] = new PhenoTree(_karva.getNode(i), 
					nodeTerminals, _karva.getConfig());
		}
		String cellTerminals = 
			KarvaUtilities.getTerminalSet(_karva.getNumNodes());
		for( int i = 0; i < _cells.length; ++i){
			_cells[i] = new PhenoTree(_karva.getCell(i), 
					cellTerminals, _karva.getConfig());
		}
	}
	
	public Double[] getResults(Double[] input){
		Double[] nodeResults = new Double[_nodes.length];
		for( int i = 0; i < _nodes.length; ++i) {
			_nodes[i].SetTerminalValues(input);
			nodeResults[i] = _nodes[i].getValue();
		}
		Double[] cellResults = new Double[_cells.length];
		for( int i = 0; i < _cells.length; ++i) {
			_cells[i].SetTerminalValues(nodeResults);
			cellResults[i] = _cells[i].getValue();
		}
		return cellResults;		
	}

	
}




