// created by Rafael Tulio
package br.com.quality.robots;

import java.util.List;

import br.com.quality.robots.key.RnKeys;

public interface IRNRobotsTool extends IScreenshotCapture {

	public String captureCss(String xpath) throws Exception;

	public String captureCss(String xpath, String attribute) throws Exception;

	/**
	 * The method finds an element and sets a condition
	 * @param xpath - the element path
	 * @param condition - true to checked or false to unchecked
	 */
	public void tickCheckBox(String xpath, Boolean condition) throws Exception;

	/**
	 * The method finds an element and clean their content
	 *
	 * @param xpath
	 *            - the element path
	 */
	public void clearField(String xpath) throws Exception;

	/**
	 * The method clicks in a element by xpath
	 * @param xpath - the element path
	 * @throws Exception - throws an exception if the element is not found
	 */
	public void click(String xpath) throws Exception;

	/**
	 * The method finds an element and sets a coordinate to click
	 * @param xpath - the element path
	 * @param xOffset - the horizontal axis
	 * @param yOffset - the vertical axis
	 *
	 */
	public void clickByCoordinate(String xpath, int xOffset, int yOffset) throws Exception;

	/**
	 * The method closes an application
	 */
	public void closeApp();

	public void closeWindow(String xpath) throws Exception;

	/**
	 * The method sends a double click in a element by xpath
	 * @param xpath - the element path
	 */
	public void doubleClick(String xpath) throws Exception;

	/**
	 * The method finds an element and sets a coordinate to double click
	 * @param xpath - the element path
	 * @param x - the horizontal axis
	 * @param y - the vertical axis
	 */
	public void doubleClickByCoordinate(String xpath, int xOffset, int yOffset) throws Exception;

	/**
	 * The method drags an element from one point to another
	 * @param xpathSource - the element source path
	 * @param xpathTarget - the element target path
	 */
	public void dragAndDrop(String xpathSource, String xpathTarget) throws Exception ;

	/**
	 * The method drags an element from one point to another by coordinates
	 * @param xpath - the element path
	 * @param xOffset - the horizontal axis
	 * @param yOffset - the vertical axis
	 */
	public void dragAndDropByCoodinates(String xpath, int xOffset, int yOffset) throws Exception;

	public int elemntCount(String xpath);

	/**
	 * The method finds an element and respond if it's enabled
	 *
	 * @param xpath
	 *            - the element path
	 * @return - true to a enabled element or false to a not enabled element
	 */
	public boolean isEnabled(String xpath) throws Exception;

	/**
	 * The method responds if an element exists on the page
	 *
	 * @param xpath
	 *            - the element path
	 * @return - true for existing element or false for nonexistent element
	 */
	public boolean ifExists(String xpath);

	public Object getDriver();

	public Object getElement(String xpath);

	public LocationPosition getLocation(String xpath);

	/**
	 * The method gets a text in a element
	 *
	 * @param xpath
	 *            - the element path
	 * @return - the text of the selected element
	 */
	public String getText(String xpath) throws Exception;

	/**
	 * The method finds an element and respond if it's visible
	 *
	 * @param xpath
	 *            - the element path
	 * @return - true to a visible element or false to a not visible element
	 * @throws Exception
	 */
	public boolean isVisible(String xpath) throws Exception;

	/**
	 *
	 * @param xpath - the element path
	 * @param x - the horizontal axis
	 * @param y - the vertical axis
	 * @throws Exception - throws an exception if the element is not found
	 */
	public void mouseMove(String xpath, int x, int y) throws Exception;

	/**
	 * The method launches an application
	 * @param app - application's name
	 *
	 */
	public void openSystem(String app);

	public List<Object> returnSelectOptions(String xpath);

	/**
	 * The method scrolls the screen until an element
	 *
	 * @param xpath
	 *            - the element path
	 * @param direction
	 *            - the direction to scroll the screen
	 * @param offSet
	 *            - the number of times the scroll will be applied
	 * @param time
	 *            - the waiting time for the object to load
	 */
	public void ScrollToObject(String xpath) throws Exception;

	public void selectValue(String xpath, String value) throws Exception;

	/**
	 * The method sends a keyboard command to a element
	 *
	 * @param xpath
	 *            - the element path
	 * @param key
	 *            - the keyboard command
	 */
	public void sendKeys(String xpath, RnKeys key) throws Exception;

	/**
	 * The method find an element and put it in focus
	 * @param xpath - The element path
	 */
	public void setFocus(String xpath) throws Exception;

	/**
	 * The method sets a text in a element
	 *
	 * @param xpath
	 *            - the element path
	 * @param text
	 *            - the text to set on element
	 */
	public void setText(String xpath, String text) throws Exception;

	/**
	 * The method alternates the application tab
	 */
	public void switchTab();

	/**
	 * The method does validation before the following actions:
	 * @param xpath
	 * @throws Exception
	 */
	public void validate(String xpath) throws Exception;

	/**
	 * The method waits an object loads on screen
	 *
	 * @param xpath
	 *            - the element path
	 * @param time
	 *            - time the application waits before the timeout
	 */
	public boolean waitObject(String xpath, int seconds)throws Exception;

	/**
	 * The method waits the complete loading of the page
	 */
	public void waitForPageLoaded();
}
