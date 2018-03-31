package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * Customized class by author, which helps with configuration of the framework
 * by reading properties file.
 *
 * @author Almazbek Begaliev
 *
 */
public final class Config {

	private static Properties properties;
	
	static {
		final String fileName = "./src/test/resources/testData/configuration.properties";					//file path
		try {
			FileInputStream input = new FileInputStream(fileName);
			properties = new Properties();
			properties.load(input);
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName );

		} catch (IOException e) {
			System.out.println("Exception while reading the file: " + fileName );
		}
	}
	
	/**
	 * Method returns a value of the given key from properties file. 
	 * 
	 * @param String key
	 * @return String value of the key
	 */
	public static String getProperty(String key) {
		String value = properties.getProperty(key);
		return value;
	}

}
