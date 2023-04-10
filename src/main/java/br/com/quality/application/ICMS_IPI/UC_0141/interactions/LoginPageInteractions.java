package br.com.quality.application.ICMS_IPI.UC_0141.interactions;

import br.com.quality.application.ICMS_IPI.UC_0141.pages.HomePage;
import br.com.quality.application.ICMS_IPI.geral.pages.LoginPage;
import br.com.quality.application.ICMS_IPI.geral.support.Utils;
import br.com.quality.application.ICMS_IPI.geral.tests.RunBase;

public class LoginPageInteractions extends RunBase {
    public void clickAdvanced() throws Exception {
        driverWEB().click(LoginPage.getLinkAdvanced());
    }

    public void cliProceedLink() throws Exception {
         driverWEB().click(LoginPage.getLinkProceed());
    }

    public void fillUsernameLogin() throws Exception {
        driverWEB().setText(LoginPage.getUsernameLoginFieldInput(), Utils.getUsernameLogin());
    }

    public void fillPasswordLogin() throws Exception {
        driverWEB().setText(LoginPage.getPasswordLoginFieldInput(), Utils.getPasswordLogin());
    }

    public void clickAuthenticateLogin() throws Exception {
        driverWEB().click(LoginPage.getSubmitLoginButton());
    }

    public void verifyLoginSuccess() {
        driverWEB().waitObject(LoginPage.sapUserHead(),20);

        String titlePage = driverWEB().getDriver().getTitle();
        System.out.println(titlePage);
        String titleResultString = "Página Inicial";

        if (!titlePage.contains(titleResultString)){
            throw new IllegalArgumentException("Não foi possível carregar a página Inicial");
        }
    }
}
