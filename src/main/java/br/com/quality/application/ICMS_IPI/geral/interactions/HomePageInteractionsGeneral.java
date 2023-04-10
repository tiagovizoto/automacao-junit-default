package br.com.quality.application.ICMS_IPI.geral.interactions;

import br.com.quality.application.ICMS_IPI.UC_0141.interactions.LoginPageInteractions;
import br.com.quality.general.support.GeneralUtils;
import br.com.quality.application.ICMS_IPI.geral.support.Utils;
import br.com.quality.application.ICMS_IPI.geral.pages.HomePage;
import br.com.quality.application.ICMS_IPI.geral.tests.RunBase;

import java.util.Objects;

public class HomePageInteractionsGeneral extends RunBase {

    GeneralUtils generalUtils = new GeneralUtils();
    LoginPageInteractions loginPageInteractions = new LoginPageInteractions();

    private static String chosenChannel;

    public static String getChosenChannel() {
        return chosenChannel;
    }

    public void setChosenChannel(String chosenChannel) {
        HomePageInteractionsGeneral.chosenChannel = chosenChannel;
    }

    public void openSiteSap() throws Exception {
        generalUtils.setStepPosition(GeneralUtils.getStepPosition() + 1);
        setChosenChannel("site_Sap");
        getManagementDriver().openSystem(Utils.managementEnvironmentSiteSAP());
    }

    public void checkHomeLoading() throws Exception {
        driverWEB().waitForPageLoaded();
    }

    public void selectSecurity() throws Exception {
        loginPageInteractions.clickAdvanced();
        loginPageInteractions.cliProceedLink();
    }

    public void clickMenuOption(String chosenProduct) throws Exception {
        chosenProduct = GeneralUtils.removeAccents(chosenProduct).toUpperCase();
        System.out.println("Produto selecionado: " + chosenProduct);

        if (Objects.equals(chosenProduct, "ICMS")) {
            driverWEB().click(HomePage.getMenuIcmsIPI());
        } else if (Objects.equals(chosenProduct, "PISCOFINS")) {

        } else if (Objects.equals(chosenProduct, "ISS")) {

        } else if (Objects.equals(chosenProduct, "IRPJCSLL")) {

        } else if (Objects.equals(chosenProduct, "ECD")) {

        } else if (Objects.equals(chosenProduct, "ECF")) {

        } else if (Objects.equals(chosenProduct, "TAXTOOL")) {

        } else if (Objects.equals(chosenProduct, "CIAP")) {

        } else if (Objects.equals(chosenProduct, "LTG")) {

        } else if (Objects.equals(chosenProduct, "FCI")) {

        } else if (Objects.equals(chosenProduct, "REINF")) {

        } else if (Objects.equals(chosenProduct, "GUIAS")) {

        } else {
            throw new IllegalArgumentException("O produto desejado ( " + chosenProduct + ") não está incluso na lista");
        }

    }


}
