import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifierSetPanel extends JPanel {

	private static final long serialVersionUID = -6140559806394687680L;

	private ArrayList<String> classes = null;
	private ArrayList<Integer> weights = null;
	private String className = "";
	private GridBagLayout layout;
	private GridBagConstraints cons;
	
	public ModifierSetPanel(String classname){
		init(classname);
	}

	private void init(String classname){
		this.className = classname;
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		
		this.setLayout(layout);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(1, 1, 1, 1);
		
		classes = new ArrayList<String>();
		weights = new ArrayList<Integer>();
		Redraw();		
	}
	
	public void Redraw() {
		//clear the panel.
		this.removeAll();
		int ItemGridY = 0;	
		//For each item in the arraylist,
		for( int i = 0; i < classes.size(); ++i ) {
			//make a new textfield, and removable button
			String s = classes.get(i);
			int w = weights.get(i);
			cons.gridy = ItemGridY;
			cons.gridx = 0;
			JTextField text = new JTextField();
			text.setColumns(15);
			text.setText(s);
			text.setEditable(false);
			layout.setConstraints(text, cons);
			add(text);

			JTextField weight = new JTextField();
			weight.setColumns(3);
			weight.setText("" + w);
			weight.setEditable(true);
			cons.gridx = 1;
			layout.setConstraints(weight, cons);
			add(weight);
			
			JButton button = new JButton("X");
			cons.gridx = 2;
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
	
	public void AddClass(String location, int weight){
		classes.add(location);
		weights.add(weight);
	}

	public void Clear(){
		classes = new ArrayList<String>();
		Redraw();
	}
	
}

