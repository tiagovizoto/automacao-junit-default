package br.com.quality.application.ICMS_IPI.UC_0141.steps;

import br.com.quality.application.ICMS_IPI.UC_0141.interactions.LoginPageInteractions;
import br.com.quality.application.ICMS_IPI.geral.interactions.HomePageInteractionsGeneral;
import br.com.quality.application.ICMS_IPI.geral.tests.RunBase;
import br.com.quality.general.support.GeneralUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;

public class LoginSteps extends RunBase {

    GeneralUtils generalUtils = new GeneralUtils();
    LoginPageInteractions loginPageInteractions = new LoginPageInteractions();
    HomePageInteractionsGeneral homePageInteractionsGeneral = new HomePageInteractionsGeneral();
    private static String loginStepName;

    @Dado("que acesso a url do SAP")
    public void accessUrlSap() throws Exception {
        loginStepName = "Dado que acesso a url do site SAP";

        generalUtils.testBase("Sap Fiori - ICMS IPI", "Teste Regressivo (E2E)");
        generalUtils.stepBase(loginStepName,"");

        homePageInteractionsGeneral.openSiteSap();
        homePageInteractionsGeneral.selectSecurity();
        GeneralUtils.getStep().passed(loginStepName,loginPageInteractions.driverWEB());
    }

    @Dado("realizo login com usuario valido")
    public void signUser() throws Exception {
        loginStepName = "E Informo o login válido";
        loginPageInteractions.fillUsernameLogin();
        loginPageInteractions.fillPasswordLogin();
        loginPageInteractions.clickAuthenticateLogin();
    }
}
