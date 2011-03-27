import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;



/**
 * OutputPanel
 * 
 * Responsibilities: Have an output stream that text can be sent to, 
 * display that text in a textarea window, like System.out has. The
 * GEP Evolver will send all messages there instead.
 * 
 * @author mabbo
 *
 */

public class OutputPanel extends JPanel {

	private JTextArea outText = null;
	private JScrollPane scroll = null;
	
	public OutputPanel(){
		
		outText = new JTextArea();
		outText.setAutoscrolls(true);
		outText.setLineWrap(true);
		scroll = new JScrollPane(outText, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
		
		this.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
			public void componentResized(ComponentEvent e) {
				ResetSize();
			}
		});
		
		ResetSize();
	}
	
	public void ResetSize(){
		scroll.revalidate();
		scroll.setSize( getWidth(), getHeight()  );
		scroll.setLocation(0,0);
	}
	
	
	
	
	
}
