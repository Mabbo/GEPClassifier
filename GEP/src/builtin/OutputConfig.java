package builtin;

import base.EvolverState;
import base.FunctionSet;
import base.ModificationSet;
import framework.EvolverStateProcess;

public class OutputConfig implements EvolverStateProcess {

	public void Process(EvolverState es) {

		//What functiosn exist?	
		FunctionSet fs = es.getConfig().getNodeFunctionSet();
		byte[] funcBytes = es.getConfig().getFunctionValues();
		for( int i = 0; i < fs.size(); ++i){
			System.out.println("Function " 
					+ i + ": " + fs.getFunction(funcBytes[i]).getFunctionName());
		}
		
		ModificationSet ms = es.getConfig().getModificationSet();
		for( int i = 0; i < ms.getCrossoverCount(); ++i){
			System.out.println("Crossover "
					+ i + ": " + ms.getCrossover(i).getClass().getSimpleName() );
		}
		for( int i = 0; i < ms.getMutatorCount(); ++i){
			System.out.println("Mutator "
					+ i + ": " + ms.getMutator(i).getClass().getSimpleName() );
		}
		
	}

	@Override
	public void Initialize(String parameters) {
		// TODO Auto-generated method stub
		
	}
}
