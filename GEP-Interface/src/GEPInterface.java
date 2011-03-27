import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;

import evolver.Evolver;


/***
 * 
 * Requirements:
 * 
 * For each parameter of the GEP algorithm (and each piece of the XML file)
 * -Must display the current value
 * -Must allow editing of the value
 * -Must allow loading and saving of config files
 * -Must allow adding of more values for parameters that allow it (functions, etc)
 * -Must allow launching of GEP Algorithm
 * 
 * @author mabbo
 *
 */


public class GEPInterface extends JFrame {
	private static final long serialVersionUID = 3544476936851462325L;
	
	private ConfigModel config = null;
	private ConfigPanel configPanel;
	private OutputPanel outputPanel;
	private GridBagLayout layout = null;
	private GridBagConstraints cons = null;
	
	private boolean EvolverIsRunning = false;
	
	private final int Width = 900;
	private final int Height = 660;
	
	public GEPInterface(){
		super("GEP Interface");
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		setLayout(layout);
		InitializeMenu();
		InitializeMainPanel();
		InitializeOutputPanel();
		this.setLocation(200, 100);
		setSize(Width,Height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public GEPInterface(String file){
		super("GEP Interface");
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		setLayout(layout);
		
		LoadFile(file);
		
		this.setLocation(200, 100);
		setSize(Width,Height);
		InitializeMenu();
		InitializeMainPanel();
		InitializeOutputPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//---------visual stuff-----------------//
	private JScrollPane scrollPane;
	private JMenuBar menubar;
	private JMenu filemenu;
	private JMenuItem quit;
	private JMenuItem open;
	private JMenuItem save;
	
	public void InitializeMainPanel(){
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 0.4;
		cons.weighty = 1;
		cons.fill = GridBagConstraints.BOTH;
		configPanel = new ConfigPanel(config);
		configPanel.setLaunchAction(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Launch();
			}
		});
		scrollPane = new JScrollPane(configPanel,
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		layout.setConstraints(scrollPane, cons);
		add(scrollPane);
		
	}
	
	public void InitializeOutputPanel(){
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 0.4;
		cons.weighty = 1;
		cons.fill = GridBagConstraints.BOTH;
		outputPanel = new OutputPanel();
		layout.setConstraints(outputPanel, cons);
		add(outputPanel);	
	}
	
	public void InitializeMenu(){
		menubar = new JMenuBar();
		filemenu = new JMenu("File");
		quit = new JMenuItem("Quit");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		filemenu.add(open);
		filemenu.add(save);
		filemenu.add(new JSeparator());
		filemenu.add(quit);
		menubar.add(filemenu);
		setJMenuBar(menubar);
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Quit();
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveConfigFile();
			}
		});
		
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadConfigFile();
			}
		});	
		
	}
	
	public void LoadConfigFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new ConfigFilter());
		int retval = chooser.showDialog(this, "Open");
		
		//Load the config file
		if( retval == JFileChooser.APPROVE_OPTION ){
			LoadFile(chooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	private void LoadFile(String filename){
		try{
			config = ConfigModel.OpenConfig(filename);
			if( configPanel != null) {
				configPanel.setConfig(config);
				this.repaint();
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, "Error loading file: " + ex.toString());
		}
	}
	
	public void SaveConfigFile() {
		
	}
	
	public void Quit(){
		System.exit(0);
	}
	
	public void Launch() {
		EvolverIsRunning = true;
		configPanel.setLaunchButtonEnabled(false);
		SaveConfigFile();
		Evolver evolver = new Evolver();
		evolver.EvolveClassifier(config.getConfigFileName());
	}
	
}









