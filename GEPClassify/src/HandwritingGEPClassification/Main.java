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
		conf.setNumNodes(6);
		conf.setNumCells(26);
		conf.setHeadLength(9);
		conf.setMaxGenerations(1000000);
		conf.setPopulationSize(30);
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
		
		
		
		//in the morning, if we havent had amazing success, try:
		// -set head length huge, number of nodes smaller
		// -forces more use of complex functions
		evolver.RunGeneticAlgorithm();
		
		//Try classifier out on test data
		
		
		
	}
}
	
/*
 * 		//32424
		//evolver.AddToInitialPopulation("jjDCdagbbjf-be*SnjdamhSgP+glebllk-jCj-dlhjoclpnCPoegclofDPgcpikdijlPlipmnljkjcDDdjjkkjei+kPhagaclnhlC-fhochcbhDCoPjefgpfe+m*-Pmolpnj+DkghkdhidphbCSCccmeimnA-oplhfcih+gS+DpbjkhniDbf*cfebpbmedADdhlicfhd*cmmdenfe-o-dolegnefmhDSfeeaicnp-SilfjejjeDf+hSoamookfCegCamflchk+a*Aclhhegx-ehPomyomutpPnreoxoomeSjkebbganqrrv+*rsthnrSgCAAgxfcrbaowjxsxjtfkusvq+fepiacn**oAnouvwgDmhpwjpnhxsh+mofhmvjcjwCh-vempbom**D+knambroD+oq*lrofbilgcD+ntolltd+SwAcefhhbpDadSavednaSvC+gldioiiv-Pc+adbouepb*ySpuqkngmi+sswaxlkpiAtfklsmrjq-+q-myxqckfCiCiiqfungabpo+blkkitioqwg+omtyubqoylwgdgwcf");
		//32753
		//evolver.AddToInitialPopulation("Ap+Akmancme*o-kpieehbm+cmgDehanhkolAedkghikepg*PCkiclpo-fmDofdfiniloP--ciclde*SAicpohopddlhSDkgjpneAjg-Pcdkncnkm**ahdjjig-**dnpnfbln-C+hdopbcfk+nbSpldjhak+DlpoojeikjD+jibnfjbhjfADC+dncchahjnDfighgghCmAPilfponb*iAP-hmonniglCPCdggengp*hpAfhghbj*mmCAlkblpefll+Anfihbo+PioShbolnlqjAcpuepxrk+Seovmpjviwg+kjSlwlolxPnCfAucoqophdsPPditehqjkcuslvsagmfDPbSimiercrincAodpfxe*S+safmujvoiAciAqtctvb+-ehqryoyai*DpgPugxnpfAw-mdleftndqAtq*bddiqfCg*Akohmysy-a+jClcmbdtsP+DvwfcxrxvPDA+gduitwdsaSStyptfwSjApSknjkkmtvAlAqnuwtg+aCAPufqxyqrSgnehhmmmaafPjrsopsinbsujncigoncyxyi+ahydts");
		//34562
		//evolver.AddToInitialPopulation("Ap+Akmancke*o-kpieehbm+DD+cehanikolAdAkgoikepgfPCkijlpl-fmjhfdfinilon--ciclde*SA-mlohogddAhogkggnneAjgPPcdkncnkC*akgljjigndd*-pnfbln-C+hddpbckk+pSbnlijhak+DlpoojiikjD+jijnfjbhj+fADCdnnchahjnDfiongghCmhPilfponb*iAP-hmohniglCPCjggedgp*hDAffghoj*mmdAlkblpe+Afflnfihbo+PioAhbolnfqjAc+uesxjk+Seovmpjvnwg+kASlwgolfPnCfyucoqhpPhx*PditehqjmPAtqvsagmfDPPSiliercrincAodpfxs*S+safmujvoi*ciAqtcttb+-ehfryoyai*DpgPkgxnpfAw-mslrfdndq*w*qbddbwfAkCgtormysy-a+eClclbdasP+DvwfcxqxvPDA+gdjiqadqaCSbypdfwlPApSksjkkmtqflmqnuwts+aCAPufqxcqSSgnChhhmdnafPjrsmmginbAdjncygofcyPtlwgdgccf");
		//40800
		//evolver.AddToInitialPopulation("Ap+Akmalagj*o-kpbgehamlnDDhikgnpooe+ohlkohcmpD+*ajeidcb-fmPmhjigoil+eDldlkmip*SA-mlohhadSCnnjoehiadAAjgbijcoeoDkCSSmbfgfgnffikihlkff-S+ldjkodlloDPfnalahnh+DlpogcipmpD+jijdmfbhf+f+DCmknncbhDf+*iomojfCmhDneilbpj*iAP-hmokmbghh*Agmiljop+Cjghjfgbl*mmCpiioaaj+f-fhgkgbop+-ioAhkgfinq*+d-xkisrq+SeoPukitewgaDuPulvadog-SjPvrrcqkCrnxSbdtjgyj+eACqtuxgfpSSParloysprryhywwktxu*S+skfsrksp*PS*ioyycqm+-ehfdwogvu*DpgPkxupoiAwmSCgcxosxp-CCDttjbbcA-rAAhgmsfj-C-DCjcddgnrDDm-vsdbepv+ASApwhdnodCuamvrncjhlywgfkkjvyft*r+Dbinqlp+aCAPufnjyarCahcwcrymdauDCPjckqvebjv*moqrmcvyPrvAuwejae");
		//41232
		//evolver.AddToInitialPopulation("Ap+Akmalmga*o-kpagphaal-gDhckgnjpooh+odkfbcmpDD*Djeidce-fmPAodkgoil+oj*dlgaip*SA-mlohnmdCnjSSgenkahAAjgbijnolnDkCSSmiillgn+jnfihlgfn-S+ldjkeolffP-nnllkpbc+DlpogcplmpD+jijicfbof+f+DCakfnibhDf+*ipkijaCmhDPbiebpj*iAP-hmoikkgeo*AealljppkaAbhoiibj*mmcliioeap+--PAgkgbpp+-ioAhkgdinq*+t-xkisgu+SeoDukgoeggaDuPugvadagPjS-ehocqkxSCpnvdtjhlj+ArmqajdgfSAPPSwdocplrSSPyltjjxu*S+skfsokss*PS*ioyqcsh+-ehfrwwupi*DpgPkyueviAwmnCloxssxsDCCsttjbbcA-rAAhgmsfj-C-DCjcddqorDDp-vsdheyvbuyApckdnodCuAmhrncchlCv-frkjrmotm-+Dbmntlp+aCAPufujvaCahc+kfruddaet*swcwngdbjvbwjqrblvyP+vPupghqe");
		//41664
		//evolver.AddToInitialPopulation("Ap+Akmalmgn*o-kpagphfal-gDhckgnjpo+heodkfbcmpDD*Djeidce-fmPAjdkgoil*ojeelgaip*SA-mlohnmdSCnjSgenkahAAjgbiknolfDkCSSmiillgn+jnfihlgfn-S+ldjkkdlmfP-nnllkpbc+DlpogcplmpD+jijicfbof+f+DCakfnibhDf+*ipkijaCmhfPbiebpj*iAP-hmoikkg-o*pealljppgSe-plmnmb*mmcliiojap+--PAgkgbpp+-ioAhkgdinq*+t-xkisgu+Seo-ukgocmgaDuPugvadagPjS-euccqexSCpsvdtjhlj+ASmqajdgeSPPASwdocplrSSDyltjjxu*S+skfsokss*PS*iofqcsh+-ehfrwwupi*DpgPkyceviAwmnCiixssxsDCCstdqbbcA-rAAhgmsfj-C-DCjcddqorDDp-vsdheyvbu-llcwdnadCuAmhrncchlCv-frkjpmntm-+Dbmntlp+aCAPufujvaCahc+kfjuddaet*swcwngdbjvywjqrnlsyP+pPupgbqe");
		//44200 :
		//evolver.AddToInitialPopulation("Ap+Akmamfig*o-kpliajfpl-PAehkiknmo+dA*kepaelpSDi*ialkha-fmlDkkfecmlSbDPlgimca*SA--lohnhiCnACCofckblAjgbAjimfgoDkCSDllcicinSmPakalejo-C+PcnjbllmhDbSDgacppb+DlponampmiD+jijbppnnp+f+DCagfnhfhlPm+nkklomPCmfPgbkoeo*iAP-hmocofgAp*Cpchleap-DAnnkmlmc*mmAhebincj-DiP-gagamg+-iohhkapjoqmpAbquhmio+SeoCtokosicCAS-ovjjhrA-f-xthgsevPPPCAkjkplrj-gP*pjlvvm-Aivgjngdufn*+Pvnptysc*S+safsoapu*PS*iofvaqe+-ehfjigbvi*DpcPkhfffaAwmCCoqnbtgDdAkSnipkaaA-rAAhgmslh-C-DCjcddqx-gP*otsbadcvAg-ckiapksdP-CChprubylf+PlyjxpjatSAD+tdgeoc+aCAPufosexD+A+wvdkkqnaDprptkvyfqb+cAalepubiyAoyShbtisa");
		//44201 :   
		evolver.AddToInitialPopulation("Ap+Akmamfig*o-kpliajapl-PAnhkiibmo+SA*kepoenpcSh*hjlcha-fm-mkkkecmlSADndjifca*SA--lohfhkSCnACoihkbiAAjgbjimfbeDkCSDlliilknSPmokalajg-C+Pcnnnhkmh++SDgalijn+DlponkmcmiD+jijfpppih+f+DCagfnhfhlPl+nkjlomCmfPPgbdjeo*iAP-hmocofgdp*Cpcpleap-+ASnomkmc*mmASeboncjii--Dgnghfg+-ioAhkaijoqDDA-vuhmio+SeoCtokosigCASgoojjhkA-f-CthtskgA*xPPkjnpxgj-gPSpjldvm-AivgsnmdhfneSPDnprywr*S+safsoapu*PS*iofveqe+-ehfntgbdi*DpcPkhffyyAwmCCnqnbegDwkADnapkaaA-rAAhgmslh-C-DCjcddqx-gP*otjbaxwv+g-icitpksdP--qhpkuvylnkPCyjipmhtSACuswyjob+aCAPufbsjbD+A+wvdkkqnahCoptkvyggblsAilervdiywoySeucius");
		//evolver.AddToInitialPopulation("Ap+Akmamfig*o-kpliajfpl-PAehkiinmo+dA*kepaelpSDi*ialkha-fmlDkkgecmlSbDPlgifca*SA--lohnhiCnACCoickblAjgPAjimfbeDkCSDllcickmPSnmkalcjn-C+PcnjnllmhD+SDgacppb+DlponampmiD+jijbppnig+f+DCagfnhfhlPm+nkklomCmfPdgbdoeo*iAP-hmocofgAp*Cpchleap-DAnnkmpmc*mmAhebincj-PiD-gagamg+-iohhkaijoqmpAbquhmio+SeoCtokosicCASgovjjhrA-f-xthiskvPPPwAkjsplrj-gP*pjlvvm-Aivgjngdufn*hPvnptysc*S+safsoapu*PS*iofvaqe+-ehfjigbvi*DpcPkhfffaAwmCCnqnbtgDDAkSnapkaaA-rAAhgmslh-C-DCjcddqx-gP*otsbadcv+g-ckiapksdP-CChprubyllfkPyjopjhtSAD+swgeoc+aCAPufosebD+A+wvdkkqnaPprptkvysqbkcAylepuviyAoyShuuisa");

 * 
 * 
 * */
