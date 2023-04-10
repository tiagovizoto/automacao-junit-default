// created by Rafael Tulio
package br.com.quality.robots.builder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.quality.robots.IRNRobotsTool;
import br.com.quality.robots.SeleniumRobotsTool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SeleniumRobotsBuilder {

	private ChromeOptions optionsChrome;
	private ChromeOptions optionsChromeBeta;
	private FirefoxOptions optionsFirefox;
	private OperaOptions optionsOpera;
	private InternetExplorerOptions optionsIE;
	private EdgeOptions optionsEdge;
	private int timeoutSeconds;
	private int pollingSeconds;
	private IRNRobotsTool seleniumRobot;
	private WebDriver driver;
	private String sURL;

	public SeleniumRobotsBuilder() {
		this.timeoutSeconds = 30;
		this.pollingSeconds = 5;
	}

	public SeleniumRobotsBuilder driverPath(String driverPath) {
		if (driverPath.contains("chrome")) {
			this.chrome();
			System.setProperty("webdriver.chrome.driver", driverPath);
		} else if (driverPath.contains("beta")) {
			this.chromeBeta();
			System.setProperty("webdriver.chrome.driver", driverPath);
		} else if (driverPath.contains("gecko")){
			this.firefox();
			System.setProperty("webdriver.gecko.driver", driverPath);
		} else if (driverPath.contains("opera")){
			this.opera();
			System.setProperty("webdriver.opera.driver", driverPath);
		} else if (driverPath.toUpperCase().contains("IE")){
			this.ie();
			System.setProperty("webdriver.ie.driver", driverPath);
		} else if (driverPath.contains("edge")) {
			this.edge();
			System.setProperty("webdriver.edge.driver", driverPath);
		}

		return this;
	}
	public SeleniumRobotsBuilder setChromeOptions(ChromeOptions options){
		this.optionsChrome = options;
		return this;
	}

	public SeleniumRobotsBuilder setChromeBetaOptions(ChromeOptions options) {
		this.optionsChromeBeta = options;
		return this;
	}

	public SeleniumRobotsBuilder remoteExec(String sURL) {
		this.sURL = sURL;
		return this;
	}
	public SeleniumRobotsBuilder chrome() {
		if (optionsChrome == null) this.optionsChrome = new ChromeOptions();
		return this;
	}

	public SeleniumRobotsBuilder chromeBeta() {
		if (optionsChromeBeta == null) this.optionsChromeBeta = new ChromeOptions();
		return this;
	}

	public SeleniumRobotsBuilder firefox() {
		if (optionsFirefox == null) this.optionsFirefox = new FirefoxOptions();
		return this;
	}

	public SeleniumRobotsBuilder opera() {
		if (optionsOpera == null) this.optionsOpera = new OperaOptions();
		return this;
	}

	public SeleniumRobotsBuilder ie() {
		if (optionsIE == null) this.optionsIE = new InternetExplorerOptions();
		return this;
	}

	public SeleniumRobotsBuilder edge() {
		if (optionsEdge == null) this.optionsEdge = new EdgeOptions();
		return this;
	}

	public SeleniumRobotsBuilder starMaximized() {
		if (optionsChrome != null) {
			this.optionsChrome.addArguments("start-maximized");
		} else if (optionsChromeBeta != null) {
			this.optionsChromeBeta.addArguments("start-maximized");
			optionsChromeBeta.setBinary("C:\\Program Files\\Google\\Chrome Beta\\Application\\chrome.exe");
		} else if (optionsFirefox != null) {
			 this.optionsFirefox.addArguments("start-maximizer");
		} else if (optionsOpera != null){
			this.optionsOpera.addArguments("--start-maximized");
		} else if (optionsIE != null) {
			this.optionsIE.addCommandSwitches("start-maximizer");
		} else if (optionsEdge != null) {
			//TODO addArguments foi incluido na versão 4.0, atualizar selenium para 4.0
		} else {
			System.out.println("[ERROR] - NOT MAXIMIZED. Set the browser before maximize!");
			throw new WebDriverException("[ERROR] - NOT MAXIMIZED. Set the browser before maximize!");
		}

		return this;
	}

	public SeleniumRobotsBuilder headless() {
		if (optionsChrome != null) {
			this.optionsChrome.addArguments("--headless");
		} else if (optionsChromeBeta != null) {
			this.optionsChromeBeta.addArguments("--headless");
		} else if (optionsFirefox != null) {
			this.optionsFirefox.addArguments("--headless");
		} else if (optionsOpera != null) {
			this.optionsOpera.addArguments("--headless");
		} else if (optionsIE != null) {
			this.optionsIE.addCommandSwitches("--headless");
		} else if (optionsEdge != null) {
			//TODO addArguments foi incluido na versão 4.0, atualizar selenium para 4.0
		} else {
			System.out.println("[ERROR] - HEADLESS NOT CCONFIGURED. Set the browser before or browser does not support headless.");
			throw new WebDriverException("[ERROR] - HEADLESS NOT CCONFIGURED. Set the browser before or browser does not support headless.");
		}

		return this;
	}

	 // tempo total para esperar o objeto aparecer
	public SeleniumRobotsBuilder timeoutSeconds(int timeoutSeconds){
		this.timeoutSeconds = timeoutSeconds;
		return this;
	}
	// tempo para reverificação se existe o objeto 
	public SeleniumRobotsBuilder pollingSeconds(int pollingSeconds){
		this.pollingSeconds = pollingSeconds;
		return this;
	}

	public SeleniumRobotsTool build() {
		if (sURL == null) {
			if (optionsChrome != null) {
				 this.driver = new ChromeDriver(optionsChrome);
			} else if (optionsChromeBeta != null) {
				this.driver = new ChromeDriver(optionsChromeBeta);
			} else if (optionsFirefox != null) {
				this.driver = new FirefoxDriver(optionsFirefox);
			} else if (optionsOpera != null) {
				this.driver = new OperaDriver(optionsOpera);
			} else if (optionsIE != null) {
				this.driver = new InternetExplorerDriver(optionsIE);
			} else if (optionsEdge != null) {
				this.driver = new EdgeDriver(optionsEdge);
			} else {
				System.out.println("[ERROR] - Build falhou. Verifique se o browser é compatível!");
				throw new WebDriverException("[ERROR] - Build falhou. Verifique se o browser é compatível!");
			}
		} else {
			try {
				if (optionsChrome != null) {
					this.driver = new RemoteWebDriver(new URL(sURL), optionsChrome);
				} else if (optionsChromeBeta != null) {
					this.driver = new RemoteWebDriver(new URL(sURL), optionsChromeBeta);
				} else if (optionsFirefox != null) {
					this.driver = new RemoteWebDriver(new URL(sURL), optionsFirefox);
				} else if (optionsOpera != null) {
					this.driver = new RemoteWebDriver(new URL(sURL), optionsOpera);
				} else if (optionsIE != null) {
					this.driver = new RemoteWebDriver(new URL(sURL), optionsIE);
				} else if (optionsEdge != null) {
					this.driver = new RemoteWebDriver(new URL(sURL), optionsEdge);
				} else {
					System.out.println("[ERROR] - Build falhou. Verifique se o browser é compatível!");
					throw new WebDriverException("[ERROR] - Build falhou. Verifique se o browser é compatível!");
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
		this.seleniumRobot = new SeleniumRobotsTool(driver, timeoutSeconds, pollingSeconds);

		return (SeleniumRobotsTool) seleniumRobot;
	}
}
