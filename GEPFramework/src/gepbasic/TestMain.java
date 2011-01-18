package gepbasic;

import framework.*;

public class TestMain {

	public static void main(String[] args) {

		GEPConfig conf = new BasicGEPConfig();
		conf.LoadConfig("../gep_sample.config");
	
		Evolver evolver = new BasicEvolver();
		Karva result = evolver.EvolveClassifier(conf);
		
		System.out.println(result.getDNA());
		
		
		/*
		System.out.println(conf.toString());
		
		Genome g = new Genome(conf);
		g.InitializeRandom();
		Karva k = new Karva(conf, g);
		
		k.Initialize();
		
		System.out.println(k.getDNA());
		RandomReplacement rr = new RandomReplacement();
		rr.Mutate(k);
		System.out.println(k.getDNA());
		
		//double[] inputs = {4.0, 3.0, 2.0, 1.0};
		//int val = p.Classify(inputs);
		//double val = p.getOutput(inputs);
		//System.out.println(val);
		*/
		
		
		
		System.out.println("Done");
	}

}
