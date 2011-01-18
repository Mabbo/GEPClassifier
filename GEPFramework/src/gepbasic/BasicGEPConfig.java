package gepbasic;

import java.io.IOException;
import java.lang.reflect.Constructor;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import framework.*;

public class BasicGEPConfig implements framework.GEPConfig {
	 
	public void LoadConfig(String filename) {
		try{
			Config_Load(filename);
		}catch(Exception ex){
			System.err.println("Error loading config file: " + ex.getMessage());
		}
	}

	private void Config_Load(String filename) 
		throws ParserConfigurationException, SAXException, 
			   IOException, XPathExpressionException {
			    
		InitXPath(filename);

		_title = getStringValue("//Title");
		_datafiletype = getStringValue("//DataSet/FileType");
		_datafilelocation = getStringValue("//DataSet/FileLocation");
		_datafilename = getStringValue("//DataSet/FileName");
		_trainpercentage = getDoubleValue("//DataSet/TrainPercentage");
		_numclasses = getIntValue("//DataSet/Classes");
		_numinputs = getIntValue("//DataSet/Inputs");
		
		_numruns = getIntValue("//Runs");
		_numgenerations = getIntValue("//Generations");
		_maxruntime = getIntValue("//MaxRunTime");
		_populationsize = getIntValue("//PopulationSize");
		
		_nodeheadlength = getIntValue("//NodeDescription/Head");
		
		//Load node functions
		NodeList nodeFunctions = getNodes("//NodeDescription/FunctionSet/*");
		
		String functionNameStart = "functions.";
		_nodefunctionset = new BasicFunctionSet();
		for (int i = 0; i < nodeFunctions.getLength(); ++i) {
			String functionName = nodeFunctions.item(i).getNodeName();
	        AddFunctionToSet(_nodefunctionset, functionNameStart + functionName);
	    }		
		
		NodeList layers = getNodes("//NodeLayers/Layer/Nodes/text()");
		_numnodelayers = layers.getLength();
		_nodesperlayer = new int[_numnodelayers];
		for( int i = 0; i < _numnodelayers; ++i) {
			_nodesperlayer[i] = Integer.parseInt(layers.item(i).getNodeValue());
		}
		
		_cellheadlength = getIntValue("//CellDescription/Head");
		//Load node functions
		NodeList cellFunctions = getNodes("//CellDescription/FunctionSet/*");

		_cellfunctionset = new BasicFunctionSet();
		for (int i = 0; i < cellFunctions.getLength(); ++i) {
			String functionName = nodeFunctions.item(i).getNodeName();
	        AddFunctionToSet(_cellfunctionset, functionNameStart + functionName);
	    }
		
		String selectionname = getStringValue("//Selection/Method");
		String selectionPackage = "selections.";
		_selectionMethod = createSelectionMethod(selectionPackage + selectionname);

		_keeppercentage = getDoubleValue("//Selection/PercentageKept");
		
		//Load the modifiers
		_mutationrate = getDoubleValue("//MutationRate");
		
		_modifiers = new BasicModificationSet();
		String modifierspackage = "modifiers."; 
		NodeList crossovers = getNodes("//Crossovers/*");
		NodeList mutators = getNodes("//Mutators/*");
		for (int i = 0; i < crossovers.getLength(); ++i) {
			Node node = crossovers.item(i);
			String crossovername = modifierspackage + node.getNodeName();
	        double weight = Double.parseDouble(crossovers.item(i).getAttributes()
	        				.getNamedItem("weight").getNodeValue());
	        AddCrossoverToSet(_modifiers, crossovername, weight);
	    }
		for( int i = 0; i < mutators.getLength(); ++i){
			Node node = mutators.item(i);
			String mutatorname = modifierspackage + node.getNodeName();
	        double weight = Double.parseDouble(mutators.item(i).getAttributes()
	        				.getNamedItem("weight").getNodeValue());
	        AddMutatorToSet(_modifiers, mutatorname, weight);
		}		
	}
	
	private DocumentBuilderFactory domFactory;
	private DocumentBuilder builder;
	private Document doc;
	private XPathFactory factory; 
	private XPath xpath; 
	
