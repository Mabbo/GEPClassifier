package framework;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Phene Class
 * 
 * The full expression of a gene. While a gene is merely a
 * string of characters, the phene is built from its design,
 * and acts as a node in the NN. 
 * 
 * Given a gene, represents a expression tree, and finds an output
 * based on the given inputs.
 * 
 * Needs knowledge of the config to get correct functions
 * 
 */
@SuppressWarnings("unused")
public class Phene {
	Phenome _phenome;
	Gene _gene;
	aNode root = null;
	
	public Phene(Gene gene, Phenome phenome){
		_phenome = phenome;
		_gene = gene;
	}
	
	double getOutput(double[] inputs){
		
	
		return 0.0;
	}
	
	private void BuildTree(){
		LinkedList<FunctionNode> worklist = new LinkedList<FunctionNode>();
		
		int readItt = 0;
		
		do {
			//read the next character
			String nextChar = _gene.getCharAt(readItt);
			//if the character is a terminal, make a terminal node
			
			
			//if the character is a function, make a function node
			// and add the new function node to the worklist
			
			//if this root is null, set this to the root (first node made)
			
			//if this isn't the root node, get the first node from the worklist
			//add this node as a child to the worklist function node. If that
			//node has enough children now, remove it from the worklist.
			
			
		} while(!worklist.isEmpty());
		
		
	}
	
	
	private abstract class aNode{
		public abstract double getValue();
	}
	private class FunctionNode extends aNode{
		public ArrayList<aNode> children = new ArrayList<aNode>();
		public Function function;
		public int getNumChildren() { return function.getNumArgs();}
		
		public FunctionNode(Function func){
			function = func;
		}
		
		public double getValue() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	private class TerminalNode extends aNode{
		private String _terminal;
		public TerminalNode(String terminal){
			_terminal = terminal;
		}
		public String getTerminal(){return _terminal;}
		
		public static final double NO_VALUE = -45000001;  
		private double _value = NO_VALUE;
		public void setValue(double value) { _value = value; }
		public double getValue() { return _value;}
	}

}
