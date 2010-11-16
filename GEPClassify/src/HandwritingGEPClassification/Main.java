package HandwritingGEPClassification;

import functionsets.ExtendedSafeMath;
import modifiers.ModificationSet;
import GEPClassify.*;

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
		conf.setPopulationSize(100);
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
		
		//32424
		//evolver.AddToInitialPopulation("jjDCdagbbjf-be*SnjdamhSgP+glebllk-jCj-dlhjoclpnCPoegclofDPgcpikdijlPlipmnljkjcDDdjjkkjei+kPhagaclnhlC-fhochcbhDCoPjefgpfe+m*-Pmolpnj+DkghkdhidphbCSCccmeimnA-oplhfcih+gS+DpbjkhniDbf*cfebpbmedADdhlicfhd*cmmdenfe-o-dolegnefmhDSfeeaicnp-SilfjejjeDf+hSoamookfCegCamflchk+a*Aclhhegx-ehPomyomutpPnreoxoomeSjkebbganqrrv+*rsthnrSgCAAgxfcrbaowjxsxjtfkusvq+fepiacn**oAnouvwgDmhpwjpnhxsh+mofhmvjcjwCh-vempbom**D+knambroD+oq*lrofbilgcD+ntolltd+SwAcefhhbpDadSavednaSvC+gldioiiv-Pc+adbouepb*ySpuqkngmi+sswaxlkpiAtfklsmrjq-+q-myxqckfCiCiiqfungabpo+blkkitioqwg+omtyubqoylwgdgwcf");
		//32753
		//evolver.AddToInitialPopulation("Ap+Akmancme*o-kpieehbm+cmgDehanhkolAedkghikepg*PCkiclpo-fmDofdfiniloP--ciclde*SAicpohopddlhSDkgjpneAjg-Pcdkncnkm**ahdjjig-**dnpnfbln-C+hdopbcfk+nbSpldjhak+DlpoojeikjD+jibnfjbhjfADC+dncchahjnDfighgghCmAPilfponb*iAP-hmonniglCPCdggengp*hpAfhghbj*mmCAlkblpefll+Anfihbo+PioShbolnlqjAcpuepxrk+Seovmpjviwg+kjSlwlolxPnCfAucoqophdsPPditehqjkcuslvsagmfDPbSimiercrincAodpfxe*S+safmujvoiAciAqtctvb+-ehqryoyai*DpgPugxnpfAw-mdleftndqAtq*bddiqfCg*Akohmysy-a+jClcmbdtsP+DvwfcxrxvPDA+gduitwdsaSStyptfwSjApSknjkkmtvAlAqnuwtg+aCAPufqxyqrSgnehhmmmaafPjrsopsinbsujncigoncyxyi+ahydts");
		//34562
		//evolver.AddToInitialPopulation("Ap+Akmancke*o-kpieehbm+DD+cehanikolAdAkgoikepgfPCkijlpl-fmjhfdfinilon--ciclde*SA-mlohogddAhogkggnneAjgPPcdkncnkC*akgljjigndd*-pnfbln-C+hddpbckk+pSbnlijhak+DlpoojiikjD+jijnfjbhj+fADCdnnchahjnDfiongghCmhPilfponb*iAP-hmohniglCPCjggedgp*hDAffghoj*mmdAlkblpe+Afflnfihbo+PioAhbolnfqjAc+uesxjk+Seovmpjvnwg+kASlwgolfPnCfyucoqhpPhx*PditehqjmPAtqvsagmfDPPSiliercrincAodpfxs*S+safmujvoi*ciAqtcttb+-ehfryoyai*DpgPkgxnpfAw-mslrfdndq*w*qbddbwfAkCgtormysy-a+eClclbdasP+DvwfcxqxvPDA+gdjiqadqaCSbypdfwlPApSksjkkmtqflmqnuwts+aCAPufqxcqSSgnChhhmdnafPjrsmmginbAdjncygofcyPtlwgdgccf");
		//40800
		//evolver.AddToInitialPopulation("Ap+Akmalagj*o-kpbgehamlnDDhikgnpooe+ohlkohcmpD+*ajeidcb-fmPmhjigoil+eDldlkmip*SA-mlohhadSCnnjoehiadAAjgbijcoeoDkCSSmbfgfgnffikihlkff-S+ldjkodlloDPfnalahnh+DlpogcipmpD+jijdmfbhf+f+DCmknncbhDf+*iomojfCmhDneilbpj*iAP-hmokmbghh*Agmiljop+Cjghjfgbl*mmCpiioaaj+f-fhgkgbop+-ioAhkgfinq*+d-xkisrq+SeoPukitewgaDuPulvadog-SjPvrrcqkCrnxSbdtjgyj+eACqtuxgfpSSParloysprryhywwktxu*S+skfsrksp*PS*ioyycqm+-ehfdwogvu*DpgPkxupoiAwmSCgcxosxp-CCDttjbbcA-rAAhgmsfj-C-DCjcddgnrDDm-vsdbepv+ASApwhdnodCuamvrncjhlywgfkkjvyft*r+Dbinqlp+aCAPufnjyarCahcwcrymdauDCPjckqvebjv*moqrmcvyPrvAuwejae");
		//41232
		evolver.AddToInitialPopulation("Ap+Akmalmga*o-kpagphaal-gDhckgnjpooh+odkfbcmpDD*Djeidce-fmPAodkgoil+oj*dlgaip*SA-mlohnmdCnjSSgenkahAAjgbijnolnDkCSSmiillgn+jnfihlgfn-S+ldjkeolffP-nnllkpbc+DlpogcplmpD+jijicfbof+f+DCakfnibhDf+*ipkijaCmhDPbiebpj*iAP-hmoikkgeo*AealljppkaAbhoiibj*mmcliioeap+--PAgkgbpp+-ioAhkgdinq*+t-xkisgu+SeoDukgoeggaDuPugvadagPjS-ehocqkxSCpnvdtjhlj+ArmqajdgfSAPPSwdocplrSSPyltjjxu*S+skfsokss*PS*ioyqcsh+-ehfrwwupi*DpgPkyueviAwmnCloxssxsDCCsttjbbcA-rAAhgmsfj-C-DCjcddqorDDp-vsdheyvbuyApckdnodCuAmhrncchlCv-frkjrmotm-+Dbmntlp+aCAPufujvaCahc+kfruddaet*swcwngdbjvbwjqrblvyP+vPupghqe");
		//41664
		//Ap+Akmalmgn*o-kpagphfal-gDhckgnjpo+heodkfbcmpDD*Djeidce-fmPAjdkgoil*ojeelgaip*SA-mlohnmdSCnjSgenkahAAjgbiknolfDkCSSmiillgn+jnfihlgfn-S+ldjkkdlmfP-nnllkpbc+DlpogcplmpD+jijicfbof+f+DCakfnibhDf+*ipkijaCmhfPbiebpj*iAP-hmoikkg-o*pealljppgSe-plmnmb*mmcliiojap+--PAgkgbpp+-ioAhkgdinq*+t-xkisgu+Seo-ukgocmgaDuPugvadagPjS-euccqexSCpsvdtjhlj+ASmqajdgeSPPASwdocplrSSDyltjjxu*S+skfsokss*PS*iofqcsh+-ehfrwwupi*DpgPkyceviAwmnCiixssxsDCCstdqbbcA-rAAhgmsfj-C-DCjcddqorDDp-vsdheyvbu-llcwdnadCuAmhrncchlCv-frkjpmntm-+Dbmntlp+aCAPufujvaCahc+kfjuddaet*swcwngdbjvywjqrnlsyP+pPupgbqe


		//in the morning, if we havent had amazing success, try:
		// -set head length huge, number of nodes smaller
		// -forces more use of complex functions
		evolver.RunGeneticAlgorithm();
		
		//Try classifier out on test data
		
		
		
	}
}
	
