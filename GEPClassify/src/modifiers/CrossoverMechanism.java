package modifiers;

import GEPClassify.KarvaString;

/**
 * Has one function, taking in two karva strings
 * and returning a new, child karva string * 
 * 
 * @author Morley Abbott
 *
 */

public interface CrossoverMechanism {
	void Crossover(KarvaString parentA, KarvaString parentB);
}
