// created by Rafael Tulio
package br.com.quality.robots;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import br.com.quality.robots.key.RnKeys;

public class SeleniumRobotsTool implements IRNRobotsTool {
	private WebDriver driver;
	private boolean switchTab = false;

	private int timeoutSeconds;
	private int pollingSeconds;

	private long startWaitByTime;

	public SeleniumRobotsTool(WebDriver driver, int timeoutSeconds, int pollingSeconds) {
		this.driver = driver;
		this.timeoutSeconds = timeoutSeconds;
		this.pollingSeconds = pollingSeconds;
		System.out.println("Automation framework created by Rafael Tulio");
	}

	public void openSystem(String app) {
		driver.get(app);
	}

	public void closeApp() {
		if(driver != null)
			driver.quit();
	}

	public void click(String xpath) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		getElement(xpath).click();
	}

	public void click(By by) throws Exception {
		waitForPageLoaded();
		validate(by);
		// SCRIPT FEITO PARA CORRIGIR UM BUG NO CHROMEDRIVER -> SERÁ CORRIGIDO NA VERSÃO 104 DO CHROME
		WebElement we;
		Boolean flagOk = true;
		setStartWaitByTime();
		do
		{
			try {
				getElement(by).click();
				flagOk = true;
				break;
			} catch (Exception e) {
				if (e.getMessage().contains("unknown error: cannot determine loading status")){
					flagOk = true;
					break;
				}

				Thread.sleep(200);
				flagOk = false;
			}
		} while(waitByTime(10000) == false);

		if (!flagOk) {
			getElement(by).click();
		}

		// SCRIPT FEITO PARA CORRIGIR UM BUG NO CHROMEDRIVER -> SERÁ CORRIGIDO NA VERSÃO 104 DO CHROME
		Boolean flag2Ok = true;
		setStartWaitByTime();
		do
		{
			try {
				waitForPageLoaded();
				flag2Ok = true;
				break;
			} catch (Exception e) {
				if (e.getMessage().contains("unknown error: cannot determine loading status")){
					flag2Ok = true;
					break;
				}

				Thread.sleep(200);
				flag2Ok = false;
			}
		} while(waitByTime(10000) == false);

		if (!flag2Ok) {
			waitForPageLoaded();
		}
	}

	public void click(By by, int position) throws Exception {
		waitForPageLoaded();
		validate(by, position);
		WebElement we;
		Boolean flagOk = true;

		setStartWaitByTime();

		do
		{
			try {
				getElement(by, position).click();
				flagOk = true;
				break;
			} catch (Exception e) {
				if (e.getMessage().contains("unknown error: cannot determine loading status")){
					flagOk = true;
					break;
				}
				Thread.sleep(200);
				flagOk = false;
			}
		} while(waitByTime(10000)==false);

		if (!flagOk) {
			getElement(by, position).click();
		}
	}

	public void setStartWaitByTime()
	{
		startWaitByTime=System.currentTimeMillis();
	}


	public boolean waitByTime(int miliseconds)
	{
		if(System.currentTimeMillis() - startWaitByTime < miliseconds)
			return false;
		else
			return true;
	}

	public void clickManual(By by, int position) throws Exception {
		waitForPageLoaded();
		validateManual(by, position);
		for (int x = 0; x < (this.timeoutSeconds * 10)  ; x++) {
			try {
				getElement(by, position).click();
				break;
			}
			catch (Exception e){
				Thread.sleep(100);
			}
			getElement(by, position).click();
		}
	}

	public void clickByCoordinate(String xpath, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		WebElement element = getElement(xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).moveByOffset(xOffset, yOffset).click().build().perform();
	}
	public void clickByCoordinate(By by, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(by);
		WebElement element = getElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).moveByOffset(xOffset, yOffset).click().build().perform();
	}
	public void doubleClick(String xpath) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(xpath)).doubleClick().build().perform();
	}
	public void doubleClick(By by) throws Exception {
		waitForPageLoaded();
		validate(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(by)).doubleClick().build().perform();
	}
	public void doubleClickByCoordinate(String xpath, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		WebElement element = getElement(xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).moveByOffset(xOffset, yOffset).doubleClick().build().perform();
	}
	public void doubleClickByCoordinate(By by, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(by);
		WebElement element = getElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).moveByOffset(xOffset, yOffset).doubleClick().build().perform();
	}
	public void setText(String xpath, String text) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		getElement(xpath).clear();
		getElement(xpath).sendKeys(text);
	}

	public void setText(By by, String text) throws Exception {
		waitForPageLoaded();
		validate(by);
		getElement(by).click();
		getElement(by).clear();
		getElement(by).sendKeys(text);
	}


	public void setText(By by, int position, String text) throws Exception {
		waitForPageLoaded();
		validate(by, position);
		getElement(by, position).clear();
		getElement(by, position).sendKeys(text);
	}
	
	public String getText(String xpath) throws Exception{
		return getElement(xpath).getText();
	}
	public String getText(By by) throws Exception{
		return getElement(by).getText();
	}

	public String getText(By by, int position) throws Exception{
		return getElement(by, position).getText();
	}

	@Override
	public void selectValue(String xpath, String value) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		new Select(getElement(xpath)).selectByValue(value);
	}


