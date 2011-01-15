package framework;
/**
 * Crossover 
 * 
 * Responsibilities:
 * Given two Karvas, return a new, child Karva with traits
 * of both parent Karvas.
 * 
 * @author mabbo
 *
 */
public interface Crossover {
	public Karva Cross(Karva parentA, Karva parentB);
}
