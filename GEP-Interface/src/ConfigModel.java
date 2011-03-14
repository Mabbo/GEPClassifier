import java.io.IOException;
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

import com.sun.org.apache.xpath.internal.NodeSet;
import com.sun.xml.internal.bind.v2.model.core.ClassInfo;

public class ConfigModel {

	private String Title = "";
	private String DataSetFile = null;
	private String DataSetLoaderLocation = null;
	private String DataSetLoaderFilename = null;
	private String DataSetLoaderParameterString = null;
	private int NumberOfInputs = 1;
	private int NumberOfClasses = 1;
	private double TrainingPercentage = 1.0;
	private int NumberOfRuns = 1;
	private int NumberOfGenerations = 1;
	private int PopulationSize = 10;
	
	//---------------------------------------------------//
	
	private int NodeHeadSize = 2;
	private ArrayList<String> FunctionLocations;
	private ArrayList<String> FunctionFiles;
	private int NumberRNC = 0;
	private int NumberLayers = 0;
	private int[] NodesInLayer = null;
	
	//---------------------------------------------------//
	
	private String FitnessProcessLocation;
	private String FitnessProcessFile;
	private double KeepPercentage = 0.75;
	private String SelectionMethodLocation;
	private String SelectionMethodFile;
	private ArrayList<String> MutatorLocations;
	private ArrayList<String> MutatorFiles;
	private ArrayList<String> CrossoverLocations;
	private ArrayList<String> CrossoverFiles;
	private double MutationRate = 0.1;

	//---------------------------------------------------//
	
	private ArrayList<String> StartProcessLocations = null;
	private ArrayList<String> StartProcessParameter = null;
	private ArrayList<String> StartProcessFiles = null;
	private ArrayList<String> BeforeRunProcessLocations = null;
	private ArrayList<String> BeforeRunProcessFiles = null;
	private ArrayList<String> BeforeRunProcessParameter = null;
	private ArrayList<String> BeforeGenerationProcessLocations = null;
	private ArrayList<String> BeforeGenerationProcessFiles = null;
	private ArrayList<String> BeforeGenerationProcessParameter = null;
	private ArrayList<String> EndOfGenerationProcessFiles = null;
	private ArrayList<String> EndOfGenerationProcessLocations = null;
	private ArrayList<String> EndOfGenerationProcessParameter = null;
	private ArrayList<String> EndOfRunProcessLocations = null;
	private ArrayList<String> EndOfRunProcessFiles = null;
	private ArrayList<String> EndOfRunProcessParameter = null;
	private ArrayList<String> EndProcessLocations = null;
	private ArrayList<String> EndProcessFiles = null;
	private ArrayList<String> EndProcessParameter = null;
	
	
	public ConfigModel(){

		Title = "";
		DataSetFile = "";
		DataSetLoaderFilename = "";
		DataSetLoaderParameterString = "";
		NumberOfInputs = 1;
		NumberOfClasses = 1;
		TrainingPercentage = 1.0;
		NumberOfRuns = 1;
		NumberOfGenerations = 1;
		PopulationSize = 10;
		
		NodeHeadSize = 2;
		FunctionLocations = new ArrayList<String>();
		FunctionFiles = new ArrayList<String>();
		
		NumberRNC = 0;
		NumberLayers = 0;
		NodesInLayer = new int[0];
		
		//---------------------------------------------------//
		
		FitnessProcessLocation = "";
		FitnessProcessFile = "";
		KeepPercentage = 0.75;
		SelectionMethodLocation = "";
		SelectionMethodFile = "";
		MutatorLocations = new ArrayList<String>();
		MutatorFiles = new ArrayList<String>();
		CrossoverLocations = new ArrayList<String>();
		CrossoverFiles = new ArrayList<String>();
		MutationRate = 0.1;

		//---------------------------------------------------//
		
		StartProcessLocations = new ArrayList<String>();
		StartProcessFiles = new ArrayList<String>();
		StartProcessParameter = new ArrayList<String>();
		BeforeRunProcessLocations = new ArrayList<String>();
		BeforeRunProcessFiles = new ArrayList<String>();
		BeforeRunProcessParameter = new ArrayList<String>();
		
		BeforeGenerationProcessLocations = new ArrayList<String>();
		BeforeGenerationProcessFiles  = new ArrayList<String>();
		BeforeGenerationProcessParameter = new ArrayList<String>();
		EndOfGenerationProcessLocations = new ArrayList<String>();
		EndOfGenerationProcessFiles = new ArrayList<String>();
		EndOfGenerationProcessParameter = new ArrayList<String>();
		EndOfRunProcessLocations = new ArrayList<String>();
		EndOfRunProcessFiles = new ArrayList<String>();
		EndOfRunProcessParameter = new ArrayList<String>();
		EndProcessLocations = new ArrayList<String>();
		EndProcessFiles = new ArrayList<String>();
		EndProcessParameter = new ArrayList<String>();
	}
	
