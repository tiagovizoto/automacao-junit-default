package br.com.quality.application.ICMS_IPI.geral.pages;

import org.openqa.selenium.By;

public class LoginPage {

    final static private By advancedLinkOption = By.id("details-button");
    final static private By linkProceed = By.id("proceed-link");
    final static private By usernameLoginFieldInput = By.id("USERNAME_FIELD-inner");
    final static private By passwordLoginFieldInput = By.id("PASSWORD_FIELD-inner");
    final static private By submitLoginButton = By.id("LOGIN_LINK");

    final static private By headTitleSap = By.xpath("//h1[@class='sapUshellHeadTitle']");
    public static By getLinkProceed(){
     return linkProceed;
    }

    public static By getLinkAdvanced() {
        return advancedLinkOption;
    }

    public static By getUsernameLoginFieldInput() {
        return usernameLoginFieldInput;
    }

    public static By getPasswordLoginFieldInput() {
        return passwordLoginFieldInput;
    }

    public static By getSubmitLoginButton() {

        return submitLoginButton;
    }

    public static By sapUserHead() {
        return headTitleSap;
    }
}
