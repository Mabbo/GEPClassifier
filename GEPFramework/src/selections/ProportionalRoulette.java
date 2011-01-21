package selections;

import java.util.ArrayList;
import java.util.Collections;

import framework.Karva;
import framework.SelectionMethod;
import framework.Utilities;

public class ProportionalRoulette implements SelectionMethod {

	public void RemovePopulation(double keepfraction,
			ArrayList<Karva> population) {
		
		int numToRemove = (int)(population.size() * (1.0-keepfraction));
		int n = (population.size());
		int max = (n*n + n + 2)/2;
		
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		while( toRemove.size() < numToRemove) {
			int y = Utilities.getRandomInt(max);
			//This looks like a magic function. It is. 
			//We want there to be 1 chance of selection 0
			//2 chances of selection 1, 3 chances of 2, etc. This function
			//does that. 
			int x = (int)Math.ceil( (0.5)* (Math.sqrt( (8*y) + 1  ) + 1) );
			if( x < n && !toRemove.contains(x) ) {
				toRemove.add(x);
			}
		}
		Collections.sort(toRemove);
		for( int i = toRemove.size()-1; i >= 0; --i) {
			population.remove(toRemove.get(i));
		}
	}
}
