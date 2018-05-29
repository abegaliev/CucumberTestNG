package stepDefinitions.stepDefsApi;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.HRappSearchPage;
import pages.HrAppDeptEmpPage;
import utilities.Browser;
import utilities.Config;

public class API_PostAnEmployee {

	RequestSpecification request;
	int employeeId;
	Response response;
	String url = Config.getProperty("hrapp.BaseUrl") + "/employees/";
	Map reqEmployee;
	HRappSearchPage searchPage = new HRappSearchPage();
	HrAppDeptEmpPage deptEmpPage = new HrAppDeptEmpPage();

	Map uiEmployeeDataMap;

	@Then("^UI search results must match API post employee data$")
	public void ui_search_results_must_match_API_post_employee_data() {
	
		Browser.sleep(2);
		
		searchPage = new HRappSearchPage();
		
		uiEmployeeDataMap = new HashMap();
		uiEmployeeDataMap.put("employee_id", Integer.valueOf(searchPage.employeeId.getAttribute("value")));
		uiEmployeeDataMap.put("first_name", searchPage.firstName.getAttribute("value"));
		uiEmployeeDataMap.put("last_name", searchPage.lastName.getAttribute("value"));
		uiEmployeeDataMap.put("email", searchPage.email.getAttribute("value"));
		//uiEmployeeDataMap.put("hire_date", "2018-04-24T07:25:00Z");
		uiEmployeeDataMap.put("job_id", searchPage.jobId.getAttribute("value"));
		uiEmployeeDataMap.put("salary", Integer.valueOf(searchPage.salary.getAttribute("value")));
		uiEmployeeDataMap.put("department_id", Integer.valueOf(searchPage.departmentId.getText()));
		
		//compare the data against Json data used in POST api /  MAP
		for (Object key : uiEmployeeDataMap.keySet()) {
			System.out.println(uiEmployeeDataMap.get(key) + " <> " + reqEmployee.get(key));
			assertEquals(uiEmployeeDataMap.get(key), reqEmployee.get(key));
		}
	}
	
	@Then("^I search for Employee with \"([^\"]*)\" id$")
	public void i_search_for_Employee_with_id(String id) {

		if (!id.equals("random")) {
			employeeId = Integer.parseInt(id);
		}
		deptEmpPage.queryBtn.click();
		searchPage.empIdSearchField.clear();
		searchPage.empIdSearchField.sendKeys(String.valueOf(employeeId));
		searchPage.searchBtn.click();
	}

	@Given("^Content type and Accept type is Json$")
	public void content_type_and_Accept_type_is_Json() {
		request = given().accept(ContentType.JSON).and().contentType(ContentType.JSON);
	}
	
	
	@When("^I post a new Employee with \"([^\"]*)\" id$")
	public void i_post_a_new_Employee_with_id(String id) {
		if (id.equals("random")) {
			employeeId = new Random().nextInt(999999);
		} else {
			employeeId = Integer.parseInt(id);
		}

		reqEmployee = new HashMap();
		reqEmployee.put("employee_id", employeeId);
		reqEmployee.put("first_name", "POSTNAME");
		reqEmployee.put("last_name", "POSTLNAME");
		reqEmployee.put("email", "EM" + employeeId);
		reqEmployee.put("phone_number", "202.123.4567");
		reqEmployee.put("hire_date", "2018-04-24T07:25:00Z");
		reqEmployee.put("job_id", "IT_PROG");
		reqEmployee.put("salary", 7000);
		reqEmployee.put("commission_pct", null);
		reqEmployee.put("manager_id", null);
		reqEmployee.put("department_id", 90);

		response = request.body(reqEmployee).when().post(url);
	}

	@Then("^Status code is (\\d+)$")
	public void status_code_is(int statusCode) {
		assertEquals(response.statusCode(), statusCode);
	}

	@Then("^Response Json should contain Employee info$")
	public void response_Json_should_contain_Employee_info() {
		Map postResEmployee = response.body().as(Map.class);
		// And Response Json should contain Employee info
		for (Object key : reqEmployee.keySet()) {
			System.out.println(postResEmployee.get(key) + " <> " + reqEmployee.get(key));
			assertEquals(postResEmployee.get(key), reqEmployee.get(key));
		}
	}

	@When("^I send a GET request with \"([^\"]*)\" id$")
	public void i_send_a_GET_request_with_id(String id) {
		if (!id.equals("random")) {
			employeeId = Integer.parseInt(id);
		}
		response = given().accept(ContentType.JSON).when().get(url + employeeId);
	}

	@Then("^Employee JSON Response Data should match the posted JSON data$")
	public void employee_JSON_Response_Data_should_match_the_posted_JSON_data() {
		Map getResMap = response.body().as(Map.class);

		for (Object key : reqEmployee.keySet()) {
			System.out.println(key + ": " + reqEmployee.get(key) + "<>" + getResMap.get(key));
			assertEquals(getResMap.get(key), reqEmployee.get(key));
		}
	}
	
	public String convertDate(String dateStr) {
		dateStr = dateStr.substring(0, dateStr.indexOf("T"));
		String [] dateArr = dateStr.split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
		
		return date.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy"));
	}
	
	
	

}
