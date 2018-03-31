package utilities;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class Screenshot {

	private static WebDriver driver = Browser.getDriver();

	/**
	 * Takes a screenshot using FilesUtil(custom class).
	 * File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 * @return string (FilePath)
	 */
	public static String takeScreenshot() {
		String fileName = "screenshot" + LocalDateTime.now() + ".png";
		String directory = "//Users//almazbekbegaliev//Desktop//TestScreenshots/";
		String filePath = directory + fileName;
		
		try {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FilesUtil.copyFile(sourceFile, new File(filePath));
			Selenium.sleep(1);
		} catch (Exception e) {
			System.out.println("Error while taking a screenshot: " + e);
		}
		
		return filePath;

	}


}
