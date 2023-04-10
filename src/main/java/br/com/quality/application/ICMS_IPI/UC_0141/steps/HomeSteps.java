package br.com.quality.application.ICMS_IPI.UC_0141.steps;

import br.com.quality.general.support.GeneralUtils;
import br.com.quality.application.ICMS_IPI.UC_0141.interactions.HomePageInteractions;
import br.com.quality.application.ICMS_IPI.geral.interactions.HomePageInteractionsGeneral;
import br.com.quality.application.ICMS_IPI.geral.tests.RunBase;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;

public class HomeSteps extends RunBase {

    GeneralUtils generalUtils = new GeneralUtils();
    HomePageInteractionsGeneral homePageInteractionsGeneral = new HomePageInteractionsGeneral();
    HomePageInteractions homePageInteractions = new HomePageInteractions();
    private static String homeStepName;

    @Dado("que estou na página na home")
    public void imOnTheHomePage() throws Exception {
        homeStepName = "E valido se estou na página home";
        homePageInteractions.validateHomePage();
    }
    @Quando("acesso o menu do produto ICMS_IPI")
    public void accessICMS() throws Exception {
        homeStepName = "E seleciono o menu ICMS";
        homePageInteractionsGeneral.clickMenuOption("ICMS");
    }

}
