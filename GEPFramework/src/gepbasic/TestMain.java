package gepbasic;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GEPConfig conf = new GEPConfig();
		conf.LoadConfig("../gep_sample.config");
	
		System.out.println(conf.getTitle());
		
		System.out.println("Done");
	}

}
