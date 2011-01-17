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
		
		Gene g = new Gene("D*+dcab");
		Phene p = new Phene(g, null, conf.getNodeFunctionSet(), 4);
		
		double[] inputs = {4.0, 3.0, 2.0, 1.0};
		double val = p.getOutput(inputs);
		System.out.println(val);
		
		System.out.println("Done");
	}

}
