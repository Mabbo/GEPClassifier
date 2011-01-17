package framework;

import java.util.Random;

public class Utilities {

	public static final String allTerminals = "abcdefghijklmnopqrstuvwxyz";
	public static String getTerminals(int num) {
		return allTerminals.substring(0, num);
	}
	
	//Given the head size, function set, number of terminals, creates a valid karva DNA
	private static Random _random = new Random(System.currentTimeMillis());
	public static String CreateDNA(int headsize, int numterminals, FunctionSet functionset){
		assert(numterminals < 26);
		String possibleTerminals = getTerminals(numterminals);
		String possibbleFunctions = functionset.getSymbols();
		String headSymbols = possibbleFunctions + possibleTerminals;
		
		String dna = "";
		for( int i = 0; i < headsize; ++i ){
			dna += headSymbols.charAt(_random.nextInt(headSymbols.length()));
		}
		int tailsize = getTailLength(headsize, functionset.getMaxArgs());
		for( int i = 0; i < tailsize; ++i ){
			dna += possibleTerminals.charAt(_random.nextInt(possibleTerminals.length()));
		}
		return dna;
	}
	
	public static int getTailLength(int headLength, int maxArgs){	
		//t = h*(MaxArg-1)+1
		return (headLength*(maxArgs-1)) + 1;
	}
	
	public static int getTotalGeneLength(int headLength, int maxArgs){ 
		return headLength + getTailLength(headLength, maxArgs);
	}
	
	public static double getRandomDouble(double max){
		return _random.nextDouble() * max;
	}
	
}
