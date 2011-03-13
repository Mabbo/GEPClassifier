import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import base.Config;


public class ConfigPanel extends JPanel {
	private JLabel lblTitle;
	private JTextField txtTitle;
	
	private JLabel lblDataSet;
	private JTextField txtDataSet;
	private JButton butLoadDataSet;
	
	private JLabel lblDataSetLoader;
	private JTextField txtDataSetLoader;
	private JButton butLoadDataSetLoader;
	
	private JLabel lblDataSetLoaderParameters;
	private JTextField txtDataSetLoaderParameters;
	
	private JLabel lblNumInputs;
	private JTextField txtNumInputs;
	
	private JLabel lblNumClasses;
	private JTextField txtNumClasses;
	
	private JLabel lblTrainPercentage;
	private JTextField txtTrainPercentage;
	
	private JLabel lblNumberOfRuns;
	private JTextField txtNumberOfRuns;
	
	private JLabel lblNumberOfGenerations;
	private JTextField txtNumberOfGenerations;
	
	private JLabel lblPopulationSize;
	private JTextField txtPopulationSize;
	
	//--------------------------------------//
	
	private JLabel lblNodeHeadSize;
	private JTextField txtNodeHeadSize;
	
	private JLabel lblNodeTailSize;
	private JTextField txtNodeTailSize;
	
	private JLabel lblFunctionSet;
	private FunctionSetLabel fslblFunctionSet;
	
	
	
	
	//--------------------------------------//
	
	
	private Config config = null;
	
	public ConfigPanel(Config config){
		super();
		this.config = config;
		LoadVisualItems();
		UpdateView();
	}
	
	private final int LeftWeight = 100;
	private final int RightWeight = 80;
	private final int WeightY = 1;
	private final int TextFieldWidth = 15;
	private int currentY = 0;
	
	GridBagLayout layout = null;
	GridBagConstraints cons = null;
	
	public void LoadVisualItems(){
	
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		setLayout(layout);
		
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = WeightY;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(10, 5, 10, 5);
		
		//-------
		lblTitle = new JLabel("Title");	
		txtTitle = new JTextField(TextFieldWidth);
		AddItem(lblTitle, txtTitle);
		
		lblDataSet = new JLabel("DataSet");
		txtDataSet = new JTextField(TextFieldWidth);
		butLoadDataSet = new JButton("Load DataSet");
		AddItem(lblDataSet, txtDataSet, butLoadDataSet);
		
		lblDataSetLoader = new JLabel("DataSet Loader");
		txtDataSetLoader = new JTextField(TextFieldWidth);
		butLoadDataSetLoader = new JButton("Load DataSetLoader");
		AddItem(lblDataSetLoader, txtDataSetLoader, butLoadDataSetLoader);
	
		lblDataSetLoaderParameters = new JLabel("DataSet Loader Parameters");
		txtDataSetLoaderParameters = new JTextField(TextFieldWidth);
		AddItem(lblDataSetLoaderParameters, txtDataSetLoaderParameters);
		
		lblNumInputs = new JLabel("Number of Inputs");
		txtNumInputs = new JTextField(TextFieldWidth);
		AddItem(lblNumInputs, txtNumInputs);
		
		lblNumClasses = new JLabel("Number of Classes");
		txtNumClasses = new JTextField(TextFieldWidth);
		AddItem(lblNumClasses, txtNumClasses);
		
		lblTrainPercentage = new JLabel("Training Percentage");
		txtTrainPercentage = new JTextField(TextFieldWidth);
		AddItem(lblTrainPercentage, txtTrainPercentage);
		
		lblNumberOfRuns = new JLabel("Number of Runs");
		txtNumberOfRuns = new JTextField(TextFieldWidth);
		AddItem(lblNumberOfRuns, txtNumberOfRuns);
		
		lblNumberOfGenerations = new JLabel("Number of Generations");
		txtNumberOfGenerations = new JTextField(TextFieldWidth);
		AddItem(lblNumberOfGenerations, txtNumberOfGenerations);
		
		lblPopulationSize = new JLabel("Population Size");
		txtPopulationSize = new JTextField(TextFieldWidth);
		AddItem(lblPopulationSize, txtPopulationSize);
		
		lblFunctionSet = new JLabel("Functions");
		fslblFunctionSet = new FunctionSetLabel();
		fslblFunctionSet.AddFunction("TestFunction/Location.class");
		fslblFunctionSet.AddFunction("TestFunction/Test2.class");
		fslblFunctionSet.UpdateFunctions();
		AddItem(lblFunctionSet, fslblFunctionSet);
		
		
		
		//-------
		
	}
	
	public void AddItem(JLabel label, JTextField textField){
		cons.gridx = 0;
		cons.gridy = currentY;
		cons.weightx = LeftWeight;
		layout.setConstraints(label, cons);
		add(label);
		
		cons.gridx = 1;
		textField.setColumns(TextFieldWidth);
		cons.weightx = RightWeight;
		layout.setConstraints(textField, cons);
		add(textField);
		currentY++;
	}
	
	public void AddItem(JLabel label, Component comp){
		cons.gridx = 0;
		cons.gridy = currentY;
		cons.weightx = LeftWeight;
		layout.setConstraints(label, cons);
		add(label);
		
		cons.gridx = 1;
		cons.weightx = RightWeight;
		layout.setConstraints(comp, cons);
		add(comp);
		currentY++;
	}
	
	public void AddItem(JLabel label, JTextField textField, JButton button){
		cons.gridx = 0;
		cons.gridy = currentY;
		cons.weightx = LeftWeight;
		layout.setConstraints(label, cons);
		add(label);
		
		cons.gridx = 1;
		textField.setColumns(TextFieldWidth);
		cons.weightx = RightWeight;
		cons.insets = new Insets(10, 5, 1, 5);
		layout.setConstraints(textField, cons);
		add(textField);
		currentY++;
		
		cons.insets = new Insets(1, 5, 10, 5);
		cons.gridx = 1;
		cons.gridy = currentY;
		cons.weightx = RightWeight;
		layout.setConstraints(button, cons);
		add(button);
		cons.insets = new Insets(10, 5, 10, 5);
		currentY++;
		
	}
	
	
	
	public void UpdateModel(){
			
	}
	
	public void UpdateView(){
	
	}
	
	
}
