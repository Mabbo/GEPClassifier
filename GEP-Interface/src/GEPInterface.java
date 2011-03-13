import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
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
		InitializeMainPanel();
		
	}
	
	//---------visual stuff-----------------//
	private JScrollPane scrollPane;
	
	
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
		
		
	}
	
	public void LoadConfigFile(){
		
		
	}
	
	public void SaveConfigFile() {
		
	}
	
	
	
}









