package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import framework.*;

public class Config {

	//---------------------------------------------------//

	private String Title = null;
	private String DataSetLocation = null;
	private String DataSetFilename = null;
	private String DataSetLoaderLocation = null;
	private String DataSetLoaderFilename = null;
	private DataSetLoader DataSetLoader = null;
	private String DataSetLoaderParameterString = null;
	private int NumberOfInputs = 0;
	private int NumberOfClasses = 0;
	private double TrainingPercentage = 0.0;
	private int NumberOfRuns = 0;
	private int NumberOfGenerations = 0;
	private int PopulationSize = 0;
	
	//---------------------------------------------------//
	
	private int NodeHeadSize = 0;
	private int NodeTailSize = 0;
	private FunctionSet NodeFunctionSet = null;
	private int NumberRNC = 0;
	private int NumberLayers = 0;
	private int[] NodesInLayer = null;
	
	//---------------------------------------------------//
	
	private EvolverStateProcess FitnessProcess = null;
	private double KeepPercentage = 0.0;
	private SelectionMethod SelectionMethod = null;
	private ModificationSet ModificationSet = null;
	private double MutationRate = 0.0;

	//---------------------------------------------------//
	
	private ArrayList<EvolverStateProcess> StartProcess = null;
	private ArrayList<String> StartProcessParameter = null;
	private ArrayList<EvolverStateProcess> BeforeRunProcess = null;
	private ArrayList<String> BeforeRunProcessParameter = null;
	private ArrayList<EvolverStateProcess> BeforeGenerationProcess = null;
	private ArrayList<String> BeforeGenerationProcessParameter = null;
	private ArrayList<EvolverStateProcess> EndOfGenerationProcess = null;
	private ArrayList<String> EndOfGenerationProcessParameter = null;
	private ArrayList<EvolverStateProcess> EndOfRunProcess = null;
	private ArrayList<String> EndOfRunProcessParameter = null;
	private ArrayList<EvolverStateProcess> EndProcess = null;
	private ArrayList<String> EndProcessParameter = null;
	
	public enum LetterType{
		FUNCTION, TERMINAL, RNC,
		UNKNOWN;
	};
	
	//---------------Getters and Setters-----------------//
	
