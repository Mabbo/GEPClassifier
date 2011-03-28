import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

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
import framework.Function;
import framework.Mutator;


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
	private LaunchPanel launchPanel;
	private GridBagLayout layout = null;
	private GridBagConstraints cons = null;
	
	private final int Width = 1000;
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
		InitializeLaunchPanel();
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
		cons.weightx = 0.6;
		cons.weighty = 0.9;
		cons.fill = GridBagConstraints.BOTH;
		configPanel = new ConfigPanel(this, config);
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
		cons.weighty = 0.9;
		cons.fill = GridBagConstraints.BOTH;
		outputPanel = new OutputPanel();
		layout.setConstraints(outputPanel, cons);
		add(outputPanel);	
	}
	
	private void InitializeLaunchPanel() {
		cons.gridheight = 1;
		cons.weightx = 1;
		cons.weighty = 0.1;
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 2;
		cons.fill = GridBagConstraints.BOTH;
		launchPanel = new LaunchPanel();
		layout.setConstraints(launchPanel, cons);
		add(launchPanel);
		
		launchPanel.addLaunchActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Launch();
			}
		});
		
		launchPanel.addStopActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stop(); //Hammer time
			}
		});
		
		
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
		config.SaveConfig();
	}
	
	public void Quit(){
		System.exit(0);
	}
	
	private EvolverThread et = null; 
	
	public void Launch() {
		if( configPanel.getErrorsExist() ){
			JOptionPane.showMessageDialog(this, "Errors exist in the configuration.");
			return;
		}
		
		SaveConfigFile();

		launchPanel.setLaunched();
		et = new EvolverThread(config.getConfigFileName(), outputPanel.getWriter());
		Thread thread = new Thread(et);
		thread.start();
		
	}
	
	public void Stop() {
		if(et!=null)
			et.Kill();
		launchPanel.setStopped();
	}
	
	public class EvolverThread implements Runnable{
		
		Evolver ev = null;
		String conf = null;
		PrintWriter out = null;
		
		public EvolverThread(String confloc, PrintWriter out){
			ev = new Evolver();
			this.conf = confloc;
			this.out = out;
		}
		
		public EvolverThread(String conf){
			ev = new Evolver();
			this.conf = conf;
		}
		
		public void run() {
			if( out != null ){
				ev.Evolve(conf, out);
			} else {
				ev.Evolve(conf);
			}
		}
		
		public void Kill(){
			ev.Kill();
			if( out!=null){
				out.println("Ended Evolver");
			}
		}
		
		
	}
	
	
	
}









