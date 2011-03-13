import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FunctionSetLabel extends JPanel {

	private static final long serialVersionUID = -6140559806394687680L;

	private ArrayList<String> functionClasses = null;
	
	GridBagLayout layout;
	GridBagConstraints cons;
	
	public FunctionSetLabel(){
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		
		this.setLayout(layout);
		
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 100;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(1, 1, 1, 1);
		
		functionClasses = new ArrayList<String>();
	}
	
	public void UpdateFunctions() {
		//clear the panel.
		this.removeAll();
		int ItemGridY = 0;	
		//For each item in the arraylist,
		for( String s : functionClasses ) {
			//make a new textfield, and removable button
			cons.gridy = ItemGridY;
			cons.gridx = 0;
			cons.weightx = 100;
			JTextField text = new JTextField();
			text.setText(s);
			text.setEditable(false);
			layout.setConstraints(text, cons);
			add(text);
			JButton button = new JButton("Remove");
			cons.gridx = 1;
			cons.weightx = 20;
			layout.setConstraints(button, cons);
			add(button);
			ItemGridY++;
		}
		
		cons.gridy = ItemGridY;
		cons.gridx = 0;
		cons.weightx = 100;
		JButton addButton = new JButton("Add Function");
		layout.setConstraints(addButton, cons);
		add(addButton);
	}
	
	public void AddFunction(String location){
		functionClasses.add(location);
	}

	public ArrayList<String> getFunctionClasses(){
		return functionClasses;
	}
	
}
