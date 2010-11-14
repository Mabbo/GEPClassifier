package RunGEPClassification;

import java.io.BufferedReader;
import java.io.FileReader;

import dataset.DataSet;
import dataset.DataSetLoader;

public class ReadIrisDataSet implements DataSetLoader {

	private DataSet trainingSet = null;
	private DataSet testingSet = null;
	
	public DataSet GetTestingSet() {
		return trainingSet;
	}

	public DataSet GetTrainingSet() {
		return trainingSet;
	}
	
	public void LoadDataSet() {
		
		String[] classes = {"Iris-setosa", "Iris-versicolor", "Iris-virginica" };
		trainingSet = new DataSet(4, classes );
		
		try {
			FileReader fr = new FileReader("/home/mabbo/datasets/iris.data");
			BufferedReader br = new BufferedReader(fr);
			String tmp;
			tmp = br.readLine();
			while( tmp != null && !tmp.isEmpty()) {
				System.out.println("Loading: " + tmp);
				String[] split = tmp.split(",");
				//There should be 4 elements, plus a class
				Double[] data = new Double[4];
				for( int i = 0; i < 4; ++i ) {
					data[i] = Double.parseDouble(split[i]);
				}
				trainingSet.AddInstance(data, split[4]); 
				tmp = br.readLine();
			}
			br.close();			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
