package gepbasic;

import framework.*;

public class TestMain {

	public static void main(String[] args) {

		System.out.println("Loading Config File");
		GEPConfig conf = new BasicGEPConfig();
		conf.LoadConfig("../gep_sample.config");
		System.out.println("Config Loaded");
	
		System.out.println("Beginning Evolver");
		Evolver evolver = new BasicEvolver();
		Karva result = evolver.EvolveClassifier(conf);
		
		
		System.out.println(result.toString());	
		
		System.out.println("Done");
	}

}
