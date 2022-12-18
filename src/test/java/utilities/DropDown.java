package utilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

	private Select select;
	
	
	public DropDown(WebElement dropDown) {
		select = new Select(dropDown);
	}
	
	/**
	 * Verifies default option of the drop down using Assert class.
	 * 
	 * @param expectedValue
	 */
	public void verifyDefaultOption(String expectedValue) {
		assertEquals(select.getFirstSelectedOption().getText(), expectedValue);
	}
	
	public void verifyChosenOption(String text) {
		select.selectByVisibleText(text);
		assertEquals(select.getFirstSelectedOption().getText(), text);
	}
	
	public void verifyRandomOpByIndex() {
		String firstSelectedOp = select.getFirstSelectedOption().getText();
		select.selectByIndex(Num.getRandomInt(select.getOptions().size()));
		
		assertNotEquals(select.getFirstSelectedOption().getText(), firstSelectedOp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