	public String getFitnessProcessFile() {
		return FitnessProcessFile;
	}

	public void setFitnessProcessFile(String fitnessProcessFile) {
		FitnessProcessFile = fitnessProcessFile;
	}

	public String getSelectionMethodFile() {
		return SelectionMethodFile;
	}

	public void setSelectionMethodFile(String selectionMethodFile) {
		SelectionMethodFile = selectionMethodFile;
	}

	public ArrayList<String> getMutatorFiles() {
		return MutatorFiles;
	}

	public void setMutatorFiles(ArrayList<String> mutatorFiles) {
		MutatorFiles = mutatorFiles;
	}

	public ArrayList<String> getCrossoverFiles() {
		return CrossoverFiles;
	}

	public void setCrossoverFiles(ArrayList<String> crossoverFiles) {
		CrossoverFiles = crossoverFiles;
	}

	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDataSetFile() {
		return DataSetFile;
	}
	public void setDataSetFile(String dataSetFile) {
		DataSetFile = dataSetFile;
	}
	public String getDataSetLoaderFilename() {
		return DataSetLoaderFilename;
	}
	public void setDataSetLoaderFilename(String dataSetLoaderFilename) {
		DataSetLoaderFilename = dataSetLoaderFilename;
	}
	public String getDataSetLoaderParameterString() {
		return DataSetLoaderParameterString;
	}
	public void setDataSetLoaderParameterString(String dataSetLoaderParameterString) {
		DataSetLoaderParameterString = dataSetLoaderParameterString;
	}
	public int getNumberOfInputs() {
		return NumberOfInputs;
	}
	public void setNumberOfInputs(int numberOfInputs) {
		NumberOfInputs = numberOfInputs;
	}
	public int getNumberOfClasses() {
		return NumberOfClasses;
	}
	public void setNumberOfClasses(int numberOfClasses) {
		NumberOfClasses = numberOfClasses;
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
	public ArrayList<String> getStartProcessFiles() {
		return StartProcessFiles;
	}

	public void setStartProcessFiles(ArrayList<String> startProcessFiles) {
		StartProcessFiles = startProcessFiles;
	}

	public ArrayList<String> getBeforeRunProcessFiles() {
		return BeforeRunProcessFiles;
	}

	public void setBeforeRunProcessFiles(ArrayList<String> beforeRunProcessFiles) {
		BeforeRunProcessFiles = beforeRunProcessFiles;
	}

	public ArrayList<String> getBeforeGenerationProcessFiles() {
		return BeforeGenerationProcessFiles;
	}

	public void setBeforeGenerationProcessFiles(
			ArrayList<String> beforeGenerationProcessFiles) {
		BeforeGenerationProcessFiles = beforeGenerationProcessFiles;
	}

	public ArrayList<String> getEndOfGenerationProcessFiles() {
		return EndOfGenerationProcessFiles;
	}

	public void setEndOfGenerationProcessFiles(
			ArrayList<String> endOfGenerationProcessFiles) {
		EndOfGenerationProcessFiles = endOfGenerationProcessFiles;
	}

	public ArrayList<String> getEndOfRunProcessFiles() {
		return EndOfRunProcessFiles;
	}

	public void setEndOfRunProcessFiles(ArrayList<String> endOfRunProcessFiles) {
		EndOfRunProcessFiles = endOfRunProcessFiles;
	}

	public ArrayList<String> getEndProcessFiles() {
		return EndProcessFiles;
	}

	public void setEndProcessFiles(ArrayList<String> endProcessFiles) {
		EndProcessFiles = endProcessFiles;
	}

	public void setNumberOfGenerations(int numberOfGenerations) {
		NumberOfGenerations = numberOfGenerations;
	}
	public int getPopulationSize() {
		return PopulationSize;
	}
	public void setPopulationSize(int populationSize) {
		PopulationSize = populationSize;
	}
	public int getNodeHeadSize() {
		return NodeHeadSize;
	}
	public void setNodeHeadSize(int nodeHeadSize) {
		NodeHeadSize = nodeHeadSize;
	}
	public ArrayList<String> getFunctionLocations() {
		return FunctionLocations;
	}
	public void setFunctionLocations(ArrayList<String> functionLocations) {
		FunctionLocations = functionLocations;
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
	public String getFitnessProcessLocation() {
		return FitnessProcessLocation;
	}
	public void setFitnessProcessLocation(String fitnessProcessLocation) {
		FitnessProcessLocation = fitnessProcessLocation;
	}
	public double getKeepPercentage() {
		return KeepPercentage;
	}
	public void setKeepPercentage(double keepPercentage) {
		KeepPercentage = keepPercentage;
	}
	public String getSelectionMethodLocation() {
		return SelectionMethodLocation;
	}
	public void setSelectionMethodLocation(String selectionMethodLocation) {
		SelectionMethodLocation = selectionMethodLocation;
	}
	public ArrayList<String> getMutatorLocations() {
		return MutatorLocations;
	}
	public void setMutatorLocations(ArrayList<String> mutatorLocations) {
		MutatorLocations = mutatorLocations;
	}
	public ArrayList<String> getCrossoverLocations() {
		return CrossoverLocations;
	}
	public void setCrossoverLocations(ArrayList<String> crossoverLocations) {
		CrossoverLocations = crossoverLocations;
	}
	public double getMutationRate() {
		return MutationRate;
	}
	public void setMutationRate(double mutationRate) {
		MutationRate = mutationRate;
	}
	public ArrayList<String> getStartProcessLocations() {
		return StartProcessLocations;
	}
	public void setStartProcessLocations(ArrayList<String> startProcessLocations) {
		StartProcessLocations = startProcessLocations;
	}
	public ArrayList<String> getStartProcessParameter() {
		return StartProcessParameter;
	}
	public void setStartProcessParameter(ArrayList<String> startProcessParameter) {
		StartProcessParameter = startProcessParameter;
	}
	public ArrayList<String> getBeforeRunProcessLocations() {
		return BeforeRunProcessLocations;
	}
	public void setBeforeRunProcessLocations(
			ArrayList<String> beforeRunProcessLocations) {
		BeforeRunProcessLocations = beforeRunProcessLocations;
	}
	public ArrayList<String> getBeforeRunProcessParameter() {
		return BeforeRunProcessParameter;
	}
	public void setBeforeRunProcessParameter(
			ArrayList<String> beforeRunProcessParameter) {
		BeforeRunProcessParameter = beforeRunProcessParameter;
	}
	public ArrayList<String> getBeforeGenerationProcessLocations() {
		return BeforeGenerationProcessLocations;
	}
	public void setBeforeGenerationProcessLocations(
			ArrayList<String> beforeGenerationProcessLocations) {
		BeforeGenerationProcessLocations = beforeGenerationProcessLocations;
	}
	public ArrayList<String> getBeforeGenerationProcessParameter() {
		return BeforeGenerationProcessParameter;
	}
	public void setBeforeGenerationProcessParameter(
			ArrayList<String> beforeGenerationProcessParameter) {
		BeforeGenerationProcessParameter = beforeGenerationProcessParameter;
	}
	public ArrayList<String> getEndOfGenerationProcessLocations() {
		return EndOfGenerationProcessLocations;
	}
	public void setEndOfGenerationProcessLocations(
			ArrayList<String> endOfGenerationProcessLocations) {
		EndOfGenerationProcessLocations = endOfGenerationProcessLocations;
	}
	public ArrayList<String> getEndOfGenerationProcessParameter() {
		return EndOfGenerationProcessParameter;
	}
	public void setEndOfGenerationProcessParameter(
			ArrayList<String> endOfGenerationProcessParameter) {
		EndOfGenerationProcessParameter = endOfGenerationProcessParameter;
	}
	public ArrayList<String> getEndOfRunProcessLocations() {
		return EndOfRunProcessLocations;
	}
	public void setEndOfRunProcessLocations(
			ArrayList<String> endOfRunProcessLocations) {
		EndOfRunProcessLocations = endOfRunProcessLocations;
	}
	public ArrayList<String> getEndOfRunProcessParameter() {
		return EndOfRunProcessParameter;
	}
	public void setEndOfRunProcessParameter(
			ArrayList<String> endOfRunProcessParameter) {
		EndOfRunProcessParameter = endOfRunProcessParameter;
	}
	public ArrayList<String> getEndProcessLocations() {
		return EndProcessLocations;
	}
	public void setEndProcessLocations(ArrayList<String> endProcessLocations) {
		EndProcessLocations = endProcessLocations;
	}
	public ArrayList<String> getEndProcessParameter() {
		return EndProcessParameter;
	}
	public void setEndProcessParameter(ArrayList<String> endProcessParameter) {
		EndProcessParameter = endProcessParameter;
	}
	
	
	
	
	//Reads a config model from an xml file
	public static ConfigModel OpenConfig(String filename) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		ConfigModel conf = new ConfigModel();
		DocumentBuilderFactory domFactory;
		DocumentBuilder builder;
		Document doc;
		XPathFactory factory;
		XPath path;
		
		domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true);
	    builder = domFactory.newDocumentBuilder();
	    doc = builder.parse(filename);

	    factory = XPathFactory.newInstance();
	    path = factory.newXPath();
		
	    //Read each item from the XPath values
		
	    conf.setTitle( getStringValue("//Title", path, doc) );
		conf.setDataSetFile(getStringValue("//DataSet/File", path, doc));
		
		ClassInformation cinfo = getClassInformation("//Data/DataSetLoader", path, doc);
		
		conf.setDataSetLoaderFilename(cinfo.file);
		conf.setDataSetLoaderLocation(cinfo.dir);
		conf.setDataSetLoaderParameterString(cinfo.param);
		conf.setNumberOfInputs(getIntValue("//DataSet/NumberOfInputs", path, doc));
		conf.setNumberOfClasses( getIntValue("//DataSet/NumberOfClasses", path, doc) );
		conf.setTrainingPercentage( getDoubleValue("//DataSet/TrainPercentage", path, doc) );
		
		conf.setNumberOfRuns( getIntValue("//Runs", path, doc) );
		conf.setNumberOfGenerations( getIntValue("//Generations", path, doc) );
		conf.setPopulationSize( getIntValue("//PopulationSize", path, doc)  );
		
		conf.setNodeHeadSize(getIntValue("//NodeDescription/Head",path,doc));
		getFunctionList("//NodeDescription/FunctionSet/Function", conf.getFunctionLocations(), conf.getFunctionFiles(), path, doc);
		
		conf.setNumberRNC(getIntValue("//NodeDescription/RNC",path, doc));
		int layers = getNodes("//NodeLayers/Layer",path,doc).getLength();
		conf.setNumberLayers(layers);
		int[] NodesInLayer = new int[layers];
		conf.setNodesInLayer(NodesInLayer);
		
		//---------------------------------------------------//
		
		cinfo = getClassInformation("//Fitness", path, doc);
		
		conf.setFitnessProcessLocation( cinfo.dir );
		conf.setFitnessProcessFile(cinfo.file);
		conf.setKeepPercentage(Double.parseDouble(getAttribute("//Selection", "keep", path, doc)));
		
		cinfo = getClassInformation("//Selection",path,doc);
		conf.setSelectionMethodLocation(cinfo.dir);
		conf.setSelectionMethodFile(cinfo.file);
		
		
		getClassList("//Mutators/Mutator", conf.getMutatorLocations(), conf.getMutatorFiles(), null, path, doc);		
		getClassList("//Crossovers/Crossover", conf.getCrossoverLocations(), conf.getCrossoverFiles(), null, path, doc);

		conf.setMutationRate( getDoubleValue("//MutationRate", path, doc));

		//---------------------------------------------------//
	
		getClassList("//Processes/StartProcesses/Process", conf.getStartProcessLocations(), 
				conf.getStartProcessFiles(), conf.getStartProcessParameter(), path, doc);
		getClassList("//Processes/BeforeRunProcesses/Process", conf.getBeforeRunProcessLocations(), 
				conf.getBeforeRunProcessFiles(), conf.getBeforeRunProcessParameter(), path, doc);
		getClassList("//Processes/BeforeGenerationProcesses/Process", conf.getBeforeGenerationProcessLocations(), 
				conf.getBeforeGenerationProcessFiles(), conf.getBeforeGenerationProcessParameter(), path, doc);
		getClassList("//Processes/EndOfGenerationProcesses/Process", conf.getEndOfGenerationProcessLocations(), 
				conf.getEndOfGenerationProcessFiles(), conf.getEndOfGenerationProcessParameter(), path, doc);
		getClassList("//Processes/EndOfRunProcesses/Process", conf.getEndOfRunProcessLocations(), 
				conf.getEndOfRunProcessFiles(), conf.getEndOfRunProcessParameter(), path, doc);
		getClassList("//Processes/EndProcesses/Process", conf.getEndProcessLocations(), 
				conf.getEndProcessFiles(), conf.getEndProcessParameter(), path, doc);
		
		return conf;
	}
	
