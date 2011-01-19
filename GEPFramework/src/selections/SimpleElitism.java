package selections;

import java.util.ArrayList;

import framework.Karva;
import framework.SelectionMethod;

public class SimpleElitism implements SelectionMethod {

	public void RemovePopulation(double keepfraction,
			ArrayList<Karva> population) {

		int numToKeep = (int)(population.size() * keepfraction);
		for( int i = population.size()-1; i >= numToKeep ; --i) {
			population.remove(i);
		}
	}

}
