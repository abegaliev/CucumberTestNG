/*
 * Copyright 2018, Almazbek Begaliev. All rights reserved.
 * Almazbek Begaliev's PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */

package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * This is customized utility class from author, which contains reusable methods
 * related to Selenium WebDriver automation.
 * 
 * @author Almazbek Begaliev
 */
public class Selenium {

	private static WebDriver driver = Browser.getDriver();

	/**
	 * implements: driver.manage().timeouts().implicitlyWait( Long arg1 , TimeUnit
	 * arg2);
	 * 
	 * @param seconds
	 */
	public static void implicitTimeouts(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Causes the currently executing thread line to sleep (temporarily cease
	 * execution) for the specified number of milliseconds.
	 * 
	 * @param seconds,
	 *            the length of time to sleep in seconds
	 */
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000); // converting to millis;
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	/**
	 * Returns List of handles(ID's of all opened windows/tabs by this driver).
	 * 
	 * @param driver;
	 * @return List<String>;
	 */
	public static List<String> getListOfHandles() {
		Set<String> setID = driver.getWindowHandles();
		List<String> list = new ArrayList<>(setID);
		return list;
	}

	/**
	 * Accepts a locator of web elements and returns text of those elements
	 * 
	 * @param driver
	 * @param locator
	 * 
	 * @return List<String>
	 */
	public static List<String> getTextOfElements(By by) {
		List<WebElement> elements = driver.findElements(by);
		List<String> list = new ArrayList<>();
		for (WebElement element : elements) {
			list.add(element.getText());
		}
		return list;
	}

	/**
	 * Accepts WebElements and returns text of those elements as List
	 * 
	 * @param List<WebElement>
	 * @return List<String>
	 */
	public static List<String> getTextOfElements(List<WebElement> elements) {
		List<String> list = new ArrayList<>();
		for (WebElement element : elements) {
			list.add(element.getText());
		}
		return list;
	}

	/**
	 * Validates given expected and actual titles of the page.
	 * 
	 * @param expectedTitle
	 */
	public static void verifyTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals("Title verification failed: ", expectedTitle, actualTitle);
	}

	/**
	 * Returns true if element is presented on the Web Page, false otherwise
	 * 
	 * @param locator
	 * @return
	 */
	public static boolean isPresented(By locator) {
		List<WebElement> elementList = driver.findElements(locator);
		int size = elementList.size();
		return (size > 0);
	}


	
	/**
	 * Uses WebDriverWait class and explicitly waits during the given timeout for
	 * element to be visible on the WebPage, returns the elements if it was found,
	 * null otherwise
	 * 
	 * @param locator
	 * @param timeOutInSec
	 * @return
	 */
	public static WebElement waitToBeVisible(By locator, int timeOutInSec) {
		System.out.println("Explicitly waiting: " + timeOutInSec + " seconds for element to be available");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	/**
	 * Uses WebDriverWait class and explicitly waits during the given timeout for
	 * element to be visible on the WebPage, returns the element if it was found,
	 * null otherwise
	 * 
	 * @param WebElement
	 * @param timeOutInSec
	 * @return WebElement
	 */
	public static WebElement waitToBeVisible(WebElement element, int timeOutInSec) {
		System.out.println("Explicitly waiting: " + timeOutInSec + " seconds for element to be available");

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
		element = wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	/**
	 * Uses WebDriverWait class and explicitly waits during the given timeout for
	 * element to be clickable, and returns that Element.
	 * 
	 * @param WebElement
	 * @param timeOutInSec
	 * @return WebElement
	 */
	public static WebElement waitToBeClickable(WebElement elem, int timeOutInSec) {
		System.out.println("Explicitly waiting: " + timeOutInSec + " seconds for element to be clickable");

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elem));
		return element;
	}

	/**
	 * Uses WebDriverWait class and explicitly waits during the given timeout for
	 * element to be clickable. Returns element if it was clickable.
	 * 
	 * @param locator
	 * @param timeOutInSec
	 * @return
	 */
	public static WebElement waitToBeClickable(By locator, int timeOutInSec) {
		System.out.println("Explicitly waiting: " + timeOutInSec + " seconds for element to be clickable");

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	/**
	 * Returns index of target number in the given List.
	 * 
	 * @param list
	 * @param target
	 * @return
	 */
	public static ArrayList<Integer> getIndex(List<Integer> list, int target) {
		ArrayList<Integer> ind = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == target) {
				ind.add(i + 1);
			}
		}
		return ind;
	}

}