	private void InitXPath(String filename) throws ParserConfigurationException, SAXException, IOException {
		domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true); // never forget this!
	    builder = domFactory.newDocumentBuilder();
	    doc = builder.parse(filename);

	    factory = XPathFactory.newInstance();
	    xpath = factory.newXPath();
	}
	
	private NodeList getNodes(String expression) throws XPathExpressionException{
		//For each part of the config, load it with an XPathExpression object
	    XPathExpression expr = xpath.compile(expression);

	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    return nodes;
	}
	private String getStringValue(String expression) throws XPathExpressionException {
		NodeList nodes = getNodes(expression + "/text()");
	    for (int i = 0; i < nodes.getLength();) {
	        return nodes.item(i).getNodeValue(); 
	    }	
	    return "";
	}
	private double getDoubleValue(String expression) throws XPathExpressionException {
		NodeList nodes = getNodes(expression + "/text()");
	    for (int i = 0; i < nodes.getLength();) {
	        return Double.parseDouble(nodes.item(i).getNodeValue()); 
	    }	
	    return 0.0;
	}
	private int getIntValue(String expression) throws XPathExpressionException {
		NodeList nodes = getNodes(expression + "/text()");
	    for (int i = 0; i < nodes.getLength();) {
	        return Integer.parseInt(nodes.item(i).getNodeValue()); 
	    }	
	    return 0;
	}
	
	private boolean _configured = false;
	
	private String 	_title = "";
	private String 	_datafiletype = "";
	private String 	_datafilelocation = "";
	private String 	_datafilename = "";
	private double 	_trainpercentage = 0.7;
	private int    	_numclasses = 0;
	private int		_numinputs = 0;
	
	private int 	_numruns = 0;
	private int 	_numgenerations = 0;
	private long 	_maxruntime = 0;
	private int		_populationsize = 0;

	private int 	_nodeheadlength = 0;
	private int		_nodetaillength = -1;
	private int 	_nodelength = -1;
	private FunctionSet _nodefunctionset = null;
	
	private int 	_numnodelayers = 0;
	private int[]	_nodesperlayer = null;
	
	private int		_cellheadlength = 0;
	private int 	_celllength = -1;
	private int 	_celltaillength = -1;
	private FunctionSet _cellfunctionset = null;

	private double	_mutationrate = 0;
	private ModificationSet _modifiers = null;	
	private SelectionMethod _selectionMethod = null;
	private double	_keeppercentage = 0.0;
	
	public String toString() {
		String result = "";
		result += "Configured: \t\t" + getIsConfigured() + "\n";
		result += "Title: \t\t\t" + getTitle() + "\n";
		result += "DataFile Type: \t\t" + getDataFileType() + "\n";
		result += "DataFile Location: \t" + getDataFileLocation() + "\n";
		result += "DataFile Name: \t\t" + getDataFileName() + "\n";
		result += "Training Percentage: \t" + getTrainingPercentage() + "\n";
		result += "Number of Classes: \t" + getNumberOfClasses() + "\n";
		result += "Number of Inputs: \t" + getNumberOfInputs() + "\n";
		result += "Number of Runs: \t" + getNumberOfRuns() + "\n";
		result += "Number of Generations: \t" + getGenerationsPerRun() + "\n";
		result += "Maximum Runtime: \t" + getMaxTimePerRunMs() + "\n";
		result += "Population Size: \t" + getPopulationSize() + "\n";
		result += "Node Head Length: \t" + getNodeHeadLength() + "\n";
		result += "Node Tail Length: \t" + getNodeTailLength() + "\n";
		result += "Node Length: \t\t" + getNodeLength() + "\n";
		result += "Node Function Set: \t" + getNodeFunctionSet().toString() + "\n";
		result += "Number of Node Layers: \t" + getNodeLayers() + "\n";
		for( int i = 0; i < _nodesperlayer.length; ++i) {
			result += "\tLayer " + i + ": " + getNumNodes(i) + "\n";
		}
		result += "Cell Head Length: \t" + getCellHeadLength() + "\n";
		result += "Cell Tail Length: \t" + getCellTailLength() + "\n";
		result += "Cell Length: \t\t" + getCellLength() + "\n";		
		result += "Cell Function Set: \t" + getCellFunctionSet().toString() + "\n";

		result += "Modification Set: \t" + getModifiers().toString() + "\n";
		result += "Mutation Rate: \t\t" + getMutationRate() + "\n";
		
		result += "Selection Method: \t" + getSelectionMethod().toString() + "\n";
		
		//private double	_keeppercentage = 0.0;
		result += "Keep Percentage: \t" + _keeppercentage + "\n";
		
		return result;
	}
	
	public FunctionSet getCellFunctionSet() {
		return _cellfunctionset;
	}
 
	public int getCellHeadLength() {
		return _cellheadlength;
	}

	public int getCellLength() {
		if( _celllength == -1 ) {
			_celllength = getCellHeadLength() + getCellTailLength();
		}
		return _celllength;
	}

	public int getCellTailLength() {
		if( _celltaillength == -1 ) {
			_celltaillength = Utilities.getTailLength(_cellheadlength, 
					_cellfunctionset.getMaxArgs());
		}
		return _celltaillength;
	}
	 
	public String getDataFileLocation() {
		return _datafilelocation;
	}
	
	public String getDataFileName() {
		return _datafilename;
	}
	 
	public String getDataFileType() {
		return _datafiletype;
	}
 
	public int getGenerationsPerRun() {
		return _numgenerations;
	}
 
	public boolean getIsConfigured() {
		return _configured;
	}

	public long getMaxTimePerRunMs() {
		return _maxruntime;
	}
	 
	public FunctionSet getNodeFunctionSet() {
		return _nodefunctionset;
	}

	public int getNodeLayers() {
		return _numnodelayers;
	}
	 
	public int getNodeHeadLength() {
		return _nodeheadlength;
	}
		
	public int getNodeLength() {
		if( _nodelength == -1){
			_nodelength = getNodeHeadLength() + getNodeTailLength();
		}
		return _nodelength;
	}

	public int getNodeTailLength() {
		if( _nodetaillength == -1) {
			_nodetaillength = Utilities.getTailLength(_nodeheadlength, 
					_nodefunctionset.getMaxArgs());
		}
		return _nodetaillength;
	}
 
	public int getNumNodes(int layerIndex) {
		return _nodesperlayer[layerIndex];
	}

	public int getNumberOfRuns() {
		return _numruns;
	}
	 
	public int getPopulationSize() {
		return _populationsize;
	}

	public ModificationSet getModifiers() {
		return _modifiers;
	}

	public int getNumberOfClasses() {
		return _numclasses;
	}
	
	public int getNumberOfInputs() {
		return _numinputs;
	}

	public String getTitle() {
		return _title;
	}

	public double getTrainingPercentage() {
		return _trainpercentage;
	}
	
	public SelectionMethod getSelectionMethod() {
		return _selectionMethod;
	}

	public double getKeepPercentage() {
		return _keeppercentage;
	}

	public double getMutationRate() {
		return _mutationrate;
	}
	
	private static void AddFunctionToSet(FunctionSet fs, String functionName) {
		Function f = (Function) createObjectOfType(functionName);
		fs.addFunction(f);
	}
	
	private static void AddMutatorToSet(ModificationSet ms, String mutatorname, double weight){
		Mutator m = (Mutator) createObjectOfType(mutatorname);
		ms.AddMutator(m, weight);
	}
	private static void AddCrossoverToSet(ModificationSet ms, String crossover, double weight){
		Crossover m = (Crossover) createObjectOfType(crossover);
		ms.AddCrossover(m, weight);
	}
	
	@SuppressWarnings("unchecked")
	private static Object createObjectOfType(String typename) {
		Class c = null;
		try {
			c = Class.forName(typename);
		} catch (ClassNotFoundException e) {
			System.err.println("Invalid class name: '" + typename + "'.");
			return null;
		}
		Constructor ct = c.getConstructors()[0];
		Object obj = null;
		try {
			obj = ct.newInstance(new Object[]{});
		} catch (Exception e) {
			System.err.println("Could not instantiate instance of type '" + typename + "'.");
			return null;
		}	
		return obj;
	}
	
	private SelectionMethod createSelectionMethod(String name){
		SelectionMethod selmeth = (SelectionMethod) createObjectOfType(name);
		return selmeth;		
	}

}
