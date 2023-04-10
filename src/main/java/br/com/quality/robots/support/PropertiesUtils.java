package br.com.quality.robots.support;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {
    private Properties prop = new Properties();
    public PropertiesUtils() {
       try (InputStream input = new FileInputStream("nttCorpRobotFramework.properties")) {
           prop.load(input);
       } catch (IOException io) {
           io.printStackTrace();
       }
    }
    public String getProperty(String key) {
        if (prop.getProperty(key)!= null)
            return prop.getProperty(key);
        else
            System.out.println("Property key: "+ key + " NOT FOUND!");
        return null;
    }

    public static class NttRobotProperties {
        // ==========================
        // ===========================
        // Chave das propriedades no arquivo .properties
        public static final String PROPERTY_REPORT_ENABLE = "robot.report.enable";
        public static final String PROPERTY_REPORT_DB_CONNECTIONSTRING = "robot.report.db.connectionstring";
        public static final String PROPERTY_REPORT_DB_USER = "robot.report.db.user";
        public static final String PROPERTY_REPORT_DB_PASS = "robot.report.db.pass";
        /**
         * Construtor padrão da classe
         */
        private NttRobotProperties() {
        }
    }
}