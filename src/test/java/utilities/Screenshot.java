package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

public final class Screenshot {

	private static WebDriver driver = Browser.getDriver();

	/**
	 * Takes a screenshot using FilesUtil(custom class). File sourceFile =
	 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 */
	public static String takeScreenshot() {
		String fileName = "screenshot" + LocalDateTime.now() + ".png";
		String directory = "//Users//almazbekbegaliev//Desktop//";
		String fullPath = directory + fileName;
		
		File sourceFile = null;
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;

		try {
			
			//byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			// scenario.embed(screenshot, "image/png");
			sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			sourceChannel = new FileInputStream(sourceFile).getChannel();
			destChannel = new FileOutputStream(fullPath).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
			
			// Alternative for Copying files.
			// FileUtils.copyFile(sourceFile, new File(directory + fileName));
		} catch (Exception e) {
			
			System.out.println("Exception while taking a screenshot: " + e);
			
		} finally {
			
			try {
				sourceChannel.close();
				destChannel.close();
			} catch (IOException e) {
				System.out.println("Exception while copying the file: "+ e);
			}
		}
		return fullPath;
	}
	
	public static void getScreenShotCucumber(Scenario scenario, WebDriver driver) {
		Date currentDate = new Date();
		String screenShotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
		if (scenario.isFailed()) {
			File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenShotFile, new File("src/test/java/screenshot/" + screenShotFileName + ".png"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = Num.getRandomInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

}

