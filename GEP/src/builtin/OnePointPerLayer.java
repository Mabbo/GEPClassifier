package builtin;

import java.util.Random;

import base.Config;
import base.Genome;
import base.Unit;
import framework.Crossover;

public class OnePointPerLayer implements Crossover {

	private Random _rand = new Random();
	public Unit Cross(Unit parentA, Unit parentB, Config conf) {
		
		Genome child = new Genome(parentA.getGenome());
		//For each layer
		for( int layer = 0; layer < conf.getNumNodeLayers(); ++layer){
			//pick pivot 
			int pivot = _rand.nextInt(conf.getNodesInLayer(layer));
			for( int i = 0; i < pivot; ++i) {
				child.setGene(layer, i, parentA.getGenome().getGene(layer, i));
			}
			for( int i = pivot; i < conf.getNodesInLayer(layer); ++i) {
				child.setGene(layer, i, parentB.getGenome().getGene(layer, i));
			}
		}
		Unit childUnit = new Unit(conf, child);
		return childUnit;
	}

}
