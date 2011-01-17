package modifiers;

import framework.Crossover;
import framework.Karva;

public class CopyParentA implements Crossover {

	//Simply returns a copy of parentA. The Miracle of Cloning!
	public Karva Cross(Karva parentA, Karva parentB) {
		return new Karva(parentA);
	}

}
