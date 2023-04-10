// created by Rafael Tulio
package br.com.quality.report;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import br.com.quality.robots.IScreenshotCapture;

public class RobotReport {

	private ExtentReports extent;

	private Path screenshotsFilePath;

	public RobotReport(String filePath) {
		screenshotsFilePath = Paths.get(filePath).getParent().resolve("images");

		// initialize the HtmlReporter
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("OS","Windows");
		extent.setSystemInfo("Browser", "Nenhum");

		//configuration items to change the look and feel
		//add content, manage tests etc
		//htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("NttRobot Report");
		htmlReporter.config().setReportName("NttRobot Report");
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	public RobotTest createTestCase(String name, String description) {
		return createTestCase(name, description, null, null);
	}

	public RobotTest createTestCase(String name, String description, String preCondiction, String postCondiction) {
		description = createDescWithPreAndPostCondiction(
				description, preCondiction, postCondiction);
		ExtentTest test = extent.createTest(name, description);
		return new RobotTest(test, this);
	}

	private String createDescWithPreAndPostCondiction(String description, String preCondiction, String postCondiction) {
		return String.format(
				(StringUtils.isNotBlank(description) ?
						String.format("<p>DESC: %s</p>", description) : "")
						+ (StringUtils.isNotBlank(preCondiction) ?
						String.format("<p>PRE: %s</p>", preCondiction) : "")
						+ (StringUtils.isNotBlank(postCondiction) ?
						String.format("<p>POST: %s</p>", postCondiction) : ""));
	}

	protected void addScreenshot(ExtentTest test, IScreenshotCapture screenshotTaker) throws Exception {
		addScreenshot(test, screenshotTaker, null);
	}

	protected void addScreenshot(ExtentTest test, IScreenshotCapture screenshotTaker, String screenshotName) throws Exception {
		screenshotName = now() + "_" +  generateScreenshotFileName(test, screenshotName) ;

		if (screenshotName.length() > 30) {
			screenshotName = screenshotName.substring(0, 30) + ".png";
		}

		Path testImagesPath = getTestImagesPath(test);
		String filePath = testImagesPath.resolve(screenshotName.replaceAll("[\\\\/:*?\"<>|]", "")).toString();

		File file = screenshotTaker.captureScreenshotAsFile();
		FileUtils.copyFile(file, new File(filePath));
		test.addScreenCaptureFromPath(filePath);
	}

	private Path getTestImagesPath(ExtentTest test) {
		return screenshotsFilePath.resolve(
				getFormattedMainHierarchicalTestName(test));
	}

	private String getScreenshotFileNamePrefix(ExtentTest test) {
		Test model = test.getModel();
		return String.valueOf(model.getID());
	}

	private String generateScreenshotFileName(ExtentTest test, String screenshotName) {
		String generatedScreenshotName = screenshotName;
		if(StringUtils.isBlank(screenshotName))
			generatedScreenshotName = getFormattedTestName(test);

		String prefix = getScreenshotFileNamePrefix(test);
		return String.format("%s_%s.png", prefix, generatedScreenshotName);
	}

	private String getFormattedTestName(ExtentTest test) {
		Test model = test.getModel();
		String formatBnPattern = formatBnPattern(model.getName());

		return formatBnPattern;
	}

	private String getFormattedMainHierarchicalTestName(ExtentTest test) {
		Test model = test.getModel();
		String hierarchicalName = model.getHierarchicalName();
		String mainHierarchicalName = hierarchicalName.split("\\.")[0] ;

		return formatBnPattern(mainHierarchicalName);
	}
	public String now(){
		return now("yyyy_MM_dd_HH_mm_ss");
	}
	public String now(String formatStr)
	{
		DateFormat dateFormat = new SimpleDateFormat(formatStr); //$NON-NLS-1$
		java.util.Date date = new java.util.Date();

		return dateFormat.format(date);

	}
	private String formatBnPattern(String anyText) {
		return anyText.replaceAll(" ", "_").toLowerCase();
	}

	public void generate() {
		extent.flush();
	}

}
