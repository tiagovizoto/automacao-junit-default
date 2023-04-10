// created by Rafael Tulio
package br.com.quality.report;

public class RobotReportBuilder {

	RobotReport report;
	private String filePath;
	
	public RobotReportBuilder newReport(String filePath) {
		this.filePath = filePath;
		return this;
	}
	
	public RobotReport build() {
		report = new RobotReport(this.filePath);
		return report;
	}
}
