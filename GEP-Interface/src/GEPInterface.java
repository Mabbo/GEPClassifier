import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import base.Config;

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
	
	Config config = null;
	ConfigPanel configPanel;
	
	private final int Width = 600;
	private final int Height = 660;
	
	public GEPInterface(){
		super("GEP Interface");
		config = new Config();
		InitializeMenu();
		InitializeMainPanel();
		this.setLocation(200, 100);
	}
	
	//---------visual stuff-----------------//
	private JScrollPane scrollPane;
	private JMenuBar menubar;
	private JMenu filemenu;
	private JMenuItem quit;
	private JMenuItem open;
	private JMenuItem save;
	
	public void InitializeMainPanel(){
		
		configPanel = new ConfigPanel(config);
		scrollPane = new JScrollPane(configPanel,
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		setSize(Width,Height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			try{
				String filename = chooser.getSelectedFile().getAbsolutePath();
				config = new Config();
				config.LoadConfigurationFile(filename);
				configPanel.setConfig(config);
				this.repaint();
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Error loading file: " + ex.toString());
			}
		}
	}
	
	public void SaveConfigFile() {
		
	}
	
	public void Quit(){
		System.exit(0);
	}
	
	
}









