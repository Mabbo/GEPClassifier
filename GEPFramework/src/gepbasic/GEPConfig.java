package gepbasic;

import java.io.IOException;
import java.lang.reflect.Constructor;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import framework.Function;
import framework.ModificationSet;
import framework.SelectionMethod;

public class GEPConfig implements framework.GEPConfig {

	 
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
		
		_numruns = getIntValue("//Runs");
		_numgenerations = getIntValue("//Generations");
		_maxruntime = getIntValue("//MaxRunTime");
		_populationsize = getIntValue("//PopulationSize");
		
		_nodeheadlength = getIntValue("//NodeDescription/Head");
		
		//Load node functions
		NodeList nodeFunctions = getNodes("//NodeDescription/FunctionSet/*");
		System.out.println(nodeFunctions.getLength());
		
		String functionNameStart = "functions.";
		_nodefunctionset = new BasicFunctionSet();
		for (int i = 0; i < nodeFunctions.getLength(); ++i) {
			String functionName = nodeFunctions.item(i).getNodeName();
	        System.out.println("Adding function: " + functionName );
	        AddFunctionToSet(_nodefunctionset, functionNameStart + functionName);
	    }		
		
		NodeList layers = getNodes("//NodeLayers/Layer/Nodes/text()");
		_numnodelayers = layers.getLength();
		_nodesperlayer = new int[_numnodelayers];
		for( int i = 0; i < _numnodelayers; ++i) {
			String layerValue = layers.item(i).getNodeValue();
			System.out.println(layerValue);
			_nodesperlayer[i] = Integer.parseInt(layers.item(i).getNodeValue());
		}
		
		_cellheadlength = getIntValue("//CellDescription/Head");
		//Load node functions
		NodeList cellFunctions = getNodes("//CellDescription/FunctionSet/*");
		System.out.println(nodeFunctions.getLength());

		_cellfunctionset = new BasicFunctionSet();
		for (int i = 0; i < cellFunctions.getLength(); ++i) {
			String functionName = nodeFunctions.item(i).getNodeName();
	        System.out.println("Adding function: " + functionName );
	        AddFunctionToSet(_cellfunctionset, functionNameStart + functionName);
	    }
		
		String selectionname = getStringValue("//Selection/Method");
		String selectionPackage = "selections.";
		_selectionMethod = createSelectionMethod(selectionPackage + selectionname);

		_keeppercentage = getDoubleValue("//Selection/PercentageKept");
		
		
		
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
	
	private int 	_numruns = 0;
	private int 	_numgenerations = 0;
	private long 	_maxruntime = 0;
	private int		_populationsize = 0;

	private int 	_nodeheadlength = 0;
	private int		_nodetaillength = -1;
	private int 	_nodelength = -1;
	private BasicFunctionSet _nodefunctionset = null;
	
	private int 	_numnodelayers = 0;
	private int[]	_nodesperlayer = null;
	
	private int		_cellheadlength = 0;
	private int 	_celllength = -1;
	private int 	_celltaillength = -1;
	private BasicFunctionSet _cellfunctionset = null;

	private ModificationSet _modifiers = null;	
	private SelectionMethod _selectionMethod = null;
	private double	_keeppercentage = 0.0;
	
	public BasicFunctionSet getCellFunctionSet() {
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
			_celltaillength = getTailLength(_cellheadlength, 
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
	 
	public BasicFunctionSet getNodeFunctionSet() {
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
			_nodetaillength = getTailLength(_nodeheadlength, 
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
	
	public static int getTailLength(int headLength, int maxArgs){	
		//t = h*(MaxArg-1)+1
		return (headLength*(maxArgs-1)) + 1;
	}
	
	private static void AddFunctionToSet(BasicFunctionSet fs, String functionName) {
		Function f = (Function) createObjectOfType(functionName);
		fs.addFunction(f);
	}
	
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
