package utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class FluentUtil {

	public static WebElement getElementWithWait(WebDriver driver, final By locator, int duration, int frequency) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(duration))
				.pollingEvery(Duration.ofSeconds(frequency)).ignoring(NoSuchElementException.class);

		WebElement message = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {

				return driver.findElement(locator);
			}
		});
		return message;
	}

}
