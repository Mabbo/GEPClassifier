package GEPClassify;

import dataset.DataSet;

public class BasicGEPEvolver implements GEPEvolver {

	private GEPConfig _config;
	private DataSet _trainSet;
	
	private int current_gen_number = 0;
	
	public void setGEPConfig(GEPConfig conf) {
		_config = conf;
	}

	public void setTrainingSet(DataSet tset) {
		_trainSet = tset;
	}

	public void RunGeneticAlgorithm() {
		assert( _config != null );
		assert( _trainSet != null );
		
		do {
			CreatePopulation();
			RankByFitness();
			CullWeak();
			RefillPopulation();
			current_gen_number++;
		}
		while( !CheckForFinished() );
	}
	
	public boolean CheckForFinished() {
		if( current_gen_number >= _config.getMaxGenerations() )
			return true;		
		return false;
	}

	public void CreatePopulation() {
		// TODO Auto-generated method stub

	}

	public void CullWeak() {
		// TODO Auto-generated method stub

	}

	public void RankByFitness() {
		// TODO Auto-generated method stub

	}

	public void RefillPopulation() {
		// TODO Auto-generated method stub

	}
	

}
