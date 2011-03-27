import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import framework.*;

public class ConfigPanel extends JPanel {
	private static final long serialVersionUID = -8900449870756436728L;
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
	
	private JLabel lblFunctionSet;
	private ClassSetPanel<Function> fslblFunctionSet;
	
	private JLabel lblNumberRNC;
	private JTextField txtNumberRNC;	
	
	private JLabel lblNumLayers;
	private JTextField txtNumLayers;
	
	private JLabel lblLayers;
	private LayerControlPanel lcpanel;
	
	//--------------------------------------//
	
	private JLabel lblFitnessProcess;
	private JTextField txtFitnessProcess;
	private JButton butFitnessProcess;
	
	private JLabel lblKeepPercentage;
	private JTextField txtKeepPercentage;
	
	private JLabel lblSelectionMethod;
	private JTextField txtSelectionProcess;
	private JButton butSelectionProcess;
	
	private JLabel lblMutators;
	private ClassSetPanel<Mutator> cspMutators;
	
	private JLabel lblCrossovers;
	private ClassSetPanel<Crossover> cspCrossovers;
	
	private JLabel lblMutationRate;
	private JTextField txtMutationRate;

	//--------------------------------------//

	private JLabel lblStartProcess;
	private ESPLoaderPanel esplpStartProcess;
	
	private JLabel lblEndProcess;
	private ESPLoaderPanel esplpEndProcess;
	
	private JLabel lblBeforeRunProcess;
	private ESPLoaderPanel esplpBeforeRunProcess;
	
	private JLabel lblEndOfRunProcess;
	private ESPLoaderPanel esplpEndOfRunProcess;
	
	private JLabel lblBeforeGenerationProcess;
	private ESPLoaderPanel esplpBeforeGenerationProcess;
	
	private JLabel lblEndOfGenerationProcess;
	private ESPLoaderPanel esplpEndOfGenerationProcess;
	
	//--------------------------------------//
	
	//--------------------------------------//
	
	
	private ConfigModel config = null;
	
	public ConfigPanel(ConfigModel config){
		super();
		this.config = config;
		LoadVisualItems();
		UpdateView();
	}
	
	private final double LeftWeight = 0.4;
	private final double RightWeight = 0.6;
	private final int WeightY = 1;
	private final int TextFieldWidth = 15;
	private int currentY = 0;
	
	GridBagLayout layout = null;
	GridBagConstraints cons = null;
	
	public void setConfig(ConfigModel conf){
		config = conf;
		UpdateView();
	}
	
