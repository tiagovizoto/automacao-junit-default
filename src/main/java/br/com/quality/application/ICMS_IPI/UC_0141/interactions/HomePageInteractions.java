package br.com.quality.application.ICMS_IPI.UC_0141.interactions;

import br.com.quality.application.ICMS_IPI.UC_0141.pages.HomePage;
import br.com.quality.application.ICMS_IPI.geral.tests.RunBase;

public class HomePageInteractions extends RunBase {

    private RuntimeException runtimeException;

    public void validateHomePage() throws Exception {
        driverWEB().waitObject(HomePage.getTitleHomePage(),10);
        driverWEB().isVisible(HomePage.getTitleHomePage());
    }
}
