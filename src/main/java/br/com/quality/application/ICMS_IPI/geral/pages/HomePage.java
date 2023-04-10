package br.com.quality.application.ICMS_IPI.geral.pages;

import org.openqa.selenium.By;

public class HomePage {

    final static private By menuICmsIpi = By.xpath("//div[@id='__item0-anchorNavigationBar-1-inner']");

    public static By getMenuIcmsIPI() {
        return menuICmsIpi;
    }
}
