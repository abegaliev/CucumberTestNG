package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.Config;

public class APIDay5 {

	/*
	 * Given Content type and Accept type is Json When I post a new Employee with
	 * "234" id Then Status code is 201 And Response Json should contain Employee
	 * info When I send a Get request with "234" Then status code is 200 And
	 * employee Json Response Data should match the posted Json data
	 */
	@Test
	public void postEmployee() {
		String url = Config.getProperty("hrApp.BaseUrl") + "employees/";
		int randomID = new Random().nextInt(999999);

		Map reqEmployee = new HashMap();
		reqEmployee.put("employee_id", randomID);
		reqEmployee.put("first_name", "POSTNAME");
		reqEmployee.put("last_name", "POSTLNAME");
		reqEmployee.put("email", "EM" + randomID);
		reqEmployee.put("phone_number", "202.123.4567");
		reqEmployee.put("hire_date", "2018-04-24T07:25:00Z");
		reqEmployee.put("job_id", "IT_PROG");
		reqEmployee.put("salary", 7000);
		reqEmployee.put("commission_pct", null);
		reqEmployee.put("manager_id", null);
		reqEmployee.put("department_id", 90);

		// Given Content type and Accept type is Json
		Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(reqEmployee)
				.when().post(url);

		assertEquals(response.statusCode(), 201);

		Map postResEmployee = response.body().as(Map.class);
		// And Response Json should contain Employee info
		for (Object key : reqEmployee.keySet()) {
			Object actValue = postResEmployee.get(key);
			if (actValue != null && (actValue + "").endsWith("0") && (actValue + "").contains(".")) {
				int num = (int) ((Double) postResEmployee.get(key) / 1);
				assertEquals(num, reqEmployee.get(key));
			} else {
				assertEquals(postResEmployee.get(key), reqEmployee.get(key));
			}
		}
		/*
		 * When i send a GET request with "1100" id Then status code is 200 And employee
		 * JSON Response Data should match the posted JSON data
		 */

	}

}
