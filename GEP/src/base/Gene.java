package base;

import java.util.Random;

/***
 * class Gene
 * 
 * A gene is the data-level representation of a single node. It is
 * the dna of this node. A genome will contain many of these, wrapping
 * a whole unit's dna together. 
 * 
 * A gene is an array of bytes, and an array of doubles. The first h bytes 
 * are the head, the next t bytes are the tail, and the r doubles are the 
 * random numerical constants.
 * 
 * @author mabbo
 *
 */


public class Gene {
	private byte[] _dna;
	private double[] _rnc;
	public Gene(byte[] dna, double[] rnc ){
		_dna = dna;
		_rnc = rnc;
	}
	public Gene(Gene g){
		_dna = g._dna.clone();
		_rnc = g._rnc.clone();
	}
	public byte getDNA(int index){
		return _dna[index];
	}
	public double getRNC(int index){
		return _rnc[index];
	}
	public byte[] getDNA() {return _dna;}
	public double[] getRNC() {return _rnc;}
	public void setDNA(byte[] dna){
		_dna = dna;
	}
	public void setDNA(byte[] dna, double[] rnc){
		_dna = dna;
		_rnc = rnc;
	}
	
	private static Random _rand = new Random(); 
	public static Gene makeRandomGene(Config conf){
		byte[] dna = new byte[conf.getNodelength()];
		byte[] headValues = conf.getHeadValues();
		byte[] tailValues = conf.getTailValues();
		int i = 0;
		for( ; i < conf.getHeadlength(); ++i) {
			dna[i] = headValues[_rand.nextInt(headValues.length)];
		}
		for( ; i < conf.getNodelength(); ++i) {
			dna[i] = tailValues[_rand.nextInt(headValues.length)];
		}
		double[] rnc = new double[conf.getNumRNC()];
		for( i = 0; i < conf.getNumRNC(); ++i){
			rnc[i] = _rand.nextDouble() * 10.0;
		}
		Gene ret = new Gene(dna, rnc);
		return ret;
	}
	
	
	
}