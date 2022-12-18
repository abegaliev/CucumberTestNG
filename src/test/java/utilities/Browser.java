package utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;

public class Browser {

	private static WebDriver driver;

	/**
	 * Sets up the WebDriver and returns it, if WebDriver was already set up before
	 * returns same instance. Default implicit wait is 10 seconds
	 * 
	 * @return driver;
	 */

	public static final WebDriver getDriver() {

		if (driver == null) { // || ((RemoteWebDriver) driver).getSessionId() == null) {

			String browser = null;
			try {
				browser = Config.getProperty("browser").toLowerCase();
			} catch (Exception e) {
				System.out.println("\n===========You didn't specify BROWSER type:===========");
				System.out.println("=============Default WebDriver is a CHROME==============");
				System.out.println("==================Check properties file=================");
			}

			switch (browser) {

			default:
			case "chrome":

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case "ied":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;

			case "safari":
				WebDriverManager.iedriver().setup();
				driver = new SafariDriver();
				break;

			case "opera":
				WebDriverManager.operadriver().setup();
				driver = new OperaDriverManager().getWebDriver();
				break;
				
			case "remote":
				driver = SauceLabs.setUp();
				break;
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}
		return driver;
	}

	/**
	 * Implements:
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut);
	 * 
	 * @param seconds
	 */
	public static void implicitTimeouts(int seconds) {
		WebDriver driver = Browser.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	/**
	 * Returns List of handles(ID's of all opened windows/tabs by this driver).
	 * 
	 * @param driver;
	 * @return list of handles
	 */
	public static List<String> getListOfHandles() {
		WebDriver driver = Browser.getDriver();
		Set<String> setID = driver.getWindowHandles();
		List<String> list = new ArrayList<>(setID);
		return list;
	}

	/**
	 * Causes the currently executing thread line to sleep (temporarily cease
	 * execution) for the specified number of milliseconds.
	 * 
	 * @param seconds,
	 *            the duration of time to sleep in seconds
	 */
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000); // converting to milliseconds;
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	/**
	 * Closes the current window if the driver instance is not null, quits the
	 * browser if it's the last window currently open.
	 */
	public static void close() {
		if (driver != null)
			driver.close();
	}

	/**
	 * Quits the WebDriver.
	 */
	public static void quit() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
