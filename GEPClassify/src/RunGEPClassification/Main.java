package RunGEPClassification;

import GEPClassify.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Create Training Set
		ReadIrisDataSet rids = new ReadIrisDataSet();
		rids.LoadDataSet();
		
		//Create Config
		GEPConfig conf = new BasicGEPConfig();
		
		//Create Evolver
		
		//Run Evolver
		
		//Try classifier out on test data
		
		
		
		
		System.out.println("Done");
	}

}
