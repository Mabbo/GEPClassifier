package base;
import framework.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Config {
	
private boolean configured = false;

	ArrayList<Class<Function> > functionClasses;
	Class<SelectionMethod> selectionClass;
	ArrayList<Class<EvolverStateProcess> > evolverStateProcesses;
	
	private String 	title = "";
	private String 	datafilelocation = "";
	private String 	datafilename = "";
	private DataSetLoader datasetloader = null;
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
	
	private byte 	numFunctions = 0;
	private byte 	numTerminals = 0;
	
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
	public int getClassIndex() {
		return classindex;
	}
	public int[] getIgnoreColumns() {
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
	public DataSetLoader getDataSetLoader()  {
		return datasetloader;		
	}
	
	//-----------------------------------------------------//
	
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

	
	
	//-----------XML PARSING--------------//
	
	private DocumentBuilderFactory domFactory;
	private DocumentBuilder builder;
	private Document doc;
	private XPathFactory factory; 
	private XPath xpath; 

	public void LoadConfigurationFile(String filename) 
		throws ParserConfigurationException, SAXException, 
			   IOException, XPathExpressionException {
		
		InitXPath(filename);

		title = getStringValue("//Title");
		datafilelocation = getStringValue("//DataSet/FileLocation");
		datafilename = getStringValue("//DataSet/FileName");
		trainpercentage = getDoubleValue("//DataSet/TrainPercentage");
		numclasses = getIntValue("//DataSet/Description/NumberOfClasses");
		numinputs = getIntValue("//DataSet/Description/NumberOfInputs");
		classindex = getIntValue("//DataSet/Description/ClassIndex");
		//get the ignored columns
		NodeList ignoreInputs = getNodes("//DataSet/Description/Ignore/text()");
		ignorecolumns = new int[ignoreInputs.getLength()];
		for( int i = 0; i < ignoreInputs.getLength(); ++i){
			ignorecolumns[i] = Integer.parseInt(ignoreInputs.item(i).getNodeValue());
		}
		
		Node dslnode = getNodes("//DataSet/DataSetLoader").item(0);
		
		String dslClassName = dslnode.getAttributes()
		.getNamedItem("classfile").getNodeValue();
		
		Node locationNode = dslnode.getAttributes()
			.getNamedItem("location");
		String dslClassDir = "bin/";
		if( locationNode != null ) {
			dslClassDir = locationNode.getNodeValue();
		}
		
		Class<?> dslClass = getClassFromFile(dslClassDir, dslClassName); 
		datasetloader = (DataSetLoader)createObjectOfClass(dslClass);

		//--------------------//
		
		numruns = getIntValue("//Runs");
		numgenerations = getIntValue("//Generations");
		maxruntime = getIntValue("//MaxRunTime");
		populationsize = getIntValue("//PopulationSize");
		
		headlength = getIntValue("//NodeDescription/Head");
		
		//-----Functions-----//
		
		functionset = new FunctionSet();
		NodeList functionNodes = getNodes("//NodeDescription/FunctionSet/*");
		for( int i = 0; i < functionNodes.getLength(); ++i){
		//For each function node
			Node funcNode = functionNodes.item(i);
			//Read it's name, and location
			String funcClassName = funcNode.getAttributes()
			.getNamedItem("classfile").getNodeValue();
			
			Node funcLocationNode = funcNode.getAttributes()
				.getNamedItem("location");
			String funcClassDir = "bin/";
			if( funcLocationNode != null ) {
				funcClassDir = funcLocationNode.getNodeValue();
			}
			//Get the class from the file
			Class<?> funcClass = getClassFromFile(funcClassDir, funcClassName);
			//Instantiate the function
			Function function = (Function)createObjectOfClass(funcClass);
			function.setSymbol((byte) i);
			//Add to the functionset
			functionset.addFunction(function);
		
		}
		
		
		
		
	}
	
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
	
	
	private static Class<?> getClassFromFile(String dir, String className){
		// Create a File object on the root of the directory containing the class file
		File file = new File(dir);

		try {
		    URL url = file.toURI().toURL();
		    URL[] urls = new URL[]{url};
		    ClassLoader cl = new URLClassLoader(urls);
		    
		    Class<?> cls = cl.loadClass(className);
		    		    
		    return cls;
		} catch (MalformedURLException e) {
			System.err.println("1: Error loading class '" + className + "'.");
			return null;
		} catch (ClassNotFoundException e) {
			System.err.println("2: Error loading class '" + className + "'.");
			return null;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	private static Object createObjectOfClass(Class c) {
		Constructor ct = c.getConstructors()[0];
		Object obj = null;
		try {
			obj = ct.newInstance(new Object[]{});
		} catch (Exception e) {
			System.err.println("Could not instantiate instance of type '" + c.getCanonicalName() + "'.");
			return null;
		}	
		return obj;
	}
	
	
	
}












