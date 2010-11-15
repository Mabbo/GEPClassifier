package HandwritingGEPClassification;

import functionsets.ExtendedSafeMath;
import modifiers.ModificationSet;
import GEPClassify.BasicGEPConfig;
import GEPClassify.BasicGEPEvolver;
import GEPClassify.GEPConfig;
import GEPClassify.GEPEvolver;
import IrisGEPClassification.IrisModificationSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Create Training Set
		ReadHandwritingDataSet rhwds = new ReadHandwritingDataSet();
		rhwds.LoadDataSet();
		
		//Create Config
		GEPConfig conf = new BasicGEPConfig();
		conf.setFunctionSet(new ExtendedSafeMath());
		conf.setNumNodes(25);
		conf.setNumCells(26);
		conf.setHeadLength(5);
		conf.setMaxGenerations(1000000);
		conf.setPopulationSize(200);
		ModificationSet modset = new HandWritingModificationSet();
		modset.LoadModifiers();
		conf.setModificationSet( modset );
		System.out.println(conf);
		
		System.out.println("");
	

		GEPEvolver evolver = new BasicGEPEvolver();
		evolver.setGEPConfig(conf);
		evolver.setTrainingSet(rhwds.GetTrainingSet());
		evolver.setTestSet(rhwds.GetTestingSet());
		//Run Evolver
		
		//Adding my best found ever, so that I dont have to wait another hour to get
		// to this point. Use with
		//conf.setNumNodes(15);
		//conf.setNumCells(26);
		//conf.setHeadLength(5);
		//evolver.AddToInitialPopulation("mPfgncfhbcahiehipdnojeika+*fpogkbPDP*fahmdecSSknChfpjdnSlakhmnmkdcPmpjpdmkbikbpi+eojfbojCiDfjcfnelnjhgdoliobpbgh*c*hanpnpphiDDfpdmcolh*C*hapdpjkim*dojhbihfDahjenmehoaPbSbllfnjea*SnbjieiammS-hbmeofbfPmcbcgiiklcPocalllmnocjDSmbgbkdoegh++aofabmkblnPSlhgjfndefiiimgfbgbdjghncbomaPm-aPjodgcnolDimbdkcmn*agnabeabfdD-SbSkgefommDa+cabbkchj*cjgkajngfmehDhehkglllmlndegchmaojbbldijondb-kl*cmoeebPkf+aakgjgdn-hcagniobcflPk-fjfmed+gojfjaecdklnPn*ceokgdch+ePaljdga");

		//conf.setNumNodes(25);
		//conf.setNumCells(26);
		//conf.setHeadLength(7);
		//evolver.AddToInitialPopulation("g*jnlhfedljciaffkhDp-iingbgkjna++hkebblmajmcaomdP*DngfdpknmhnPfCf*mfojeijgefhSpfljnmjmodknmcgecjibbpjaalk+mdjnijogglblng-flpfoPlieknmoeoClbgcjjbmnkmaao+cfoejldjlbdlncmPjDfikdjnilmgkac-aCbibhnhdnoakDccigockgbjaobgalkn*podlgkki-*j+edblejacibjicklhlPifbdkgpfaeff*SCgkiponampkdmCpcdmdgofabChhf+hSnlchebodDjPeaagdfnabdllPf-P*hSdabnjhmpDdDoCnekphcgooppSomahnnmckcdpl+gip*e+pfnjociabvuuPChutaofpqknd+ymgmxssgcjcwvnkerb-rjugnsyundqsPcukiibuccrD-wiqixngdoxkqujDsbayedwrlcdcjdbmrbCglorsirnybc+fyCpvclxlomvrikxqfhynhcrktdurobPu+csboyxaycfhdgulnxtvcnnfxhhcfCSjldvwgjdsiioxPhefkuhrfthqybmnCfmellyxst+mudCgrrxduspxCmSvmjgdxarxnngnlCsyovqihqnbpwtcxkfahaaqmbrkonCltvCuwjqcopdve*Dw*tCfsbgbtqr*SytygDeorhdccsmscaCSjqdrlxefwxrPsynibyppuualngkcoDiinjdnrwveukjhPluinpoxak-koig-ftspnohuv");
		
		//evolver.AddToInitialPopulation("jfmiibjjfed-bep-jfankeog+o+jbhbgc-jCjpndbbollea+mgihnhcmljnaoednlianSkfkodaobcC-Scknehgn+kPhDdbjfibacojcnpcpchDCoPjlmhjcb+m*-mmooiil+DkghhgchllhnklhbamhfbnCgi-lgbdpo+gSpaajmhcciSnnjaapbijmDhPofiohib-kbbcflbljo-o-kodihiajicC*jjfclcepomgoeaipkkllk-ikknhmafkimDfacnblkPo+*kffmhpxvSkhkbqtxot-ivugojqate*+orryhaxjflbarngtpmnSjnagrmyiloabSq*yfatgrxqCfcvjdebbnx*PDnsxtoxD-gmtebivcnhyy-DvydvqmnSC+ptrndpxk-jxcorfuiiD+os*lrpqaalSxnmtmyctndc-rxxqrtphpSwjjugdaduexeeiqdperxvtiDgltjefhvfstCasepdpmpbusysgwitiPwxtwhpgxg-+o-iyuornwgfqj-xcwhtpbtgDiycgryfoskmrblhsopqfaaauuixum");
		
		evolver.RunGeneticAlgorithm();	
		
		//Try classifier out on test data
		
		
		
	}
}
	
