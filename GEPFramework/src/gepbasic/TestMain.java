package gepbasic;

import framework.*;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GEPConfig conf = new GEPConfig();
		conf.LoadConfig("../gep_sample.config");
	
		System.out.println(conf.toString());
		
		Genome g = new Genome(conf);
		g.InitializeRandom();
		Phenome p = new Phenome(g);
		
		p.Initialize();
		
		double[] inputs = {4.0, 3.0, 2.0, 1.0};
		int val = p.Classify(inputs);
		//double val = p.getOutput(inputs);
		System.out.println(val);
		
		System.out.println("Done");
	}

}
