import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClassSetPanel<T> extends JPanel {

	private static final long serialVersionUID = -6140559806394687680L;

	private ArrayList<String> classes = null;
	String className = "";
	GridBagLayout layout;
	GridBagConstraints cons;
	
	public ClassSetPanel(String className){
		this.className = className; 
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		
		this.setLayout(layout);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.gridheight = 1;
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
			JTextField text = new JTextField();
			text.setColumns(25);
			text.setText(s);
			text.setEditable(false);
			layout.setConstraints(text, cons);
			add(text);
			
			JButton button = new JButton("X");
			cons.gridx = 1;
			layout.setConstraints(button, cons);
			add(button);
			ItemGridY++;
		}
		
		cons.gridy = ItemGridY;
		cons.gridx = 0;
		JButton addButton = new JButton("Add " + className);
		layout.setConstraints(addButton, cons);
		add(addButton);
	}
	
	public void AddClass(String location){
		classes.add(location);
	}

	public ArrayList<String> getClassNames(){
		return classes;
	}
	
	public void Clear(){
		classes = new ArrayList<String>();
		Redraw();
	}
	
}
