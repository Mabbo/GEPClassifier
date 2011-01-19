package dataset;

import java.io.BufferedReader;
import java.io.FileReader;

import framework.*;

/*
 * This class loads .data files which only contain numerical data
 * Each line of the file should be n numbers, comma separated, with 
 * a class name at the end
 * 
 * */

public class NumericalDataFileLoader implements DataSetLoader {

	public void LoadData(String filename, DataSet trainingSet) {
	
		int numInputs = trainingSet.getNumberOfInputs();
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String tmp;
			tmp = br.readLine();
			while( tmp != null && !tmp.isEmpty()) {
				String[] split = tmp.split(",");
				double[] data = new double[numInputs];
				for( int i = 0; i < numInputs; ++i ) {
					data[i] = Double.parseDouble(split[i+1]);
				}
				int classNum = trainingSet.getClassNumber(split[0]); 
				Instance inst = new BasicInstance();
				inst.setValues(data);
				inst.setClassValue(classNum);
				trainingSet.AddInstance(inst);
				
				tmp = br.readLine();
			}
			br.close();			
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		}
	}
}
