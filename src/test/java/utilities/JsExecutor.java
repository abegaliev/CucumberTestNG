package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * Helps to execute JavaScript scripts, using JavascriptExecutor;
 * 
 * @author almazbekbegaliev
 *
 */
public class JsExecutor {

	private static final JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();

	/**
	 * Finds and returns WebElement using JavascriptExecutor
	 * 
	 * @param By locator
	 * @return WebElement
	 */
	public static WebElement getElement(By locator) {
		WebElement element = null;

		String locText = locator.toString();
		System.out.println(locText);
		int index = locText.indexOf(" ") + 1;
		locText = locText.substring(index);
		locText = "'" + locText + "'";

		if (locator instanceof ById) {
			element = (WebElement) js.executeScript("return document.getElementById(" + locText + ");");

		} else if (locator instanceof ByClassName) {
			List<WebElement> list = (List<WebElement>) js.executeScript("return document.getElementsByClassName(" + locText + ");");
			if (list.size() > 0)
				element = list.get(0);

		} else if (locator instanceof ByName) {
			List<WebElement> list = (List<WebElement>) js.executeScript("return document.getElementsByName(" + locText + ");");
			if (list.size() > 0)
				element = list.get(0);

		} else if (locator instanceof ByCssSelector) {
			element = (WebElement) js.executeScript("return document.querySelector(" + locText + ");");

//		} else if (locator instanceof ByXPath) {
//
//			String script = "return document.evaluate(" + locText + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;";
//			element = (WebElement) js.executeScript(script);
		}

		if (element == null)
			throw new NoSuchElementException(locator.toString());

		return element;
	}

	/**
	 * Clicks given WebElement.
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Highlights before clicking the given WebElement.
	 * 
	 * @param element
	 */
	public static void clicAndkHighlight(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Clicks given WebElement using JavascriptExecutor and catches any Exceptions
	 * 
	 * @param element
	 */
	public static void safeClick(WebElement element) {
		try {

			js.executeScript("arguments[0].click();", element);

		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document: " + e.getStackTrace());

		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM: " + e.getStackTrace());

		} catch (Exception e) {
			System.out.println("Unable to click on element: " + e.getStackTrace());
		}
	}

	/**
	 * Scrolls the window by given pixels, ==> Positive number, makes scroll down
	 * ==> Negative number, makes scroll up
	 * 
	 * @param pixels
	 */
	public static void scroll(int pixels) {
		js.executeScript("scroll(0," + pixels + ");");
	}

	/**
	 * Scrolls into view given WebElement
	 * 
	 * @param element
	 */
	public static void scrollToElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Highlights the given element on the WebPage;
	 * 
	 * @param element
	 */
	public static void highlight(WebElement element) {
		js.executeScript("arguments[0].style.border='3px dotted red'", element);

	}

	/**
	 * Returns text of the given WebElement
	 * 
	 * @param element
	 * @return String text of the element
	 */
	public static String getText(WebElement element) {
		String text = (String) (js.executeScript("return arguments[0].value;", element));
		if (text == null || text.isEmpty()) {
			text = (String) (js.executeScript("return arguments[0].innerHTML;", element));
		}
		return text;
	}

	/**
	 * Sends a keys to the WebElement, just like a sendkeys() method.
	 * 
	 * @param WebElement
	 * @param AttributeName
	 * @param Value
	 */
	public static void setText(WebElement element, String attributeName, String text) {
		// WrapsDriver wrappedElement = (WrapsDriver) element;
		// JavascriptExecutor driver =
		// (JavascriptExecutor)wrappedElement.getWrappedDriver();

		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attributeName, text);
	}

	/**
	 * Returns Title of the page
	 * 
	 * @return title
	 */
	public static String getTitle() {
		JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
		String title = js.executeScript("return document.title;").toString().trim();
		return title;
	}

	/**
	 * Returns Page Inner Text(All text on the page)
	 * 
	 * return pageText;
	 */
	public static String getInnerText() {
		String pageText = js.executeScript("return document.documentElement.innerText;").toString().trim();
		return pageText;
	}

	/**
	 * Generates Java Script Alert on the page.
	 * 
	 * @param message
	 */
	public static void generateAlert(String message) {
		js.executeScript("alert('" + message + "')");
	}

	/**
	 * Refreshes the page.
	 */
	public static void refreshPage() {
		js.executeScript("history.go(0);");
	}

	/**
	 * Refreshes the window.
	 */
	public static void refreshWindow() {
		js.executeScript("location.reload()");
	}

	/**
	 * Returns size of the Browser's Window
	 * 
	 * @return long[] size, [ height, width]
	 */
	public static long[] getWindowSize() {
		long[] size = new long[2];
		size[0] = (long) js.executeScript("return window.innerHeight;");
		size[1] = (long) js.executeScript("return window.innerWidth;");

		return size;
	}

	public static void scrollToButtom() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

}