/*
	public void selectValue(By by, String value) throws Exception {
		waitForPageLoaded();
		validate(by);
		new Select(getElement(by)).selectByValue(value);
	}*/
	public void sendKeys(String xpath, RnKeys key) throws Exception {
		Keys seleniumKey = Keys.getKeyFromUnicode(key.getKeyChar());

		this.driver.findElement(By.xpath(xpath)).sendKeys(seleniumKey);
	}
	public void sendKeys(By by, RnKeys key) throws Exception {
		Keys seleniumKey = Keys.getKeyFromUnicode(key.getKeyChar());

		this.driver.findElement(by).sendKeys(seleniumKey);
	}
	public void tickCheckBox(String xpath, Boolean condition) throws Exception{
		waitForPageLoaded();
		validate(xpath);
		if (ifExists(xpath)) {
			WebElement elemnt = getElement(xpath);
			if (elemnt.isSelected() != condition) {
				elemnt.click();
			}
		} else {
			System.out.println("(checkBox) Element {" + xpath + "} doesn't exist.");
		}
	}
	public void tickCheckBox(By by, Boolean condition) throws Exception{
		waitForPageLoaded();
		validate(by);
		if (ifExists(by)) {
			WebElement elemnt = getElement(by);
			if (elemnt.isSelected() != condition) {
				elemnt.click();
			}
		} else {
			System.out.println("(checkBox) Element {" + by.toString() + "} doesn't exist.");
		}
	}
	public void dragAndDrop(String xpathSource, String xpathTarget) throws Exception {
		waitForPageLoaded();
		validate(xpathSource);
		validate(xpathTarget);
		WebElement elementSource = getElement(xpathSource);
		WebElement elementTarget = getElement(xpathTarget);
		Actions actions = new Actions(driver);
		actions.dragAndDrop(elementSource, elementTarget).build().perform();
	}
	public void dragAndDrop(By bySource, By byTarget) throws Exception {
		waitForPageLoaded();
		validate(bySource);
		validate(byTarget);
		WebElement elementSource = getElement(bySource);
		WebElement elementTarget = getElement(byTarget);
		Actions actions = new Actions(driver);
		actions.dragAndDrop(elementSource, elementTarget).build().perform();
	}
	public void dragAndDropByCoodinates(String xpath, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		WebElement element = getElement(xpath);
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(element, xOffset, yOffset).build().perform();
	}
	public void dragAndDropByCoodinates(By by, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(by);
		WebElement element = getElement(by);
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(element, xOffset, yOffset).build().perform();
	}
	public void mouseMove(String xpath, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		WebElement element = getElement(xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).moveByOffset(xOffset, yOffset).build().perform();
	}
	public void mouseMove(By by, int xOffset, int yOffset) throws Exception {
		waitForPageLoaded();
		validate(by);
		WebElement element = getElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).moveByOffset(xOffset, yOffset).build().perform();
	}
	public void setFocus(String xpath) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(xpath)).build().perform();
	}
	public void setFocus(By by) throws Exception {
		waitForPageLoaded();
		validate(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(by)).build().perform();
	}
	public void capturePrint() {
		try {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// ajustar local de salvamento do print
			FileUtils.copyFile(file, new File("D:\\screenshot.png"));
		} catch (IOException e) {
			System.out.println("Erro ao salvar captura de tela");
			e.printStackTrace();
		}
	}

	public void clearField(String xpath) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		getElement(xpath).click();
		getElement(xpath).clear();
	}
	public void clearField(By by) throws Exception {
		waitForPageLoaded();
		validate(by);
		getElement(by).click();
		getElement(by).clear();
	}
	public boolean isVisible(String xpath) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		return getElement(xpath).isDisplayed();
	}
	public boolean isVisible(By by) throws Exception {
		waitForPageLoaded();
		validate(by);
		return getElement(by).isDisplayed();
	}
	public boolean isVisible(By by, int position) throws Exception {
		waitForPageLoaded();
		validate(by, position);
		return getElement(by, position).isDisplayed();
	}
	public boolean ifExists(String xpath) {
		try {
			WebElement el = this.getElement(xpath);
		
			return el != null && el.getSize().getWidth() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean ifExists(By by) {
		try {
			WebElement el = this.getElement(by);

			return el != null && el.getSize().getWidth() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean waitObject(String xpath,int seconds) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(seconds))
					.pollingEvery(Duration.ofSeconds(this.pollingSeconds))
					.ignoring(NoSuchElementException.class);
			WebElement el = wait.until((WebDriver driver) -> {
				return driver.findElement(By.xpath(xpath));
			});

			return el != null;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean waitObject(By by,int seconds) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(seconds))
					.pollingEvery(Duration.ofSeconds(this.pollingSeconds))
					.ignoring(NoSuchElementException.class);
			WebElement el = wait.until((WebDriver driver) -> {
				return driver.findElement(by);
			});

			return el != null;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitObjectManual(By by, int position ,int seconds) {
		try {

			WebElement el;

			for (int x = 0; x < (seconds * 10)  ; x++) {
				try {
					el= driver.findElements(by).get(position);
					if  (el != null)
						return true;
					Thread.sleep(100);
				}
				catch (Exception e){
					Thread.sleep(100);
				}
			}
			return false;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitObject(By by, int position ,int seconds) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(seconds))
					.pollingEvery(Duration.ofSeconds(this.pollingSeconds))
					.ignoring(NoSuchElementException.class);
			WebElement el = wait.until((WebDriver driver) -> {
				return driver.findElements(by).get(position);
			});

			return el != null;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean waitObjectInvisibility(String xpath, int seconds){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(this.pollingSeconds))
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}
	public boolean waitObjectInvisibility(By by, int seconds){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(this.pollingSeconds))
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	public boolean waitObjectInvisibility(int seconds, WebElement... elements){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(this.pollingSeconds))
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(elements)));
	}
	public boolean isEnabled(String xpath) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		if (getElement(xpath).isEnabled()) {
			return true;
		}
		return false;
	}
	public boolean isEnabled(By by) throws Exception {
		waitForPageLoaded();
		validate(by);
		if (getElement(by).isEnabled()) {
			return true;
		}
		return false;
	}
	public void waitForPageLoaded() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(this.timeoutSeconds))
				.pollingEvery(Duration.ofSeconds(this.pollingSeconds));
		wait.until(driver ->
			((JavascriptExecutor) driver)
			.executeScript("return document.readyState")
			.toString().equals("complete"));
	}

	public void ScrollToObject(String xpath) throws Exception{
		waitForPageLoaded();
		validate(xpath);
		WebElement element = getElement(xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	public void ScrollToObject(By by) throws Exception{

		// SCRIPT FEITO PARA CORRIGIR UM BUG NO CHROMEDRIVER -> SERÁ CORRIGIDO NA VERSÃO 104 DO CHROME
		WebElement we;
		Boolean flagOk = true;
		setStartWaitByTime();
		do
		{
			try {
				waitForPageLoaded();
				flagOk = true;
				break;
			} catch (Exception e) {
				if (e.getMessage().contains("unknown error: cannot determine loading status")){
					flagOk = true;
					break;
				}
				Thread.sleep(200);
				flagOk = false;
			}
		} while(waitByTime(10000)==false);

		if (!flagOk) {
			waitForPageLoaded();
		}
		validate(by);
		WebElement element = getElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void ScrollToObject(By by, int position) throws Exception{
		waitForPageLoaded();
		validate(by, position);
		WebElement element = getElement(by, position);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void switchTab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		if (switchTab) {
			driver.close();
			driver.switchTo().window(tabs.get(0));
			switchTab = false;
		} else {
			driver.switchTo().window(tabs.get(1));
			switchTab = true;
		}
	}

	public void validate(String xpath) throws Exception {
		waitForPageLoaded();
		if(!waitObject(xpath, this.timeoutSeconds))
			throw new Exception("Element [" + xpath + "] not exists!");
	}
	public void validate(By by) throws Exception {
		waitForPageLoaded();
		if(!waitObject(by, this.timeoutSeconds))
			throw new Exception("Element [" + by.toString() + "] not exists!");
	}
	public void validate(By by, int position) throws Exception {
		waitForPageLoaded();
		if(!waitObject(by, position, this.timeoutSeconds))
			throw new Exception("Element [" + by.toString() + "] not exists!");
	}

	public void validateManual(By by, int position) throws Exception {
		waitForPageLoaded();
		if(!waitObjectManual(by, position, this.timeoutSeconds))
			throw new Exception("Element [" + by.toString() + "] not exists!");
	}
	public WebElement getElement(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
//	public WebElement getElement(By by) {
//		return driver.findElement(by);
//	}

	public WebElement getElement(By by) throws Exception {
		WebElement we;
		for(int x = 0; x < 1; x++)
		{
			try {
				we = driver.findElement(by);
				return we;
			} catch (Exception e) {
				Thread.sleep(100);
			}
		}
		we = driver.findElement(by);
		return we;
	}
	public WebElement getElement(By by, int position) {
		return driver.findElements(by).get(position);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String captureCss(String xpath) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		try {
			WebElement elemnt = driver.findElement(By.xpath(xpath));
			if (elemnt.isDisplayed()) {
				if (!(elemnt.getAttribute("style") == null || elemnt.getAttribute("style") == "")) {
					return elemnt.getAttribute("style");
				} else {
					return elemnt.getCssValue("style");
				}
			} else
				return "";

		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	public String captureCss(By by) throws Exception {
		waitForPageLoaded();
		validate(by);
		try {
			WebElement elemnt = driver.findElement(by);
			if (elemnt.isDisplayed()) {
				if (!(elemnt.getAttribute("style") == null || elemnt.getAttribute("style") == "")) {
					return elemnt.getAttribute("style");
				} else {
					return elemnt.getCssValue("style");
				}
			} else
				return "";

		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	public String captureCss(String xpath, String attribute) throws Exception {
		waitForPageLoaded();
		validate(xpath);
		try {

			if (attribute.equals("Color")){
				return driver.findElement(By.xpath(xpath)).getCssValue("color");
			}
			else if (attribute.equals("bgColor")){

				String s = driver.findElement(By.xpath(xpath)).getCssValue("background-color");
				String[] cor = new String[10]; int i=0;

				s = s.substring(s.indexOf("(")+1, s.indexOf(")"));
				StringTokenizer st = new StringTokenizer(s);
				while(st.hasMoreElements()){
					cor[i]= st.nextToken(",").trim();
					i++;
				}
				s="";
				for(int j=0;j<3;j++){
					s+=""+cor[j];
				}

				return s;

			}
			else return "";


	} catch (Exception e) {
		System.out.println(e);
		return "";
	}
	}
	public String captureCss(By by, String attribute) throws Exception {
		waitForPageLoaded();
		validate(by);
		try {

			if (attribute.equals("Color")){
				return driver.findElement(by).getCssValue("color");
			}
			else if (attribute.equals("bgColor")){

				String s = driver.findElement(by).getCssValue("background-color");
				String[] cor = new String[10]; int i=0;

				s = s.substring(s.indexOf("(")+1, s.indexOf(")"));
				StringTokenizer st = new StringTokenizer(s);
				while(st.hasMoreElements()){
					cor[i]= st.nextToken(",").trim();
					i++;
				}
				s="";
				for(int j=0;j<3;j++){
					s+=""+cor[j];
				}

				return s;

			}
			else return "";


		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	@Override
	public LocationPosition getLocation(String xpath) {
		WebElement elmnt = driver.findElement(By.xpath(xpath));
		return new LocationPosition(elmnt.getLocation().x, elmnt.getLocation().y);
	}
	public LocationPosition getLocation(By by) {
		WebElement elmnt = driver.findElement(by);
		return new LocationPosition(elmnt.getLocation().x, elmnt.getLocation().y);
	}
	@Override
	public List<Object> returnSelectOptions(String xpath) {
		Select combo = new Select(driver.findElement(By.xpath(xpath)));
		List<WebElement> list = combo.getOptions();
		return new ArrayList<>(list);
	}
	public List<Object> returnSelectOptions(By by) {
		Select combo = new Select(driver.findElement(by));
		List<WebElement> list = combo.getOptions();
		return new ArrayList<>(list);
	}
	@Override
	public int elemntCount(String xpath) {
		List<WebElement> elemnt = driver.findElements(By.xpath(xpath));
		return elemnt.size();
	}
	public int elemntCount(By by) {
		List<WebElement> elemnt = driver.findElements(by);
		return elemnt.size();
	}

	@Override
	public File captureScreenshotAsFile() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	@Override
	public void closeWindow(String xpath) throws Exception {
		throw new NotImplementedException();
	}

	public void switchIframe(String... id){
		driver.switchTo().defaultContent();
		WebElement iframe;

		if(id.length > 0){
			for(int i = 0; i < id.length; i++){
				iframe = driver.findElement(By.id(id[i]));
				driver.switchTo().frame(iframe);
			}
		}
	}
}
