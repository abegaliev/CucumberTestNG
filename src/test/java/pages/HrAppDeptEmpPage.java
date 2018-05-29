package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Browser;
import utilities.Element;

public class HrAppDeptEmpPage {

	private WebDriver driver;

	public HrAppDeptEmpPage() {
		driver = Browser.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "pt1:ot1")
	public WebElement depId;

	@FindBy(id = "pt1:ot2")
	public WebElement depName;

	@FindBy(id = "pt1:ot3")
	public WebElement managerId;

	@FindBy(id = "pt1:ot4")
	public WebElement locationId;

	@FindBy(id = "pt1:cb3")
	public WebElement nextBtn;

	@FindBy(id = "pt1:r1:0:it1::content")
	public WebElement email;

	@FindBy(id = "pt1:r1:0:ot1")
	public WebElement firstName;

	@FindBy(id = "pt1:r1:0:ot2")
	public WebElement lastName;

	@FindBy(id = "pt1:r1:0:cb1")
	public WebElement findDepails;
	
	@FindBy(id = "pt1:pc1:_dchTbr::icon")
	public WebElement detach;

	@FindBy(css = "div[id='pt1:pc1:t1::db'] tr")
	public List<WebElement> employees;
	
	@FindBy(id = "pt1:cb6")
	public WebElement queryBtn;
	

	public void searchForDepartment(int deptId) {
		int currentDepId = Integer.parseInt(depId.getText().trim());

		while (currentDepId != deptId) {
			nextBtn.click();
			Element.waitToBeVisible(depId, 5);
			currentDepId = Integer.parseInt(depId.getText().trim());
		}

	}

}
