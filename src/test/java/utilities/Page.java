package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Page {

	/**
	 * Moves to the given target Element using Actions class.
	 * 
	 * @param element
	 */
	public static void moveToElement(WebElement element) {
		WebDriver driver = Browser.getDriver();
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * Rotates the scroll wheel on wheel- equipped mice.
	 * 
	 * @param scroll,
	 *            number of "notches" to move the mouse wheel Negative values
	 *            indicate movement up/away from the user, positive values indicate
	 *            movement down/towards the user.
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
	public static void waitForPageLoad(int timeOutInSeconds) {
		WebDriver driver = Browser.getDriver();
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to get loaded...");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
		}
	}

	/**
	 * Waits for the Alert during the given amount of time, And dismisses it.
	 * 
	 * @param timeInSec
	 */
	public void waitToDismissAlert(int timeInSec) {
		WebDriver driver = Browser.getDriver();
		try {
			// Waits till alert is present
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			alert.dismiss();
			System.out.println("Dismissed the alert successfully.");
		} catch (Throwable e) {
			System.err.println("Error came while waiting for the alert: " + e.getMessage());
		}
	}

	/**
	 * Switches to second the window of the Browser.
	 * 
	 * @param currentHandle;
	 */
	public static void switchToSecondWindow() {
		WebDriver driver = Browser.getDriver();
		String currentHandle = driver.getWindowHandle();
		List<String> handles = Browser.getListOfHandles();
		for (String handle : handles) {
			if (!handle.equals(currentHandle)) {
				driver.switchTo().window(handle);
				System.out.println("Switched to second Window: " + driver.getCurrentUrl());
				return;
			}
		}
	}

	public static void switchToWindow(int index) {
		WebDriver driver = Browser.getDriver();
		List<String> handles = Browser.getListOfHandles();
		if (handles.size() > index)
			driver.switchTo().window(handles.get(index));
		else {
			System.out.println("No any window to move.");
		}
	}

	/**
	 * Switches to the window by given title of the Page.
	 * 
	 * @param targetTitle
	 */
	public static void switchToWindowByTitle(String targetTitle) {
		WebDriver driver = Browser.getDriver();
		String origin = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().trim().equalsIgnoreCase(targetTitle)) {
				System.out.println("Switched to new Window: " + driver.getCurrentUrl());
				return;
			}
		}
		System.out.println("==> switchToWindowByTitle() Couldn't switch to new Window");
		driver.switchTo().window(origin);
	}

	/**
	 * Switches to the window by given URL of the window.
	 * 
	 * @param targetUrl
	 */
	public static void switchToWindow(String targetUrl) {
		WebDriver driver = Browser.getDriver();
		String origin = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getCurrentUrl().equalsIgnoreCase(targetUrl)) {
				System.out.println("Switched to new Window: " + driver.getCurrentUrl());
				return;
			}
		}
		System.out.println("==> switchToWindow() Couldn't switch to new Window");
		driver.switchTo().window(origin);
	}

	/**
	 * Validates given expected and actual titles of the page.
	 * 
	 * @param expectedTitle
	 */
	public static void verifyTitle(String expectedTitle) {
		WebDriver driver = Browser.getDriver();
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle, "Title verification failed: ");
	}

	/**
	 * Validates given expected and actual URLs of the page.
	 * 
	 * @param expectedUrl
	 */
	public static void verifyUrl(String expectedUrl) {
		WebDriver driver = Browser.getDriver();
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl, "URl verification failed: ");
	}

}
