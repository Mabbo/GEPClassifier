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
	void LoadData(DataSet trainingSet, DataSet testingSet,
				  String filename);
	//Load dataset with specified train/test ratios
	void LoadData(DataSet trainingSet, int trainRatio, 
				  DataSet testingSet,  int testingRatio,
				  String filename);
}



