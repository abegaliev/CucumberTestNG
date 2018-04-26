package stepDefinitions.stepDefsApi;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Country;
import pojo.Region;
import pojo.RegionResponse;
import utilities.Config;
import utilities.Num;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PostEmployee_stepDefs {

	RequestSpecification request;
	Response response;
	String url = Config.getProperty("hrApp.BaseUrl");
	Map reqEmp;
	Map respEmp;
	int empId;
	
	@Given("^Content type and Accept type is Json$")
	public void content_type_and_Accept_type_is_Json() throws Exception {
		request = given().accept(ContentType.JSON)
					  .and().contentType(ContentType.JSON);
	}

	@When("^I post a new Employee with \"([^\"]*)\" id$")
	public void i_post_a_new_Employee_with_id(String id) throws Exception {
		if(id.equals("random")) {
			empId = Num.getRandomInt(6);
		}else {
			empId = Integer.parseInt(id);
		}
		
		reqEmp = new HashMap();
		reqEmp.put("employee_id", empId);
		reqEmp.put("first_name", "POSTNAME");
		reqEmp.put("last_name", "POSTLNAME");
		reqEmp.put("email", "EM" + empId);
		reqEmp.put("phone_number", "202.123.4567");
		reqEmp.put("hire_date", "2018-04-24T07:25:00Z");
		reqEmp.put("job_id", "IT_PROG");
		reqEmp.put("salary", 7000);
		reqEmp.put("commission_pct", null);
		reqEmp.put("manager_id", null);
		reqEmp.put("department_id", 90);
		
		response = request.get(url+ ("employees/"+empId));
		
		respEmp = response.body().as(Map.class);
		
	}

	@Then("^Status code is (\\d+)$")
	public void status_code_is(int statusCode)  {
		assertEquals(response.statusCode(), statusCode);
	}

	@Then("^Response Json should contain Employee info$")
	public void response_Json_should_contain_Employee_info() {
		for (Object key : reqEmp.keySet()) {
			Object actValue = respEmp.get(key);
			if (actValue != null && (actValue+"").endsWith("0") && (actValue+"").contains(".") ) {
				int  num =(int) ((Double)respEmp.get(key) /1);
				assertEquals(num,  reqEmp.get(key));
			} else {
				assertEquals(respEmp.get(key), reqEmp.get(key));
			}
		}
	}

	@When("^I send a Get request with \"([^\"]*)\"$")
	public void i_send_a_Get_request_with(String id) {
		if(!id.equals("random")) {
			empId = Integer.parseInt(id);
		}
			
		response = request.when().get(url+("employees/"+ empId));
		respEmp = response.body().as(Map.class);
		
	}

	@Then("^employee Json Response Data should match the posted Json data$")
	public void employee_Json_Response_Data_should_match_the_posted_Json_data(){
		for (Object key : reqEmp.keySet()) {
			Object actValue = respEmp.get(key);
			if (actValue != null && (actValue+"").endsWith("0") && (actValue+"").contains(".") ) {
				int  num =(int) ((Double)respEmp.get(key) /1);
				assertEquals(num,  reqEmp.get(key));
			} else {
				assertEquals(respEmp.get(key), reqEmp.get(key));
			}
		}
	}
	
	
	
	

}
