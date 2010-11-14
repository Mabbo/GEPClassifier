package modifiers;

import GEPClassify.KarvaString;

/**
 * Has one function, taking in one karva string
 * and returning a mutated version of it.
 * 
 * @author Morley Abbott
 *
 */

public interface MutationMechanism {
	void Mutate(KarvaString karva);
}
