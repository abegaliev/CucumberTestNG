package utilities;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DropDown {

	private Select select;
	
	public DropDown(WebElement dropDown) {
		select = new Select(dropDown);
	}
	
	public void chooseOptions(String opt) {
		select.selectByVisibleText(opt);
	}
	
	public void verifyDefaultOption(String expectedValue) {
		Assert.assertEquals(select.getFirstSelectedOption().getText(), expectedValue);
	}
	
	public void verifyChosenOption(String value) {
		select.selectByVisibleText(value);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), value);
	}
	
	public void verifyRandomOpIndex() {
		String firstSelectedOp = select.getFirstSelectedOption().getText();
		select.selectByIndex(Num.getRandomInt(select.getOptions().size()-1));
		Assert.assertNotEquals(select.getFirstSelectedOption().getText(), firstSelectedOp);
	}
	
	
	
	
	
	
	
	
}
