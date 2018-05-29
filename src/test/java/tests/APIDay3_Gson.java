package tests;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay3_Gson {
	
	public void testWithJsonToHashMap() {
		Response response = given().accept(ContentType.JSON) // header
				.when().get("http://34.223.219.142:1212/ords/hr/employees/120");

		Map<String, String> map = response.as(HashMap.class);

		System.out.println(map.keySet());
		System.out.println(map.get("employee_id"));
	    //assertEquals(map.get("employee_id"), 120);
		
	}

	
	@Test
	public void getListOfMaps() {
		Response response = given().accept(ContentType.JSON) // header
				.when().get("http://34.223.219.142:1212/ords/hr/departments");

		// List<Map<String, String>> listOfMaps = response.as(ArrayList.class);
		// System.out.println(listOfMaps.get(0).get("employee_id"));

		List<Map> listOfMaps = response.jsonPath().getList("items", Map.class);

		System.out.println(listOfMaps);
		System.out.println(listOfMaps.size());
		
	}
	
	

}
