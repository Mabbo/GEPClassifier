package base;
import framework.*;
import java.util.ArrayList;


public class Config {
	
private boolean configured = false;

	ArrayList<Class<Function> > functionClasses;
	Class<SelectionMethod> selectionClass;
	ArrayList<Class<EvolverStateProcess> > evolverStateProcesses;
	
	private String 	title = "";
	private String 	datafiletype = "";
	private String 	datafilelocation = "";
	private String 	datafilename = "";
	private double 	trainpercentage = 0.7;
	private int    	numclasses = 0;
	private int		numinputs = 0;
	private int 	classindex = 0;
	private int[]	ignorecolumns = null;
	
	private int 	numruns = 0;
	private int 	numgenerations = 0;
	private long 	maxruntime = 0;
	private int		populationsize = 0;
	private int 	headlength = 0;
	private int		taillength = -1;
	private int 	numRNC = 0;
	private FunctionSet functionset = null;
	
	private int 	numnodelayers = 1;
	private int[]	nodesperlayer = null;

	private double	mutationrate = 0;
	private ModificationSet modifiers = null;	
	private SelectionMethod selectionMethod = null;
	private double	keeppercentage = 0.0;
	
	private byte 	functionIndexEnd = 0;
	private byte	terminalIndexEnd = 0;
	private byte	rncIndexEnd = 0;
	
	public enum LetterType{
		FUNCTION, TERMINAL, RNC,
		UNKNOWN;
	};
	
	private ArrayList<EvolverStateProcess> generationProcesses 
		= new ArrayList<EvolverStateProcess>();
	private ArrayList<EvolverStateProcess> runProcesses 
		= new ArrayList<EvolverStateProcess>(); 
	private ArrayList<EvolverStateProcess> endProcesses
	 	= new ArrayList<EvolverStateProcess>();
	
	private EvolverStateProcess fitnessTest;
	
	public ArrayList<EvolverStateProcess> getGenerationProcesses() {
		return generationProcesses;
	}
	public ArrayList<EvolverStateProcess> getRunProcesses() {
		return runProcesses;
	}
	public ArrayList<EvolverStateProcess> getEndProcesses() {
		return endProcesses;
	}
	
	public boolean isConfigured() {
		return configured;
	}
	public String getTitle() {
		return title;
	}
	public String getDatafiletype() {
		return datafiletype;
	}
	public String getDatafilelocation() {
		return datafilelocation;
	}
	public String getDatafilename() {
		return datafilename;
	}
	public double getTrainpercentage() {
		return trainpercentage;
	}
	public int getNumclasses() {
		return numclasses;
	}
	public int getNuminputs() {
		return numinputs;
	}
	public int getClassindex() {
		return classindex;
	}
	public int[] getIgnorecolumns() {
		return ignorecolumns;
	}
	public int getNumruns() {
		return numruns;
	}
	public int getNumgenerations() {
		return numgenerations;
	}
	public long getMaxruntime() {
		return maxruntime;
	}
	public int getPopulationsize() {
		return populationsize;
	}
	public int getHeadlength() {
		return headlength;
	}
	public int getTaillength() {
		return taillength;
	}
	public int getNodelength() {
		return getHeadlength() + getTaillength();
	}
	public int getNumRNC() {
		return numRNC;
	}
	public FunctionSet getNodefunctionset() {
		return functionset;
	}
	public int getNumNodeLayers() {
		return numnodelayers;
	}
	public int getNodesInLayer(int layer) {
		return nodesperlayer[layer];
	}
	public double getMutationrate() {
		return mutationrate;
	}
	public ModificationSet getModifiers() {
		return modifiers;
	}
	public SelectionMethod getSelectionMethod() {
		return selectionMethod;
	}
	public double getKeeppercentage() {
		return keeppercentage;
	}
	
	public void setFitnessTest(EvolverStateProcess fitnessTest) {
		this.fitnessTest = fitnessTest;
	}
	public EvolverStateProcess getFitnessTest() {
		return fitnessTest;
	}
	
//	public byte getFunctionIndexEnd() {
//		return functionIndexEnd;
//	}
//	public byte getTerminalIndexEnd() {
//		return terminalIndexEnd;
//	}
//	public byte getRNCIndexEnd() {
//		return rncIndexEnd;
//	}
	
	//--------------------------------------------//
	
	public void LoadConfigurationFile(String filename) {
		
	}

	public DataSetLoader getDataSetLoaderInstance()  {
		return null;		
	}
	
	private byte[] _funcValues = null;
	public byte[] getFunctionValues() {
		if( _funcValues == null) {
			_funcValues = new byte[functionIndexEnd];
			for( byte i = 0; i < functionIndexEnd; ++i){
				_funcValues[i] = i;
			}
		}
		return _funcValues;		
	}

	private byte[] _terminalValues = null;
	public byte[] getTerminalValues() {
		if( _terminalValues == null) {
			_terminalValues = new byte[terminalIndexEnd - functionIndexEnd];
			for( byte i = functionIndexEnd; i < terminalIndexEnd; ++i){
				_terminalValues[i] = i;
			}
		}
		return _terminalValues;		
	}
	
	private byte[] _rncValues = null;
	public byte[] getRNCValues() {
		if( _rncValues == null) {
			_rncValues = new byte[rncIndexEnd - terminalIndexEnd];
			for( byte i = terminalIndexEnd; i < rncIndexEnd; ++i){
				_rncValues[i] = i;
			}
		}
		return _rncValues;
	}
	
	private byte[] _headValues = null;
	public byte[] getHeadValues() {	
		if( _headValues == null ) {
			_headValues = new byte[rncIndexEnd];
			for( byte i = 0; i < rncIndexEnd; ++i) 
				_headValues[i] = i;
		}
		return _headValues;
	}
	
	private byte[] _tailValues = null;
	public byte[] getTailValues() {
		if( _tailValues == null) {
			_tailValues = new byte[rncIndexEnd-functionIndexEnd];
			for( byte i = functionIndexEnd; i < rncIndexEnd; ++i) 
				_tailValues[i] = i;
		}
		return _tailValues;
	}
	
	
	public LetterType getTypeFor(byte input) {
		//For a given input, is this a Function, terminal, RNC, or none
		//0<= i < functionIndexEnd => Function
		//functionIndexEnd <= i < terminalIndexEnd => Terminal
		//terminalIndexEnd <= i < rncIndexEnd => RNC
		//else, unknown
		if( input >= 0 && input < functionIndexEnd )
			return LetterType.FUNCTION;
		else if (input >= functionIndexEnd && input < terminalIndexEnd)
			return LetterType.TERMINAL;
		else if (input >= terminalIndexEnd && input < rncIndexEnd)
			return LetterType.RNC;
		else 
			return LetterType.UNKNOWN;
	}
	
	public int getIndexForTerminal(byte terminal) {
		return terminal - functionIndexEnd;
	}
	public int getIndexForRNC(byte rnc){
		return rnc - terminalIndexEnd;
	}
	
}










