package dataset;

public class ClassificationInstance {
	
	private DataSet _myDataSet;
	private Double[] _data;
	private int _classification;
	
	public DataSet getDataSet() { return _myDataSet;}
	public Double[] getData() {return _data;}
	public double getData(int i) {return _data[i];}
	public int getClassification() { return _classification;}
	
	public void setData(Double[] d) {_data = d;}
	
	public ClassificationInstance(DataSet d) {
		_classification = -1;
		_myDataSet = d;
	}
	public ClassificationInstance(DataSet d, Double[] data, int classification) {
		_myDataSet = d;
		_data = data;
		_classification = classification;
	}
	public ClassificationInstance(DataSet d, Double[] data, String classification) {
		_myDataSet = d;
		_data = data;
		_classification = _myDataSet.getClassNumber(classification);
	}
	
	public boolean Classify( int classification ) {
		return (classification == _classification);
	}
}
