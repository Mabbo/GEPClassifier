package builtin;

import base.EvolverState;
import framework.SelectionMethod;

public class BasicElitism implements SelectionMethod {

	public void RemovePopulation(double keepFraction, EvolverState es) {
		int toKeep = (int) (keepFraction * es.getConfig().getPopulationsize());
		while( es.getPopulation().size() > toKeep ) {
			es.getPopulation().remove(es.getPopulation().size()-1);
		}
	}

}
