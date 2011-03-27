
public class GUIMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GEPInterface gepi = null;
		if( args.length > 0){
			gepi = new GEPInterface(args[0]);
		} else {
			gepi = new GEPInterface();
		}
		gepi.setVisible(true);
		
		
	}

}
