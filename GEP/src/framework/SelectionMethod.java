package framework;

import base.EvolverState;


public interface SelectionMethod {
	void RemovePopulation(double keepFraction, EvolverState es);
}
