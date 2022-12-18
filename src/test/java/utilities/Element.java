/*
 * Copyright 2018, Almazbek Begaliev. All rights reserved.
 * Almazbek Begaliev's PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */

package utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/**
 * This is customized utility class, which contains reusable methods
 * related to Selenium WebDriver automation.
 * 
 * @author Almazbek Begaliev
 */
public class Element {

	
	/**
	 * Accepts a locator of web elements and returns text of those elements
	 * 
	 * @param By locator
	 * 
	 * @return A list of texts of WebElements
	 */
	public static List<String> getTextOfElements(By by) {
		WebDriver driver = Browser.getDriver();
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
	 * @param List of WebElements
	 * @return A list of texts of WebElements
	 */
	public static List<String> getTextOfElements(List<WebElement> elements) {
		List<String> list = new ArrayList<>();
		for (WebElement element : elements) {
			list.add(element.getText());
		}
		return list;
	}


	/**
	 * Returns true if element is presented on the Web Page, false otherwise
	 * 
	 * @param locator
	 * @return true of false
	 */
	public static boolean isPresented(By locator) {
		WebDriver driver = Browser.getDriver();
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
	 * @param time    in seconds
	 * @return element
	 */
	public static WebElement waitToBeVisible(By locator, int timeInSec) {
		WebDriver driver = Browser.getDriver();
		System.out.println("Explicitly waiting: " + timeInSec + " seconds for element to be available");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	
	/**
	 * Uses WebDriverWait class and explicitly waits during the given timeout for
	 * element to be visible on the WebPage, returns the element if it was found, null otherwise
	 * 
	 * @param WebElement
	 * @param timeInSec
	 * @return element
	 */
	public static WebElement waitToBeVisible(WebElement element, int timeInSec) {
		WebDriver driver = Browser.getDriver();
		System.out.println("Explicitly waiting: " + timeInSec + " seconds for element to be available");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
		element = wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	

	/**
	 * Uses WebDriverWait class and explicitly waits during the given timeout for
	 * element to be clickable. Returns element if it was clickable.
	 * 
	 * @param locator
	 * @param timeInSec
	 * @return element
	 */
	public static WebElement waitToBeClickable(By locator, int timeInSec) {
		WebDriver driver = Browser.getDriver();
		System.out.println("Explicitly waiting: " + timeInSec + " seconds for element to be clickable");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}
	

	/**
	 * Uses WebDriverWait class and explicitly waits during the given timeout for
	 * element to be clickable, and returns that Element.
	 * 
	 * @param WebElement
	 * @param timeInSec
	 * @return element
	 */
	public static WebElement waitToBeClickable(WebElement element, int timeInSec) {
		WebDriver driver = Browser.getDriver();
		System.out.println("Explicitly waiting: " + timeInSec + " seconds for element to be clickable");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
		WebElement elemen = wait.until(ExpectedConditions.elementToBeClickable(element));
		return elemen;
	}
	
	/**
	 * Uses a FluentWait class and waits for a WebElement to be available during
	 * giving timeout and returns that WebElement if found, null otherwise
	 * 
	 * @param locator
	 * @param timeInSec
	 * @return WebElement
	 */
	public static WebElement fluentWait(final By locator, int timeInSec) {
		WebDriver driver = Browser.getDriver();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeInSec))
				.ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	
	
	/**
	 * Returns index of target number in the given List.
	 * 
	 * @param list
	 * @param target
	 * @return index of target number, -1 if not found
	 */
	public static int getIndex(List<Integer> list, int target) {
		int ind = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == target) {
				ind = i;
			}
		}
		return ind;
	}
	
	
	
	
	
	
	
	

}
