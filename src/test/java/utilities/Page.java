package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Page {

	private static WebDriver driver = Browser.getDriver();

	/**
	 * Moves to given target Element using Actions class.
	 * 
	 * @param element
	 */
	public static void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * Rotates the scroll wheel on wheel- equipped mice
	 * Uses robot class
	 * 
	 * @param scroll,
	 *            number of "notches" to move the mouse wheel Negative values
	 *            indicate movement up/away from the user, 
	 *            positive values indicate movement down/towards the user.
	 */
	public static void mouseWheel(int scrolls) {
		try {
			Robot robot = new Robot();
			robot.mouseWheel(scrolls);
		} catch (AWTException e) {
			System.out.println("Could not scroll the mouse :" + e.getMessage());
		}
	}

	
	
	
	
	/**
	 * Waits for page to get loaded during the given timeouts.
	 * 
	 * @param timeOutInSeconds
	 */
	public static void waitForPageLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to get loaded...");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
		}
	}
	
	
	/**
	 * Switches to second the window of the Browser.
	 * 
	 * @param currentHandle;
	 */
	public static void switchToSecondWindow(String currentHandle) {
		List<String> handles = Selenium.getListOfHandles();
		for (String handle : handles) {
			if (!handle.equals(currentHandle)) {
				driver.switchTo().window(handle);
				return;
			}
		}
	}
	
	
	/**
	 * Switches to the window by given title.
	 * 
	 * @param targetTitle
	 */
	public static void switchToWindow(Object targetTitle) {
		String origin = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().trim().equalsIgnoreCase((String) targetTitle)) {
				return;
			}
		}
		driver.switchTo().window(origin);
	}
	
	
	/**
	 * Switches to the window by given Url.
	 * 
	 * @param targetUrl
	 */
	public static void switchToWindow(String targetUrl) {
		String origin = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getCurrentUrl().equalsIgnoreCase(targetUrl)) {
				return;
			}
		}
		driver.switchTo().window(origin);
	}
	
	
	/**
	 * Validates given expected and actual titles of the page.
	 * 
	 * @param expectedTitle
	 */
	public static void verifyTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals( expectedTitle, actualTitle , "Title verification failed: ");
	}
	
	
	/**
	 * Validates given expected and actual URLs of the page.
	 * 
	 * @param expectedUrl
	 */
	public static void verifyUrl(String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals( expectedUrl, actualUrl , "URl verification failed: ");
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
