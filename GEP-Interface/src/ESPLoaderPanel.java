import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ESPLoaderPanel extends JPanel {

	private ArrayList<String> classes = null;
	GridBagLayout layout;
	GridBagConstraints cons;
	
	public ESPLoaderPanel(){ 
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		
		this.setLayout(layout);
		
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 100;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(1, 1, 1, 1);
		
		classes = new ArrayList<String>();
		Redraw();
	}
	
	public void Redraw() {
		//clear the panel.
		this.removeAll();
		int ItemGridY = 0;	
		//For each item in the arraylist,
		for( String s : classes ) {
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
		
		JButton addButton = new JButton("Add Process");
		layout.setConstraints(addButton, cons);
		add(addButton);
	}
	
	public void AddClass(String location){
		classes.add(location);
	}

	public ArrayList<String> getClassNames(){
		return classes;
	}
	
	
}
