package GEPClassify;

import dataset.DataSet;

public class KarvaString {
	
	private GEPConfig _config;
	private DataSet _dataset;
	private String _karva;
	
	public KarvaString(GEPConfig conf, DataSet d){
		_config = conf;
		_dataset = d;
	}
	
	public KarvaString(GEPConfig conf, DataSet d, String k){
		_config = conf;
		_dataset = d;
		_karva = k;
	}
	
	public KarvaString(KarvaString other){
		this._karva = new String(other._karva);
		this._config = other._config;
		this._dataset = other._dataset;
	}
	
	public GEPConfig getConfig() {return _config;}
	public DataSet getDataSet() {return _dataset;}
	
	public void setKarva(String k){
		_karva = k;
	}
	
	private static int headLength = -1;
	public int getHeadLength() {
		if( headLength == -1)
			headLength = _config.getHeadLength();
		return headLength;
	}
	private static int tailLength = -1;
	public int getTailLength() {
		if( tailLength == -1 )
			tailLength = _config.getTailLength();
		return tailLength;
	}
	private static int numNodes = -1;
	public int getNumNodes() {
		if(numNodes == -1)
			numNodes =  _config.getNumNodes();
		return numNodes;
	}
	private static int numCells = -1;
	public int getNumCells() {
		if( numCells == -1 )
			numCells = _config.getNumCells();
		return numCells;
	}
	private static int nodeLength = -1;
	public int getNodeLength() {
		if( nodeLength == -1)
			nodeLength = _config.getHeadLength() + _config.getTailLength();
		return nodeLength;
	}
	public int getCellLength() {
		return getNodeLength();
	}
	
	public String getTotalKarva() {
		return _karva;
	}
	
	public int getLength() {
		return _karva.length();
	}
	
	public String getNode(int nodeNum){
		int start = nodeNum * getNodeLength();
		return _karva.substring(start, start + getNodeLength());
	}
	
	public String getCell(int cellNum){
		int start = getNodeLength() * getNumNodes()
			      + getCellLength() * cellNum;
		return _karva.substring(start, start + getCellLength());
	}
	
	public void Randomize() {
		_karva = "";
		// create the node head-set, symbols + (terminals = inputs)
		String nodeTerminalSet = KarvaUtilities.getTerminalSet(
				   					_dataset.getNumParameters());
		String nodeHeadSet = _config.getFunctionSet().GetSymbols() +
							 nodeTerminalSet;
		// for each node
		for( int i = 0; i < getNumNodes(); ++i) {
			// randomly fill the head
			for( int h = 0; h < getHeadLength(); ++h ) {
				_karva += KarvaUtilities.getRandChar(nodeHeadSet);
			}
			// randomly fill the tail with the terminal set
			for( int t = 0; t < getTailLength(); ++t ) {
				_karva += KarvaUtilities.getRandChar(nodeTerminalSet);
			}
		}
		// create the cell head-set, symbols + (terminals = nodes)
		String cellTerminalSet = KarvaUtilities.getTerminalSet(getNumNodes());
		String cellHeadSet = _config.getFunctionSet().GetSymbols() +
							 cellTerminalSet;
		// for each cell
		for( int i = 0; i < getNumCells(); ++i){
			// randomly fill the head
			for( int h = 0; h < getHeadLength(); ++h ) {
				_karva += KarvaUtilities.getRandChar(cellHeadSet);
			}
			// randomly fill the tail with the terminal set
			for( int t = 0; t < getTailLength(); ++t ) {
				_karva += KarvaUtilities.getRandChar(cellTerminalSet);
			}
		}
	}
	
	public String getSymbols() {
		return _config.getFunctionSet().GetSymbols();
	}
	public String getNodeTerminals() {
		return KarvaUtilities.getTerminalSet(
				_dataset.getNumParameters());
	}
	public String getCellTerminals() {
		return KarvaUtilities.getTerminalSet(getNumNodes());
	}
	
}
