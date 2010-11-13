package GEPClassify;

public class KarvaString {
	
	private GEPConfig _config;
	private String _karva;
	
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
	
	public String getNode(int nodeNum){
		int start = nodeNum * getNodeLength();
		return _karva.substring(start, start + getNodeLength());
	}
	public String getCell(int cellNum){
		int start = getNodeLength() * getNumNodes()
			      + getCellLength() * cellNum;
		return _karva.substring(start, start + getCellLength());
	}
	
}
