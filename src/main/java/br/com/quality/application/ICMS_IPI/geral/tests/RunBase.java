package br.com.quality.application.ICMS_IPI.geral.tests;

import br.com.quality.robots.SeleniumRobotsTool;
import br.com.quality.robots.builder.SeleniumRobotsBuilder;

public class RunBase {

    private static SeleniumRobotsTool driverWEB;

    public static SeleniumRobotsTool getDriverWEB() {
        return driverWEB;
    }

    public void setDriverWEB(SeleniumRobotsTool driverWEB) {
        this.driverWEB = driverWEB;
    }

    public SeleniumRobotsTool driverWEB() {
        if (getDriverWEB() == null) {
            return getManagementDriver();
        } else {
            return getDriverWEB();
        }
    }

    public SeleniumRobotsTool getManagementDriver() {
        String browser = System.getProperty("browser", "chrome");
        String osName = System.getProperty("os", "linux");
        //String osName = System.getProperty("os", "windows");
        String driverPath = "src/main/resources/DriverSelenium/";

        if (getDriverWEB() != null) {
            getDriverWEB().closeApp();
        }

        if (browser.contains("chrome") && osName.contains("windows")) {
            SeleniumRobotsTool driver = new SeleniumRobotsBuilder()
                    .driverPath(driverPath + "windows/chromedriver.exe")
                    .starMaximized()
                    .timeoutSeconds(5)
                    .pollingSeconds(1)
                    .build();

            System.out.println("Navegador desejado: Chrome");
            setDriverWEB(driver);

            return driver;
        } else if (browser.contains("chrome") && osName.contains("linux")) {
            SeleniumRobotsTool driver = new SeleniumRobotsBuilder()
                    .driverPath(driverPath + "linux/chromedriver")
                    .starMaximized()
                    .timeoutSeconds(5)
                    .pollingSeconds(1)
                    .build();

            System.out.println("Navegador desejado: Chrome Beta");
            setDriverWEB(driver);

            return driver;
        } else {
            throw new IllegalArgumentException("O navegador informado (" + browser + ") não é válido");
        }
    }

}
