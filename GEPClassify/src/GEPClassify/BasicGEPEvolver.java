package GEPClassify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import modifiers.CrossoverMechanism;
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
			score = 0;
			flagged_for_removal = false;
			Initialize();
		}
		public void Initialize() {
			expression = new ExpressedKarva(this.karva);
		}
		public int compareTo(PopulationMember arg0) {
			if (arg0.score < this.score) return -1;
			else return 1;
		}
		public String toString() {
			return "" + score + " : " + (flagged_for_removal? "X " : "  ") + karva.getTotalKarva();
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
			
			System.out.println("Generation: " + this.current_gen_number);
			for( int i = 0; i < 5; ++i) {
				System.out.println(population.get(i).toString());
			}
			System.out.println("");
			
			CullWeak();
			ApplySelection();
			ApplyMutation();
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
		if( population == null ) 
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
				
				int correctScore = this._trainSet.getNumClasses();
				int incorrectScore = -1;
				int veryIncorrectScore = -1 * _trainSet.getNumClasses()/2;
				
				if( correctClass == max)
					p.score += correctScore;
				else
					p.score += incorrectScore;
				//if the correct class is the lowest, give a lot of bad points
				if( correctClass == min)
					p.score += veryIncorrectScore;
			}
		}	
		//Now sort by fitness
		Collections.sort(population);
	}

	public void ApplySelection() {
		int oldSize = population.size();
		while( population.size() < _config.getPopulationSize()) {
			
			//select individual proportionally to how good they are
			int rpick = rand.nextInt( oldSize * oldSize );
			int pick = oldSize - (int)Math.sqrt(rpick) - 1;
			
			KarvaString k = new KarvaString(
					population.get(pick).karva);
			PopulationMember newguy = new PopulationMember(k);
			population.add(newguy);
		}
	}
	
	public void setTestSet(DataSet testSet) {
		_testSet = testSet;
	}

	public void ApplyMutation() {
		//For each mutation type,
		for( int i = 0; i < _config.getModifiers().getMutatorCount(); ++i) {
		//For each member of the population
			MutationMechanism mm = _config.getModifiers().GetMutator(i);
			Double mmprob = _config.getModifiers().GetMutatorProbability(i);
			for( int a = 1; a < population.size(); ++a ) {
				PopulationMember p = population.get(a);
				//given the mutations probability, apply it
				if( rand.nextDouble() < mmprob ) {
					mm.Mutate(p.karva);
				}
			}
		}
		//For each crossover type,
		for( int i = 0; i < _config.getModifiers().getCrossoverCount(); ++i){
		//For each member of the population
			CrossoverMechanism cm = _config.getModifiers().GetCrossover(i);
			Double cmprob = _config.getModifiers().GetCrossoverProbability(i);
			for( int a = 1; a < population.size(); ++a) {
				PopulationMember p = population.get(a);
			
				// given the crossover probability,
				if( rand.nextDouble() < cmprob) {
				// select a randomly chosen mate, and apply
					int mate = rand.nextInt(population.size()-1)+1;
					PopulationMember q = population.get(mate);
					
					//System.out.println("A: " + p.karva.getTotalKarva() 
					//+ "\nB: " + q.karva.getTotalKarva() + " = ");
					
					cm.Crossover(p.karva, q.karva);
					
					//System.out.println("A: " + p.karva.getTotalKarva() 
					//			+ "\nB: " + q.karva.getTotalKarva());
					
				}
			}
		}
		for( PopulationMember p : population ) {
			p.Initialize();
		}
	}

	
	public void AddToInitialPopulation(String kstring) {
		if( population == null ) 
			population = new ArrayList<PopulationMember>();
		KarvaString karva = new KarvaString(_config, _trainSet);
		karva.Randomize();
		if( kstring.length() == karva.getLength() )
			karva.setKarva(kstring);
		PopulationMember member = new PopulationMember(karva);
		population.add(member);
	}
	
}