	public String getTitle(){
		return Title;
	}
	public void setTitle(String value) {
		Title = value;
	}
	public String getDataSetLocation() {
		return DataSetLocation;
	}
	public void setDataSetLocation(String dataSetLocation) {
		DataSetLocation = dataSetLocation;
	}
	public String getDataSetFilename() {
		return DataSetFilename;
	}
	public void setDataSetFilename(String dataSetFilename) {
		DataSetFilename = dataSetFilename;
	}
	public String getDataSetLoaderLocation() {
		return DataSetLoaderLocation;
	}
	public void setDataSetLoaderLocation(String dataSetLoaderLocation) {
		DataSetLoaderLocation = dataSetLoaderLocation;
	}
	public String getDataSetLoaderFilename() {
		return DataSetLoaderFilename;
	}
	public void setDataSetLoaderFilename(String dataSetLoaderFilename) {
		DataSetLoaderFilename = dataSetLoaderFilename;
	}
	public DataSetLoader getDataSetLoader() {
		return this.DataSetLoader;
	}
	public String getDataSetLoaderParameterString() {
		return DataSetLoaderParameterString;
	}
	public void setDataSetLoaderParameterString(String dataSetLoaderParameterString) {
		DataSetLoaderParameterString = dataSetLoaderParameterString;
	}
	public int getNumberOfClasses(){
		return NumberOfClasses;
	}
	public void setNumberOfClasses(int value){
		NumberOfClasses = value;
	}
	public void setNumberOfInputs(int numberOfInputs) {
		NumberOfInputs = numberOfInputs;
	}
	public int getNumberOfInputs() {
		return NumberOfInputs;
	}
	public double getTrainingPercentage() {
		return TrainingPercentage;
	}
	public void setTrainingPercentage(double trainingPercentage) {
		TrainingPercentage = trainingPercentage;
	}
	public int getNumberOfRuns() {
		return NumberOfRuns;
	}
	public void setNumberOfRuns(int numberOfRuns) {
		NumberOfRuns = numberOfRuns;
	}
	public int getNumberOfGenerations() {
		return NumberOfGenerations;
	}
	public void setNumberOfGenerations(int numberOfGenerations) {
		NumberOfGenerations = numberOfGenerations;
	}
	public int getPopulationSize() {
		return PopulationSize;
	}
	public void setPopulationSize(int value) {
		PopulationSize = value;
	}
	public int getNodeHeadSize() {
		return NodeHeadSize;
	}
	public int getNodeTailSize() {
		return NodeTailSize;
	}
	public void setNodeHeadSize(int nodeHeadSize) {
		NodeHeadSize = nodeHeadSize;
	}
	public void setNodeTailSize(){
		NodeTailSize = (getNodeHeadSize() * (NodeFunctionSet.getMaxArgs()-1)) + 1;
	}
	public FunctionSet getNodeFunctionSet() {
		return NodeFunctionSet;
	}
	public void setNodeFunctionSet(FunctionSet nodeFunctionSet) {
		NodeFunctionSet = nodeFunctionSet;
	}
	public int getNumberRNC() {
		return NumberRNC;
	}
	public void setNumberRNC(int numberRNC) {
		NumberRNC = numberRNC;
	}
	public int getNumberLayers() {
		return NumberLayers;
	}
	public void setNumberLayers(int numberLayers) {
		NumberLayers = numberLayers;
	}
	public int[] getNodesInLayer() {
		return NodesInLayer;
	}
	public void setNodesInLayer(int[] nodesInLayer) {
		NodesInLayer = nodesInLayer;
	}
	public EvolverStateProcess getFitnessProcess() {
		return FitnessProcess;
	}
	public void setFitnessProcess(EvolverStateProcess fitnessProcess) {
		FitnessProcess = fitnessProcess;
	}
	public double getKeepPercentage() {
		return KeepPercentage;
	}
	public void setKeepPercentage(double value){
		KeepPercentage = value;
	}
	public SelectionMethod getSelectionMethod() {
		return SelectionMethod;
	}
	public void setSelectionMethod(SelectionMethod selectionMethod) {
		SelectionMethod = selectionMethod;
	}
	public ModificationSet getModificationSet() {
		return ModificationSet;
	}
	public void setModificationSet(ModificationSet modificationSet) {
		ModificationSet = modificationSet;
	}
	public double getMutationRate() {
		return MutationRate;
	}
	public void setMutationRate(double mutationRate) {
		MutationRate = mutationRate;
	}
	public ArrayList<EvolverStateProcess> getStartProcess() {
		return StartProcess;
	}
	public void setStartProcess(ArrayList<EvolverStateProcess> startProcess) {
		StartProcess = startProcess;
	}
	public ArrayList<String> getStartProcessParameter() {
		return StartProcessParameter;
	}
	public void setStartProcessParameter(ArrayList<String> startProcessParameter) {
		StartProcessParameter = startProcessParameter;
	}
	public ArrayList<EvolverStateProcess> getBeforeRunProcess() {
		return BeforeRunProcess;
	}
	public void setBeforeRunProcess(ArrayList<EvolverStateProcess> beforeRunProcess) {
		BeforeRunProcess = beforeRunProcess;
	}
	public ArrayList<String> getBeforeRunProcessParameter() {
		return BeforeRunProcessParameter;
	}
	public void setBeforeRunProcessParameter(
			ArrayList<String> beforeRunProcessParameter) {
		BeforeRunProcessParameter = beforeRunProcessParameter;
	}
	public ArrayList<EvolverStateProcess> getBeforeGenerationProcess() {
		return BeforeGenerationProcess;
	}
	public void setBeforeGenerationProcess(
			ArrayList<EvolverStateProcess> beforeGenerationProcess) {
		BeforeGenerationProcess = beforeGenerationProcess;
	}
	public ArrayList<String> getBeforeGenerationProcessParameter() {
		return BeforeGenerationProcessParameter;
	}
	public void setBeforeGenerationProcessParameter(
			ArrayList<String> beforeGenerationProcessParameter) {
		BeforeGenerationProcessParameter = beforeGenerationProcessParameter;
	}
	public ArrayList<EvolverStateProcess> getEndOfGenerationProcess() {
		return EndOfGenerationProcess;
	}
	public void setEndOfGenerationProcess(
			ArrayList<EvolverStateProcess> endOfGenerationProcess) {
		EndOfGenerationProcess = endOfGenerationProcess;
	}
	public ArrayList<String> getEndOfGenerationProcessParameter() {
		return EndOfGenerationProcessParameter;
	}
	public void setEndOfGenerationProcessParameter(
			ArrayList<String> endOfGenerationProcessParameter) {
		EndOfGenerationProcessParameter = endOfGenerationProcessParameter;
	}
	public ArrayList<EvolverStateProcess> getEndOfRunProcess() {
		return EndOfRunProcess;
	}
	public void setEndOfRunProcess(ArrayList<EvolverStateProcess> endOfRunProcess) {
		EndOfRunProcess = endOfRunProcess;
	}
	public ArrayList<String> getEndOfRunProcessParameter() {
		return EndOfRunProcessParameter;
	}
	public void setEndOfRunProcessParameter(
			ArrayList<String> endOfRunProcessParameter) {
		EndOfRunProcessParameter = endOfRunProcessParameter;
	}
	public ArrayList<EvolverStateProcess> getEndProcess() {
		return EndProcess;
	}
	public void setEndProcess(ArrayList<EvolverStateProcess> endProcess) {
		EndProcess = endProcess;
	}
	public ArrayList<String> getEndProcessParameter() {
		return EndProcessParameter;
	}
	public void setEndProcessParameter(ArrayList<String> endProcessParameter) {
		EndProcessParameter = endProcessParameter;
	}
	