	private static NodeList getNodes(String expression, XPath xpath, Document doc) throws XPathExpressionException{
		//For each part of the config, load it with an XPathExpression object
	    XPathExpression expr = xpath.compile(expression);

	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    return nodes;
	}
	private static Node getNode(String expression, XPath path, Document doc) throws XPathExpressionException{
		return getNodes(expression, path, doc).item(0);
	}
	private static String getStringValue(String expression, XPath path, Document doc) throws XPathExpressionException {
		NodeList nodes = getNodes(expression + "/text()", path, doc);
	    for (int i = 0; i < nodes.getLength();) {
	        return nodes.item(i).getNodeValue(); 
	    }	
	    return "";
	}
	private static String getAttribute(String expression, String attribute, XPath path, Document doc) throws XPathExpressionException{
		Node dslnode = getNode(expression, path, doc);	
		NamedNodeMap attrib = dslnode.getAttributes();
		String val = 
			(attrib.getNamedItem("classfile") == null? "" :
	         attrib.getNamedItem("classfile").getNodeValue());
		
		return val;
	}
	private static double getDoubleValue(String expression, XPath path, Document doc) throws XPathExpressionException {
		NodeList nodes = getNodes(expression + "/text()", path, doc);
	    for (int i = 0; i < nodes.getLength();) {
	        return Double.parseDouble(nodes.item(i).getNodeValue()); 
	    }	
	    return 0.0;
	}
	private static int getIntValue(String expression, XPath path, Document doc) throws XPathExpressionException {
		NodeList nodes = getNodes(expression + "/text()", path, doc);
	    for (int i = 0; i < nodes.getLength();) {
	        return Integer.parseInt(nodes.item(i).getNodeValue()); 
	    }	
	    return 0;
	}
	private static void getClassList(String expression, ArrayList<String> procDir, ArrayList<String> procFile,  
			ArrayList<String> paraList, XPath path, Document doc) throws XPathExpressionException{
		NodeList ProcNodes = getNodes(expression, path, doc);
		for( int i = 0; i < ProcNodes.getLength(); ++i){
			Node ProcNode = ProcNodes.item(i);
			ClassInformation cinfo = getClassInformation(ProcNode);
			
			procDir.add(cinfo.dir);
			procFile.add(cinfo.file);
			if( paraList != null) {
				paraList.add(cinfo.param);
			}
		}
	}
	
	
	
