package selections;

import java.util.ArrayList;
import java.util.Random;

import framework.Karva;
import framework.SelectionMethod;

public class RouletteKeepBest implements SelectionMethod {

	public static Random _random = new Random(System.currentTimeMillis());
	
	public void RemovePopulation(double keepfraction,
			ArrayList<Karva> population) {
		//Assumption: population is already sorted by fitness
		
		int numToRemove = (int) (population.size() * (1.0-keepfraction));
		for( int i = 0; i < numToRemove; ++i) {
			int pick = _random.nextInt(population.size()-1) + 1;
			population.remove(pick);
		}
	}
	public String toString(){
		return "RouletteKeepBest";
	}

}
