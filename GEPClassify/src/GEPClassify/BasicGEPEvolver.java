package GEPClassify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import modifiers.MutationMechanism;

import dataset.ClassificationInstance;
import dataset.DataSet;

public class BasicGEPEvolver implements GEPEvolver {

	private GEPConfig _config;
	private DataSet _trainSet;
	private DataSet _testSet;
	
	private int current_gen_number = 0;
	
	private final double CULL_FACTOR = 0.5;
	private Random rand = new Random();
	
	public class PopulationMember implements Comparable<PopulationMember> {
		public KarvaString karva;
		public ExpressedKarva expression;
		public int score;
		public boolean flagged_for_removal;
		public PopulationMember(KarvaString karva){
			this.karva = karva;
			expression = new ExpressedKarva(this.karva);
			score = 0;
			flagged_for_removal = false;
		}
		public int compareTo(PopulationMember arg0) {
			if (arg0.score < this.score) return -1;
			else return 1;
		}
		public String toString() {
			return "" + (flagged_for_removal? "X " : "  ") + karva.getTotalKarva() + ": " + score;
		}
	}
	
	private ArrayList<PopulationMember> population;
	
	public void setGEPConfig(GEPConfig conf) {
		_config = conf;
	}

	public void setTrainingSet(DataSet tset) {
		_trainSet = tset;
	}

	public void RunGeneticAlgorithm() {
		assert( _config != null );
		assert( _trainSet != null );
		CreatePopulation();	
		do {
			RankByFitness();
			CullWeak();
			RefillPopulation();
			current_gen_number++;
		}
		while( !CheckForFinished() );
	}
	
	public boolean CheckForFinished() {
		/*
		 * Step 1: just check generation number
		 * 
		 * */
		if( current_gen_number >= _config.getMaxGenerations() )
			return true;
		
		/*
		 * Step 2: How are things on the test set?
		 * 
		 * */
		
		
		return false;
	}

	public void CreatePopulation() {
		/*
		 * Create the initial random population
		 */
		population = new ArrayList<PopulationMember>();
		for( int i = 0; i < _config.getPopulationSize(); ++i){ 
			KarvaString karva = new KarvaString(_config, _trainSet);
			karva.Randomize();
			PopulationMember newMember = new PopulationMember(karva);
			population.add(newMember);
		}
	}

	public void CullWeak() {
		/*
		 * for each member, ranked as the i-th best
		 * with probability i*factor/pop-size, remove that member
		 * factor can be adjusted as needed
		 */
		for( int i = 1; i < _config.getPopulationSize(); ++i ) {
			double probability = (double)(i) * CULL_FACTOR 
						      / (double)(_config.getPopulationSize());
			if( rand.nextDouble() < probability ){
				population.get(i).flagged_for_removal = true;
			}
		}
		

		for( int i = 0; i < population.size(); ++i) {
			System.out.println(population.get(i).toString());
		}
		System.out.println("");
		
		for( int i = _config.getPopulationSize()-1; i >=0; --i ){
			if( population.get(i).flagged_for_removal){
				population.remove(i);
			}
		}
		
	}

	public void RankByFitness() {
		//For each member of the population
		for( int q = 0; q < _config.getPopulationSize(); ++q) {
			PopulationMember p = population.get(q);
			p.score = 0;
			//For each item in the training set
			for( int i = 0; i < _trainSet.size(); ++i) {
			//Give the inputs, and see what output is given
				ClassificationInstance c = _trainSet.getInstance(i);
				Double[] inputs = c.getData();
				Double[] outputs = p.expression.getResults(inputs);
				
				int correctClass = c.getClassification();
				int max = -1;
				int min = -1;
				Double maxScore = -100000000.0;
				Double minScore =  100000000.0;
				for( int x = 0; x < outputs.length; ++x){
					if( outputs[x] > maxScore ) {
						max = x;
						maxScore = outputs[x];
					}
					if( outputs[x] < minScore ) {
						min = x;
						minScore = outputs[x];
					}
				}
				//if the correct class is highest, give +5
				if( correctClass == max)
					p.score += 5;
				else
					p.score -= 1;
				//if the correct class is the lowest, give -3
				if( correctClass == min)
					p.score -= 3;
			}
		}	
		//Now sort by fitness
		Collections.sort(population);
	}

	public void RefillPopulation() {
		int oldSize = population.size();
		while( population.size() < _config.getPopulationSize()) {
			
			//select individual proportionally to how good they are
			int rpick = rand.nextInt( oldSize * oldSize );
			int pick = oldSize - (int)Math.sqrt(rpick) - 1;
			
			KarvaString k = new KarvaString(
					population.get(pick).karva);
			//Get mutation operators
			MutationMechanism mm = _config.getModifiers().GetMutators()[0];
			k = mm.Mutate(k);
			PopulationMember newguy = new PopulationMember(k);
			population.add(newguy);
		}
	}
	
	public void setTestSet(DataSet testSet) {
		_testSet = testSet;
	}

}
