package HandwritingGEPClassification;

import java.io.BufferedReader;
import java.io.FileReader;

import dataset.DataSet;
import dataset.DataSetLoader;

public class ReadHandwritingDataSet implements DataSetLoader {
	private DataSet trainingSet = null;
	private DataSet testingSet = null;
	
	public DataSet GetTestingSet() {
		return testingSet;
	}

	public DataSet GetTrainingSet() {
		return trainingSet;
	}

	public void LoadDataSet() {
		
		String[] classes = { "A", "B", "C", "D", "E", "F", "G", "H",
				"I","J","K","L","M","N","O","P","Q","R","S","T","U",
				"V","W","X","Y","Z"};
		trainingSet = new DataSet(16, classes );
		testingSet = new DataSet(16, classes );
		int count = 0;
		try {
			FileReader fr = new FileReader("/home/mabbo/datasets/letter-recognition.data");
			BufferedReader br = new BufferedReader(fr);
			String tmp;
			tmp = br.readLine();
			while( tmp != null && !tmp.isEmpty()) {
				//System.out.println("Loading: " + tmp);
				String[] split = tmp.split(",");
				//There should be 4 elements, plus a class
				Double[] data = new Double[16];
				for( int i = 0; i < 16; ++i ) {
					data[i] = Double.parseDouble(split[i+1]);
				}
				//if( count % 3 == 0)
					trainingSet.AddInstance(data, split[0]);
				//else
			//		trainingSet.AddInstance(data, split[0]);
			//	count += 1;
				tmp = br.readLine();
			}
			br.close();			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
