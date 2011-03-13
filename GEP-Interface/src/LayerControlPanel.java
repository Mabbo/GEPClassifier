import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/***
 * allows editing of each layer
 * 
 * |--------------
 * |layer1:  ____
 * |layer2:  ____
 * |etc
 * |--------------
 * 
 * 
 * @author mabbo
 *
 */


public class LayerControlPanel extends JPanel {

	private ArrayList<Integer> layers;
	private GridBagLayout layout;
	private GridBagConstraints cons;
	
	public LayerControlPanel(){
		layers = new ArrayList<Integer>();
		layout = new GridBagLayout();
		cons = new GridBagConstraints();
		
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weighty = 100;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(1, 3, 1, 3);
		this.setLayout(layout);
		Redraw();
	}
	
	public void SetLayers(ArrayList<Integer> layers){
		this.layers = layers;
		Redraw();
	}
	
	public void SetNumberOfLayers(int layercount){
		while( layercount > layers.size() ){
			layers.add(1);
		}
		while( layercount < layers.size() ){
			layers.remove(layers.size()-1);
		}
		Redraw();
	}
	
	public void Redraw(){
		removeAll();
		for( int i = 0; i < layers.size(); ++i) {
			JLabel lblLayer = new JLabel("" + (i+1));
			JTextField txtLayer = new JTextField(4);
			txtLayer.setText(""+ layers.get(i) );
			cons.gridy = i;
			cons.gridx = 0;
			cons.weightx = 100;
			layout.setConstraints(lblLayer, cons);
			add(lblLayer);
			cons.gridx = 1;
			layout.setConstraints(txtLayer, cons);
			add(txtLayer);
		}
	}
	
	
	
}