	private static void getFunctionList(String expression, ArrayList<String> locations, ArrayList<String> files, XPath path, Document doc) throws XPathExpressionException{
		NodeList functions = getNodes("//NodeDescription/FunctionSet/Function", path, doc);
		for( int i = 0; i < functions.getLength(); ++i) {
			Node node = functions.item(i);
			ClassInformation cinfo = getClassInformation(node);
			String ClassName = cinfo.file;
			String ClassDir = cinfo.dir;
			locations.add(ClassDir);
			files.add(ClassName);			
		}	
	}
	
	
	
	public void SaveModel(String filename){
		
		
		
	}

	public void setDataSetLoaderLocation(String dataSetLoaderLocation) {
		DataSetLoaderLocation = dataSetLoaderLocation;
	}

	public String getDataSetLoaderLocation() {
		return DataSetLoaderLocation;
	}

	public void setFunctionFiles(ArrayList<String> functionFiles) {
		FunctionFiles = functionFiles;
	}

	public ArrayList<String> getFunctionFiles() {
		return FunctionFiles;
	}
	
	
	
	
	private static ClassInformation getClassInformation(String Path, XPath xpath, Document doc) throws XPathExpressionException{
		Node node = getNode(Path, xpath, doc);
		return getClassInformation(node);
	}
	
	private static ClassInformation getClassInformation(Node node) throws XPathExpressionException{
		NamedNodeMap attrib = node.getAttributes();
		ClassInformation cinfo = new ClassInformation();
		boolean isBuiltIn = (attrib.getNamedItem("builtin") != null);
		String sfilename = "";
		String slocation = "";
		String sparams = "";
		if( !isBuiltIn ) {
			sfilename = attrib.getNamedItem("classfile").getNodeValue();
			slocation = 
				(attrib.getNamedItem("location") == null? "bin/" : 
			     attrib.getNamedItem("location").getNodeValue());
		} else {
			sfilename = "builtin." + attrib.getNamedItem("builtin").getNodeValue();
			slocation = "bin/";
		}
		sparams = 
			(attrib.getNamedItem("parameters") == null? "" : 
			     attrib.getNamedItem("parameters").getNodeValue());
		cinfo.file=  sfilename;
		cinfo.dir = slocation;
		cinfo.param = sparams;
		return cinfo;
	}
	
	//---------------------------------------------------//
	
	
	private static class ClassInformation{
		public String file;
		public String dir;
		public String param;
		public ClassInformation(){
			file = dir = param = "";
		}
	}
	
}
