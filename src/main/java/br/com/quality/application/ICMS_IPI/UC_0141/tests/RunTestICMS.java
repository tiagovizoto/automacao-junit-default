package br.com.quality.application.ICMS_IPI.UC_0141.tests;

import br.com.quality.general.support.GeneralUtils;
import br.com.quality.application.ICMS_IPI.geral.tests.RunBase;
import br.com.quality.report.RobotReportBuilder;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:reports/evd/cucumber.html", "junit:reports/evd/Cucumber.xml" },
        features = "src/main/resources/application/ICMS_IPI/UC_0141/features",
        glue = "br.com.quality.application.ICMS_IPI.UC_0141.steps",
        tags = "@fluxo-regressivo"
)
public class RunTestICMS extends RunBase {

    @BeforeClass
    public static void startTest( ) {
        GeneralUtils generalUtils = new GeneralUtils();
        generalUtils.setReport(new RobotReportBuilder().newReport(System.getProperty("user.dir") +
                "/src/main/resources/application/ICMS_IPI/UC_0141/report/NttDataReportICMS_IPI.html").build());
    }

    @AfterClass
    public static void stopTest() {
        GeneralUtils.getReport().generate();
       getDriverWEB().closeApp();
    }

}
