package framework;
/**
 * DataSetLoader
 * 
 * Responsibilities:
 * -Given a file, return a DataSet object for training, and 
 * another for testing.
 * 
 * Notes: 
 * Will be implemented by various file-type loaders
 * 
 * @author mabbo
 *
 */
public interface DataSetLoader {
	//Load dataset with default ratios
	void LoadData(String filename, DataSet trainingSet, GEPConfig conf);
}



