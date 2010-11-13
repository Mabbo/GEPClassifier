package dataset;

public interface DataSetLoader {
	public void LoadDataSet();
	public DataSet GetTrainingSet();
	public DataSet GetTestingSet();
}
