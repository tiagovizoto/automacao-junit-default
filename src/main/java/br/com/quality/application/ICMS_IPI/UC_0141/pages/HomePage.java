package br.com.quality.application.ICMS_IPI.UC_0141.pages;

import org.openqa.selenium.By;

public class HomePage {

    final static private By iconUser = By.id("meAreaHeaderButton");

    public static By getTitleHomePage() {
        return iconUser;
    }
}
