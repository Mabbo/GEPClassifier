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
	String MakeChild(KarvaString parentA, KarvaString parentB);
}
