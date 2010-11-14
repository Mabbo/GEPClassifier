package GEPClassify;

import java.util.Random;

public class KarvaUtilities {

	public static String getTerminalSet(int numTerminals) {
		String result = "";
		if( numTerminals > 26 ) 
			System.err.println("Used too many terminals!");
		for( int i = 0; i < numTerminals; ++i ) {
			result += String.valueOf((char)('a' + i));
		}
		return result;
	}
	
	private static Random r = new Random();
	public static int getRand(int max){
		return r.nextInt(max);
	}
	public static String getRandChar(String choices) {
		return "" + choices.charAt(getRand(choices.length()));
	}
	
}
