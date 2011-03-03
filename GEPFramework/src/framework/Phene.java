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
public class Phene {
	Gene _gene;
	FunctionSet _functions;
	int _terminals = -1;
	String terminalValues = "";
	aNode root = null;
	ArrayList<TerminalNode> terminalNodes;
	
	public Phene(Gene gene, Phenome phenome, FunctionSet functions, int numTerminals){
		_gene = gene;
		_functions = functions;
		_terminals = numTerminals;
		terminalValues = Utilities.getTerminals(_terminals);
		BuildTree();
	}
	
	public double getOutput(double[] inputs){
		for( int i = 0; i < terminalNodes.size(); ++i) {
			TerminalNode tn = terminalNodes.get(i);
			int index = terminalValues.indexOf(tn.getTerminal());
			tn.setValue(inputs[index]);
		}
		return root.getValue();
	}
	
	private void BuildTree(){
		LinkedList<FunctionNode> worklist = new LinkedList<FunctionNode>();
		int readItt = 0;
		terminalNodes = new ArrayList<TerminalNode>();
		
		do {
			//read the next character
			String nextChar = _gene.getCharAt(readItt);
			aNode node;
			
			//if the character is a terminal, make a terminal node
			if( terminalValues.indexOf(nextChar) > -1) {
				TerminalNode tn = new TerminalNode(nextChar);
				node = tn;
				terminalNodes.add(tn);
			} else {
			//if the character is a function, make a function node
			// and add the new function node to the worklist
				Function f = _functions.getFunction(nextChar);
				FunctionNode fn = new FunctionNode(f);
				node = fn;
				worklist.add(fn);
			}
			//if this root is null, set this to the root (first node made)
			if( root == null ) {
				root = node;
			} else{
			//if this isn't the root node, get the first node from the worklist
			//add this node as a child to the worklist function node. If that
			//node has enough children now, remove it from the worklist.
				FunctionNode parent = worklist.getFirst();
				parent.children.add(node);
				if( parent.children.size() >= parent.getNumChildren()){
					worklist.pop();
				}
			}
			readItt++;
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
			Double[] inputs = new Double[function.getNumArgs()];
			for( int i = 0; i < children.size(); ++i) {
				inputs[i] = children.get(i).getValue();
			}
			return function.ApplyFunction(inputs);
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
