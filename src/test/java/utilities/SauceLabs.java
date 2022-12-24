package utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabs {

	private static WebDriver driver;

	// https://wiki.saucelabs.com/display/DOCS/Example+Selenium+Scripts+for+Automated+Website+Tests
	// https://wiki.saucelabs.com/display/DOCS/Java+Test+Setup+Example

	public static final String USERNAME = Config.getProperty("souceLabs.username");
	public static final String ACCESS_KEY = Config.getProperty("souceLabs.acceskey");
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	public static final String browserName = Config.getProperty("remote.browser");
	public static final String browserVersion = Config.getProperty("remote.version");
	public static final Platform platform = Platform.fromString(Config.getProperty("remote.platform"));

	public static WebDriver setUp() {
		DesiredCapabilities caps;

//		switch (Config.getProperty("remote.browser")) {
//		default:
//		case "chrome":
//			caps = DesiredCapabilities.chrome();
//			break;
//		case "firefox":
//			caps = DesiredCapabilities.firefox();
//			break;
//		case "safari":
//			caps = DesiredCapabilities.safari();
//			break;
//		case "ie":
//			caps = DesiredCapabilities.internetExplorer();
//			break;
//
//		}
		caps = new DesiredCapabilities(browserName, browserVersion, platform);
		
		caps.setCapability("name", "Regression suite");
		caps.setCapability("platform", Config.getProperty("remote.platform"));
		caps.setCapability("version", Config.getProperty("remote.version"));
		
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return driver;
	}
	
	
	
	
	
	
	

}