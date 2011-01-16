package framework;

/**
 * Gene class.
 * 
 * Responsibilities:
 * Contain the string of characters which represent a single node or cell
 * of the NN.
 * Allow genetic operators to be applied to it to alter it, etc.
 * 
 * @author mabbo
 *
 */

public class Gene {
	private String _dna;
	public Gene(String dna){
		_dna = dna;
	}
	public String getCharAt(int index){
		return _dna.substring(index, index+1);
	}
	public String getDNA() {return _dna;}
}






