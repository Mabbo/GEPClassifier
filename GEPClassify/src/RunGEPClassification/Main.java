package RunGEPClassification;

import dataset.DataSet;
import GEPClassify.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Create Training Set
		ReadIrisDataSet rids = new ReadIrisDataSet();
		rids.LoadDataSet();
		
		DataSet ds = rids.GetTrainingSet();
		
		//Create Config
		GEPConfig conf = new BasicGEPConfig();
		conf.setNumNodes(6);
		conf.setNumCells(3);
		conf.setHeadLength(25);
		
		System.out.println(conf);
		
		System.out.println("");
		
		KarvaString k = new KarvaString(conf, ds);
		
		k.Randomize();
		//k.setKarva("+-+baccaa*acbbaD+abab**aaaaa");
		System.out.println(k.getTotalKarva());
		
		ExpressedKarva ek = new ExpressedKarva(k);
		
		Double[] values = {4.0, 3.0, 2.0, 1.0};

		Double[] outputs = ek.getResults(values);
		
		for( int i = 0; i < outputs.length; ++i) {
			System.out.print(outputs[i] + ", ");
		}
		System.out.println("");
		
		//Create Evolver
		
		//Run Evolver
		
		//Try classifier out on test data
		
		
		
		
		System.out.println("Done");
	}

}
