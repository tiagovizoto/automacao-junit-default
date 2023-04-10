// created by Rafael Tulio
package br.com.quality.report;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;

import br.com.quality.robots.IScreenshotCapture;

public class RobotTest {

	private ExtentTest test;
	private RobotReport bnReport;

	public RobotTest(ExtentTest test, RobotReport bnReport) {
		this.test = test;
		this.bnReport = bnReport;
	}
	public String getName(){
		return this.test.getModel().getName();
	}
	public String getDescription(){
		return this.test.getModel().getDescription();
	}
	public RobotTest passed(String details) {
		test.pass(details);
		return this;
	}

	public RobotTest passed(String details, IScreenshotCapture screenshotTaker) throws Exception {
		test.pass(details);
		bnReport.addScreenshot(test, screenshotTaker);
		return this;
	}

	public RobotTest fail(String details) {
		test.fail(details);
		return this;
	}

	public RobotTest fail(String details, IScreenshotCapture screenshotTaker) throws Exception {
		test.fail(details);
		bnReport.addScreenshot(test, screenshotTaker);
		return this;
	}

	public void addScreenCaptureFromPath(String imagePath) throws IOException {
		test.addScreenCaptureFromPath(imagePath);
	}

	public void addScreenCapture(IScreenshotCapture screenshotTaker) throws Exception {
		bnReport.addScreenshot(test, screenshotTaker);
	}

	public RobotTest createTestStep(String name, String desc) {
		return new RobotTest(test.createNode(name, desc), bnReport);
	}

	public void assertion(RobotTestAssertion testAssertion, String errorMessage) {
		try {
			testAssertion.assertion();
		} catch (Error e) {
			fail(errorMessage);
			throw e;
		}
	}
}