	//---------------------------------------------------//

	byte functionIndexEnd = 0;
	byte terminalIndexEnd = 0;
	byte rncIndexEnd = 0;
	
	//---------------------------------------------------//
	
	private DocumentBuilderFactory domFactory;
	private DocumentBuilder builder;
	private Document doc;
	private XPathFactory factory;
	XPath xpath;
	private boolean xpathinitialized = false;
	
	private void InitXPath(String filename) throws ParserConfigurationException, SAXException, IOException {
		if( xpathinitialized ) return;
		domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true); // never forget this!
	    builder = domFactory.newDocumentBuilder();
	    doc = builder.parse(filename);

	    factory = XPathFactory.newInstance();
	    xpath = factory.newXPath();
	    xpathinitialized = true;
	}
	private NodeList getNodes(String expression) throws XPathExpressionException{
		//For each part of the config, load it with an XPathExpression object
	    XPathExpression expr = xpath.compile(expression);

	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    return nodes;
	}
	private Node getNode(String expression) throws XPathExpressionException{
		return getNodes(expression).item(0);
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
	private void getESPList(String expression, ArrayList<EvolverStateProcess> procList, 
			ArrayList<String> paraList) throws XPathExpressionException{
		NodeList ProcNodes = getNodes(expression);
		for( int i = 0; i < ProcNodes.getLength(); ++i){
			//For each crossover node
			Node ProcNode = ProcNodes.item(i);
			//Read it's name, and location
			NamedNodeMap attrib = ProcNode.getAttributes();
			String ProcClassName = attrib.getNamedItem("classfile").getNodeValue();
			
			String ProcClassDir = (attrib.getNamedItem("location") == null? "bin/" :
									attrib.getNamedItem("location").getNodeValue());
			String params = (attrib.getNamedItem("parameters") == null? "" :
								attrib.getNamedItem("parameters").getNodeValue());
			//Get the class from the file
			Class<?> ProcClass = getClassFromFile(ProcClassDir, ProcClassName);
			//Instantiate the function
			EvolverStateProcess Proc = (EvolverStateProcess) createInstanceOf(ProcClass);	
			
			//Add to the modification set
			procList.add(Proc);
			paraList.add(params);
		}
	}
	
	//---------------------------------------------------//
	
	
	private void LoadDataSetInformation() throws XPathExpressionException{
		Title = getStringValue("//Title");
		DataSetLocation = getStringValue("//DataSet/FileLocation");
		DataSetFilename = getStringValue("//DataSet/FileName");
		TrainingPercentage = getDoubleValue("//DataSet/TrainPercentage");
				
		Node dslnode = getNode("//DataSet/DataSetLoader");
		NamedNodeMap attrib = dslnode.getAttributes();
		DataSetLoaderFilename = attrib.getNamedItem("classfile").getNodeValue();
		DataSetLoaderLocation = 
			(attrib.getNamedItem("location") == null? "bin/" : 
		     attrib.getNamedItem("location").getNodeValue());
		
		DataSetLoaderParameterString = 
			(attrib.getNamedItem("parameters") == null? "" : 
			     attrib.getNamedItem("parameters").getNodeValue());
		
		Class<?> dslClass = getClassFromFile(
				DataSetLoaderLocation, DataSetLoaderFilename);
		
		this.DataSetLoader = (framework.DataSetLoader) 
								createInstanceOf(dslClass);
		this.DataSetLoader.Initialize(DataSetLoaderParameterString);
		NumberOfClasses = getIntValue("//DataSet/NumberOfClasses");
		NumberOfInputs = getIntValue("//DataSet/NumberOfInputs");
	}
	
	public void LoadAlgorithmInformation() throws XPathExpressionException{
		NumberOfRuns = getIntValue("//Runs");
		NumberOfGenerations = getIntValue("//Generations");
		PopulationSize = getIntValue("//PopulationSize");
			
		Node fitnessNode = getNode("//Fitness");
		String fitnessClassName = fitnessNode.getAttributes()
			.getNamedItem("classfile").getNodeValue();
		Node fitnessLocationNode = fitnessNode.getAttributes()
			.getNamedItem("location");
		String fitnessClassDir = "bin/";
		if( fitnessLocationNode != null ) {
			fitnessClassDir = fitnessLocationNode.getNodeValue();
		}
		Class<?> fitnessClass = getClassFromFile(fitnessClassDir, fitnessClassName); 
		this.FitnessProcess = (EvolverStateProcess) createInstanceOf(fitnessClass);


		Node selectionNode = getNode("//Selection");
		String selectionClassName = selectionNode.getAttributes()
			.getNamedItem("classfile").getNodeValue();
		Node selectionLocationNode = selectionNode.getAttributes()
			.getNamedItem("location");
		String selectionClassDir = "bin/";
		if( selectionLocationNode != null ) {
			selectionClassDir = selectionLocationNode.getNodeValue();
		}
		Class<?> selectionClass = getClassFromFile(selectionClassDir, selectionClassName); 
		this.SelectionMethod = (SelectionMethod) createInstanceOf(selectionClass);
		
		KeepPercentage = Double.parseDouble(selectionNode.getAttributes().getNamedItem("keep").getNodeValue());
	
		this.ModificationSet = new ModificationSet();
		
		NodeList crossoversNodes = getNodes("//Crossovers/Crossover");
		for( int i = 0; i < crossoversNodes.getLength(); ++i){
			//For each crossover node
			Node crossNode = crossoversNodes.item(i);
			//Read it's name, and location
			String crossClassName = crossNode.getAttributes()
				.getNamedItem("classfile").getNodeValue();
			
			Node crossLocationNode = crossNode.getAttributes()
				.getNamedItem("location");
			String crossClassDir = "bin/";
			if( crossLocationNode != null ) {
				crossClassDir = crossLocationNode.getNodeValue();
			}
			//Get the class from the file
			Class<?> crossClass = getClassFromFile(crossClassDir, crossClassName);
			//Instantiate the function
			Crossover cross = (Crossover) createInstanceOf(crossClass);
			
			int crossWeight = Integer.parseInt(crossNode.getAttributes().getNamedItem("weight").getNodeValue());
			
			//Add to the modification set
			this.ModificationSet.addCrossover(cross, crossWeight);		
		}
	
		this.MutationRate = getDoubleValue("//MutationRate");
		
		NodeList mutatorNodes = getNodes("//Mutators/Mutator");
		for( int i = 0; i < mutatorNodes.getLength(); ++i){
			//For each crossover node
			Node mutatorNode = mutatorNodes.item(i);
			//Read it's name, and location
			String mutatorClassName = mutatorNode.getAttributes()
				.getNamedItem("classfile").getNodeValue();
			
			Node mutatorLocationNode = mutatorNode.getAttributes()
				.getNamedItem("location");
			String mutatorClassDir = "bin/";
			if( mutatorLocationNode != null ) {
				mutatorClassDir = mutatorLocationNode.getNodeValue();
			}
			//Get the class from the file
			Class<?> mutatorClass = getClassFromFile(mutatorClassDir, mutatorClassName);
			//Instantiate the function
			Mutator mutator = (Mutator) createInstanceOf(mutatorClass);
			
			int mutatorWeight = Integer.parseInt(mutatorNode.getAttributes().getNamedItem("weight").getNodeValue());
			
			//Add to the modification set
			this.ModificationSet.addMutator(mutator, mutatorWeight);		
		}		
		
	}
	
	public void LoadNodeInformation() throws XPathExpressionException {
		setNodeHeadSize( getIntValue("//NodeDescription/Head") );
		NodeFunctionSet = new FunctionSet();
		
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
			Function function = (Function)createInstanceOf(funcClass);
			function.setSymbol((byte) i);
			//Add to the functionset
			NodeFunctionSet.addFunction(function);		
		}
		
		setNodeTailSize();
		
		NumberRNC = getIntValue("//NodeDescription/RNC");
		NodeList layerNodes = getNodes("//NodeLayers/Layer/Nodes/text()");
		NumberLayers = layerNodes.getLength() + 1;
		this.NodesInLayer = new int[NumberLayers];
		int maxTerminals = NumberOfClasses;
		for(int i = 0; i < layerNodes.getLength(); ++i){
			NodesInLayer[i] = Integer.parseInt(layerNodes.item(i).getNodeValue());
			if( NodesInLayer[i] > maxTerminals )
				maxTerminals = NodesInLayer[i];
		}
		NodesInLayer[NumberLayers-1] = NumberOfClasses;
		
		
		functionIndexEnd = (byte) NodeFunctionSet.size();
		terminalIndexEnd = (byte) (functionIndexEnd + maxTerminals);
		rncIndexEnd = (byte) (terminalIndexEnd + NumberRNC);
	}
	
	private void LoadEvolverStateProcesses() throws XPathExpressionException{
		
		StartProcess = new ArrayList<EvolverStateProcess>();
		StartProcessParameter = new ArrayList<String>();
		BeforeRunProcess = new ArrayList<EvolverStateProcess>();
		BeforeRunProcessParameter = new ArrayList<String>();
		BeforeGenerationProcess = new ArrayList<EvolverStateProcess>();
		BeforeGenerationProcessParameter = new ArrayList<String>();
		EndOfGenerationProcess = new ArrayList<EvolverStateProcess>();
		EndOfGenerationProcessParameter = new ArrayList<String>();
		EndOfRunProcess = new ArrayList<EvolverStateProcess>();
		EndOfRunProcessParameter = new ArrayList<String>();
		EndProcess = new ArrayList<EvolverStateProcess>();
		EndProcessParameter = new ArrayList<String>();
		
		getESPList("//Processes/StartProcesses/Process", StartProcess, StartProcessParameter);
		getESPList("//Processes/EndProcesses/Process", EndProcess, EndProcessParameter);
		
		getESPList("//Processes/BeforeRunProcesses/Process", BeforeRunProcess, BeforeRunProcessParameter);
		getESPList("//Processes/EndRunProcesses/Process", EndOfRunProcess, EndOfRunProcessParameter);
		
		getESPList("//Processes/BeforeGenerationProcesses/Process", BeforeGenerationProcess, BeforeGenerationProcessParameter);
		getESPList("//Processes/EndGenerationProcesses/Process", EndOfGenerationProcess, EndOfGenerationProcessParameter);		
	}
	
	public void LoadConfigurationFile(String filename) 
	throws ParserConfigurationException, SAXException, 
		   IOException, XPathExpressionException {
	
		InitXPath(filename);
		LoadDataSetInformation();
		LoadAlgorithmInformation();
		LoadNodeInformation();
		LoadEvolverStateProcesses();
		

	}
	
	
	//---------------------------------------------------//
	
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
	
	private static Object createInstanceOf(Class<?> c) {
		Constructor<?> ct = c.getConstructors()[0];
		Object obj = null;
		try {
			obj = ct.newInstance(new Object[]{});
		} catch (Exception e) {
			System.err.println("Could not instantiate instance of type '" + c.getCanonicalName() + "'.");
			return null;
		}	
		return obj;
	}
	
	
	//---------------------------------------------------//
	
	public int getNodeLength(){
		return getNodeHeadSize() + getNodeTailSize();
	}
	
	public int getNodesInLayer(int layer){
		return NodesInLayer[layer];
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
	
	public byte[] getTerminalValues(int layerNum) {	
		int numTerminals = (layerNum == 0? NumberOfInputs : NodesInLayer[layerNum-1]);
		byte[] terminalValues = new byte[numTerminals];
		for( byte i = 0; i < numTerminals; ++i){
			terminalValues[i] = (byte) (functionIndexEnd + i);
		}
		return terminalValues;		
	}
	
	private byte[] _rncValues = null;
	public byte[] getRNCValues() {
		if( _rncValues == null) {
			_rncValues = new byte[rncIndexEnd - terminalIndexEnd];
			for( byte i = 0; i < rncIndexEnd-terminalIndexEnd; ++i){
				_rncValues[i] = (byte) (terminalIndexEnd + i);
			}
		}
		return _rncValues;
	}
	
	private byte[][] layerHeadValues = null;
	public byte[] getHeadValues(int layerNum) {
		if( layerHeadValues == null ) layerHeadValues = new byte[NumberLayers][];
		
		if( layerHeadValues[layerNum] == null) {
			byte[] functionValues = getFunctionValues();
			byte[] terminalValues = getTerminalValues(layerNum);
			byte[] rncValues = getRNCValues();
			layerHeadValues[layerNum] = new byte[functionValues.length
			                                   + terminalValues.length
			                                   + rncValues.length];
			int itt = 0;
			for( byte b : functionValues ){
				layerHeadValues[layerNum][itt] = b;
				itt++;
			}
			for( byte b : terminalValues ){
				layerHeadValues[layerNum][itt] = b;
				itt++;
			}
			for( byte b : rncValues ){
				layerHeadValues[layerNum][itt] = b;
				itt++;
			}
		}
		return layerHeadValues[layerNum];
	}
	
	private byte[][] layerTailValues = null;
	public byte[] getTailValues(int layerNum) {
		if( layerTailValues == null ) layerTailValues = new byte[NumberLayers][];
		
		if( layerTailValues[layerNum] == null) {
			byte[] terminalValues = getTerminalValues(layerNum);
			byte[] rncValues = getRNCValues();
			layerTailValues[layerNum] = new byte[terminalValues.length
			                                   + rncValues.length];
			int itt = 0;
			for( byte b : terminalValues ){
				layerTailValues[layerNum][itt] = b;
				itt++;
			}
			for( byte b : rncValues ){
				layerTailValues[layerNum][itt] = b;
				itt++;
			}
		}
		return layerTailValues[layerNum];
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
