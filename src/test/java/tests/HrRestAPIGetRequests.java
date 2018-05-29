package tests;

import static org.testng.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class HrRestAPIGetRequests {

	/*
	 * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/employees
	 * Then response status code should be 200
	 * 
	 */
	@Test
	public void simpleGet() {
		when().get("http://34.223.219.142:1212/ords/hr/employees")
		.then().statusCode(200);
		
	}
	
	
	/*
	 * When I send a GET request ot REST URL: http://34.223.219.142:1212/ords/hr/countries
	 * Then status code should be 200 
	 * Then I should see Json response
	 */
	@Test
	public void printResponse() {
		when().get("http://34.223.219.142:1212/ords/hr/countries")
		.then().statusCode(200);
		
		when().get("http://34.223.219.142:1212/ords/hr/countries")
		.body().prettyPrint();
	}
	
	
	/*
	 * When I send a GET request to REST API URL: http://34.223.219.142:1212/ords/hr/countries/US
	 * And Accept type is "application/json"
	 * Then response status code should be 200
	 */
	@Test
	public void getWithHeaders() {
		with().accept(ContentType.JSON)
		.when().get("http://34.223.219.142:1212/ords/hr/countries/US")
		.then().statusCode(200);
	
	}
	
	
	/*
	 * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/employees/1234
	 * And Accept type is "application/json"
	 * Then status code is 404
	 * And Response body error message is "Not Found"
	 */
	@Test
	public void getNegativeStatusCode() {
//		with().accept(ContentType.JSON)
//		.when().get("http://34.223.219.142:1212/ords/hr/employees/1234")
//		.then().statusCode(404);
		
		Response response = when().get("http://34.223.219.142:1212/ords/hr/employees/1234");
		assertEquals(response.statusCode(), 404);
		assertTrue(response.asString().contains("Not Found"), "Verification Failed:");

	}
	
	/*
	 * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/employees/101
	 * And Accept type is "json"
	 * Then status code is 200
	 * And Response content should be json
	 */
	@Test
	public void verifyContentWithAssertThat() {
		String url = "http://34.223.219.142:1212/ords/hr/employees/101";
		
		given().accept(ContentType.JSON)
		.when().get(url)
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON);
		
	}
	
	/*
	 * Given Accept type is Json
	 * When I send a request to REST API Url: "http://34.223.219.142:1212/ords/hr/employees/101"
	 * Then status code should be 200
	 * And Response content should be Json
	 * And First name should be Steven
	 */
	@Test
	public void verifyFirstName() throws URISyntaxException {
		URI uri = new URI("http://34.223.219.142:1212/ords/hr/employees/100");

		 given().accept(ContentType.JSON)
		.get(uri)
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("first_name", Matchers.equalTo("Steven"))
		.and().assertThat().body("last_name", equalTo("King"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
