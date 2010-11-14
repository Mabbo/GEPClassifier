package GEPClassify;

import java.util.ArrayList;

import GEPExceptions.IllegalActionException;


/**
 * The phenotype to the Karva's genotype
 * Expresses a Karva string as a single tree
 * 
 * Is only used for one node or cell.
 * 
 * @author mabbo
 *
 */

public class PhenoTree {
	GEPConfig _config;
	String _karva;
	String _terminals;
	
	aNode rootNode = null;
	ArrayList<TerminalNode> terminalNodes;
	
	String getTerminals() {
		return _terminals;
	}
	
	public PhenoTree(String karva, String terminals, GEPConfig config) {
		_config = config;
		_karva = karva;
		_terminals = terminals;
		terminalNodes = new ArrayList<TerminalNode>();
		Express();
	}

	/**
	 * Node of a tree. Has a function, which determines
	 * how many children it has
	 * 
	 * @author mabbo
	 *
	 */
	
	public abstract class aNode {
		protected PhenoTree _tree;
		public aNode(PhenoTree tree){
			_tree = tree;
		}
		public abstract double getValue() throws IllegalActionException;
	}
	
	public class FunctionNode extends aNode {
		public ArrayList<aNode> _children = new ArrayList<aNode>();
		public Function _function;
		
		public FunctionNode(Function function, PhenoTree tree){
			super(tree);
			_function = function;
		}
		
		public double getValue() throws IllegalActionException {
			Double[] inputs = new Double[_function.getNumArgs()];
			
			if( _children.size() != _function.getNumArgs() )
				System.err.println("Error in Node");
			
			for( int i = 0; i < inputs.length; ++i ){
				inputs[i] = _children.get(i).getValue();
			}
			return _function.ApplyFunction(inputs);
		}
	}
	
	public class TerminalNode extends aNode {
		private String _terminal;
		public TerminalNode(PhenoTree tree, String terminal){
			super(tree);
			_terminal = terminal;
		}
		public String getTerminal(){return _terminal;}
		public static final double NO_VALUE = -45000001;  
		private double _value = NO_VALUE;
		public void setValue(double value) { _value = value; }
		public double getValue() { return _value;}
	}

	private void Express(){
		ArrayList<FunctionNode> workList = new ArrayList<FunctionNode>();
		int readItt = 0;
		
		do {
			//Create a new node
			String nextChar = _karva.substring(readItt,readItt+1);
			readItt++;
			aNode node = null;
			boolean isTerminal = ( _terminals.indexOf(nextChar) != -1); 
			if( isTerminal ) {
				//we have a terminal, add a Terminal node
				TerminalNode terminal = new TerminalNode(this, nextChar);
				terminalNodes.add(terminal);
				node = terminal;
			}
			else {
				FunctionNode fnode = new FunctionNode(_config.getFunction(nextChar), this);
				workList.add(fnode);
				node = fnode;
			}
			
			if( rootNode == null ) {
				rootNode = node;
			} else {
				FunctionNode parentNode = workList.get(0);
				parentNode._children.add(node);
				if( parentNode._children.size() >= parentNode._function.getNumArgs() ) {
					workList.remove(0);
				}
			}
		} while (workList.size() > 0);
		
	}
	
	public void SetTerminalValues(Double[] values){
		for( int i = 0; i < terminalNodes.size(); ++i ) {
			//set each terminal node to it's appropriate value
			TerminalNode term = terminalNodes.get(i);
			int valueIndex = term.getTerminal().charAt(0) - 'a';
			term.setValue(values[valueIndex]);
		}
	}
	
	public Double getValue() {
		try {
			return rootNode.getValue();
		} catch (IllegalActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0.0;
		}
	}
	
	
}



