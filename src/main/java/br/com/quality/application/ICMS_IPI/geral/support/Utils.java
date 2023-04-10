package br.com.quality.application.ICMS_IPI.geral.support;

import br.com.quality.application.ICMS_IPI.geral.tests.RunBase;

import java.util.Objects;

public class Utils extends RunBase {

    private static String enviroment;

    public static String getEnviroment() {
        return enviroment;
    }

    public static void setEnviroment(String enviroment) {
        Utils.enviroment = enviroment;
    }

    /***
     * retornar variavel do ambiente e url de acesso
     * @return
     */
    public static String managementEnvironmentSiteSAP() {
        String environment = System.getProperty("envSiteSap", "qa");
        String url;

        setEnviroment(environment);
        System.out.println(environment);

        if ((Objects.equals(environment, "prod"))) {
            // url = "";
            throw new IllegalArgumentException("NÃO REALIZAR TESTES EM PROD - ORDENS DA DIRETORIA");
        } else if ((Objects.equals(environment, "stage"))) {
            throw new IllegalArgumentException("A URL de Stage ainda não foi configurada");
        } else if ((Objects.equals(environment, "qa"))) {
            url = "https://sapgpopas001.fh.com.br:8446/sap/bc/ui2/flp#Shell-home";
        } else {
            throw new IllegalArgumentException("O ambiente informado (" + environment + ") não é válido");
        }
        System.out.println(url);

        return url;
    }

    public static void stopTest() {
        getDriverWEB().closeApp();
    }

    public static String getUsernameLogin() {
        String username;
        username = "US_FRONTAUTO";
        return username;
    }

    public static String getPasswordLogin() {
        String password;
        password = "frontauto2022";
        return password;
    }
}
