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

	public void LoadData(String filename, DataSet trainingSet, GEPConfig conf) {
	
		int numInputs = trainingSet.getNumberOfInputs();
		int[] ignoredColumns = conf.getIgnoreColumns();
		int classIndex = conf.getClassIndex();
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String tmp;
			tmp = br.readLine();
			while( tmp != null && !tmp.isEmpty()) {
				String[] split = tmp.split(",");
				double[] data = new double[numInputs];
				int x = 0;
				for( int i = 0; i < numInputs; ++i ) {
					if( i == classIndex ) {
					} else if (arrayContains(ignoredColumns, i)) {
					} else {
						data[x] = Double.parseDouble(split[i]);
						x++;
					}
				}
				int classNum = trainingSet.getClassNumber(split[classIndex]); 
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
	private static boolean arrayContains(int[] array, int number) {
		for( int i = 0; i < array.length; ++i){
			if( array[i] == number) return true;
		}
		return false;
	}
	
}