	public void LoadVisualItems(){
	
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		setLayout(layout);
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = WeightY;
		cons.anchor = GridBagConstraints.NORTHWEST;
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
	
		lblDataSetLoaderParameters = new JLabel("Parameters");
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
		fslblFunctionSet = new ClassSetPanel<framework.Function>("Function");
		fslblFunctionSet.Redraw();
		AddItem(lblFunctionSet, fslblFunctionSet);

		lblNodeHeadSize = new JLabel("Head Size");
		txtNodeHeadSize = new JTextField(TextFieldWidth);
		AddItem(lblNodeHeadSize, txtNodeHeadSize);
		
		lblNumberRNC = new JLabel("Number of RNCs");
		txtNumberRNC = new JTextField(TextFieldWidth);
		AddItem(lblNumberRNC, txtNumberRNC);
		
		lblNumLayers = new JLabel("Number of Layers");
		txtNumLayers = new JTextField(TextFieldWidth);
		AddItem(lblNumLayers, txtNumLayers);
		
		lblLayers = new JLabel("Layers");
		lcpanel = new LayerControlPanel();
		AddItem(lblLayers, lcpanel);
		
		lblFitnessProcess = new JLabel("Fitness Process");
		txtFitnessProcess = new JTextField(TextFieldWidth);
		butFitnessProcess = new JButton("Load Fitness Process");
		AddItem(lblFitnessProcess, txtFitnessProcess, butFitnessProcess);
		
		lblKeepPercentage = new JLabel("Keep Percentage");
		txtKeepPercentage = new JTextField(TextFieldWidth);
		AddItem(lblKeepPercentage, txtKeepPercentage);
		
		lblSelectionMethod = new JLabel("Selection Process");
		txtSelectionProcess = new JTextField(TextFieldWidth);
		butSelectionProcess = new JButton("Load Selection Process");
		AddItem(lblSelectionMethod, txtSelectionProcess, butSelectionProcess);
	
		lblMutators = new JLabel("Mutators");
		cspMutators = new ClassSetPanel<Mutator>("Mutator");
		AddItem(lblMutators, cspMutators);
		
		lblMutationRate = new JLabel("Mutation Rate");
		txtMutationRate = new JTextField(TextFieldWidth);
		AddItem(lblMutationRate, txtMutationRate);
		
		lblCrossovers = new JLabel("Crossovers");
		cspCrossovers = new ClassSetPanel<Crossover>("Crossover");
		AddItem(lblCrossovers, cspCrossovers);
	
		lblStartProcess = new JLabel("Start Processes");
		esplpStartProcess = new ESPLoaderPanel();
		AddItem(lblStartProcess, esplpStartProcess);
	
		lblEndProcess = new JLabel("End Processes");
		esplpEndProcess = new ESPLoaderPanel();
		AddItem(lblEndProcess, esplpEndProcess);
		
		lblBeforeRunProcess = new JLabel("Start of Run Processes");
		esplpBeforeRunProcess = new ESPLoaderPanel();
		AddItem(lblBeforeRunProcess, esplpBeforeRunProcess);
		
		lblEndOfRunProcess = new JLabel("End of Run Processes");
		esplpEndOfRunProcess = new ESPLoaderPanel();
		AddItem(lblEndOfRunProcess, esplpEndOfRunProcess);
		
		lblBeforeGenerationProcess = new JLabel("Start of Generation Processes");
		esplpBeforeGenerationProcess = new ESPLoaderPanel();
		AddItem(lblBeforeGenerationProcess, esplpBeforeGenerationProcess);
		
		lblEndOfGenerationProcess = new JLabel("End of Generation Processes");
		esplpEndOfGenerationProcess = new ESPLoaderPanel();
		AddItem(lblEndOfGenerationProcess, esplpEndOfGenerationProcess);		
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
		textField.setEditable(false);
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
		txtTitle.setText(config.getTitle());
		txtDataSet.setText(config.getDataSetFile());
		txtDataSetLoader.setText(config.getDataSetLoaderLocation() + config.getDataSetLoaderFilename());
		txtDataSetLoaderParameters.setText(config.getDataSetLoaderParameterString());
		
		txtNumInputs.setText(config.getNumberOfInputs()+"");
		txtNumClasses.setText(config.getNumberOfClasses() + "");
		txtTrainPercentage.setText(config.getTrainingPercentage()+"");
		txtNumberOfRuns.setText(config.getNumberOfRuns()+"");
		txtNumberOfGenerations.setText(config.getNumberOfGenerations()+"");
		txtPopulationSize.setText(config.getPopulationSize()+"");
		
		txtNodeHeadSize.setText(config.getNodeHeadSize()+"");
		fslblFunctionSet.Clear();
		for(int i = 0; i < config.getFunctionLocations().size();++i){
			fslblFunctionSet.AddClass(config.getFunctionLocations().get(i) + config.getFunctionFiles().get(i));			
		}
		fslblFunctionSet.Redraw();
		
		txtNumberRNC.setText(""+ config.getNumberOfRuns());
		txtNumLayers.setText(""+ config.getNumberLayers());
		lcpanel.SetNumberOfLayers(0);
		ArrayList<Integer> alist = new ArrayList<Integer>();
		for(int i = 0; i < config.getNumberLayers();++i){
			alist.add( config.getNodesInLayer()[i] );
		}
		lcpanel.SetLayers(alist);
		
		txtFitnessProcess.setText(config.getFitnessProcessFile());
		
		txtKeepPercentage.setText("" + config.getKeepPercentage() );
		
		txtSelectionProcess.setText(config.getSelectionMethodFile());
		
		//load the mutators
		
		for(int i = 0; i < config.getMutatorFiles().size();++i){
			cspMutators.AddClass(config.getMutatorFiles().get(i));			
		}
		cspMutators.Redraw();
		
		txtMutationRate.setText("" + config.getMutationRate());
		
		for( int i = 0; i < config.getStartProcessFiles().size(); ++i){
			esplpStartProcess.AddProcess(config.getStartProcessFiles().get(i),
										 config.getStartProcessParameter().get(i));
		}
		esplpStartProcess.Redraw();
		
		for( int i = 0; i < config.getEndProcessFiles().size(); ++i){
			esplpEndProcess.AddProcess(config.getEndProcessFiles().get(i),
										 config.getEndProcessParameter().get(i));
		}
		esplpEndProcess.Redraw();
		
		for( int i = 0; i < config.getBeforeRunProcessFiles().size(); ++i){
			esplpBeforeRunProcess.AddProcess(config.getBeforeRunProcessFiles().get(i),
										 config.getBeforeRunProcessParameter().get(i));
		}
		esplpBeforeRunProcess.Redraw();
		
		for( int i = 0; i < config.getBeforeGenerationProcessFiles().size(); ++i){
			esplpBeforeGenerationProcess.AddProcess(config.getBeforeGenerationProcessFiles().get(i),
										 config.getBeforeGenerationProcessParameter().get(i));
		}
		esplpBeforeGenerationProcess.Redraw();
		
		for( int i = 0; i < config.getEndOfGenerationProcessFiles().size(); ++i){
			esplpEndOfGenerationProcess.AddProcess(config.getEndOfGenerationProcessFiles().get(i),
										 config.getEndOfGenerationProcessParameter().get(i));
		}
		esplpEndOfGenerationProcess.Redraw();
		
		for( int i = 0; i < config.getEndOfRunProcessFiles().size(); ++i){
			esplpEndOfRunProcess.AddProcess(config.getEndOfRunProcessFiles().get(i),
										 config.getEndOfRunProcessParameter().get(i));
		}
		esplpEndOfRunProcess.Redraw();
		
		this.revalidate();
	}
	
}
