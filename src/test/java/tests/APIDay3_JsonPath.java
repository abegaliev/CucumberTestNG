package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class APIDay3_JsonPath {

	/*
	 * Given Accept type is Json
	 * When I send a GET request to REST API Url: "http://34.223.219.142:1212/ords/hr/regions"
	 * Then status code should be 200
	 * And Response content should be Json
	 * And 4 regions should be returned
	 * And Americas is one of the region names
	 */
//	@Test()
	public void testItemsCountFromResponseBody() {
		given().accept(ContentType.JSON)
		.when().get("http://34.223.219.142:1212/ords/hr/regions")
		.then().assertThat().statusCode(200)
		.and().assertThat().contentType(ContentType.JSON)
		.and().assertThat().body("items.region_id", hasSize(4))
		.and().assertThat().body("items.region_name", hasItems("Americas", "Asia"));
	}
	
	
	/*
	 * Given Accept type is Json
	 * And Params are limit 100
	 * When I send a GET request to URL: "http://34.223.219.142:1212/ords/hr/employees"
	 * Then status code is 200
	 * And response content should be json
	 * And 100 employees data should be in json response body
	 */
//	@Test
	public void testResponseBody() {
		given().accept(ContentType.JSON)
		.and().param("limit", 100)
		.when().get("http://34.223.219.142:1212/ords/hr/employees")
		.then().assertThat().statusCode(200)
		.and().assertThat().contentType(ContentType.JSON)
		.and().assertThat().body("items.employee_id", hasSize(100));
		
	}
	
	
	/*
	 * Given Accept type is Json
	 * And Params are limit 100
	 * And Path param is 110
	 * When I send a GET request to URL: "http://34.223.219.142:1212/ords/hr/employees"
	 * Then status code is 200
	 * And response content should be json
	 * And 110 employees data should be in json response body
	 * And followin data should be returned:
	 * 			"employee_id": 110,
    			"first_name": "John",
    			"last_name": "Chen",
    			"email": "JCHEN",
	 */
//	@Test
	public void testWitchPathParameter() {
		given().accept(ContentType.JSON)
		.and().param("limit", 100)
		.and().pathParam("employee_id", 110)
		.when().get("http://34.223.219.142:1212/ords/hr/employees/{employee_id}")
		.then().assertThat().statusCode(200)
		.and().assertThat().contentType(ContentType.JSON)
		.and().assertThat().body("employee_id", equalTo(110),
								"last_name", equalTo("Chen"),
								"first_name", equalTo("John"),
								"email", equalTo("JCHEN"));
//		.and().assertThat().body("first_name", equalTo("John"))
//		.and().assertThat().body("last_name", equalTo("Chen"))
//		.and().assertThat().body("email", equalTo("JCHEN"));
		
	}
	
	/*
	Given Accept type is Json
	 * And Params are limit 100
	 * And Path param is 110
	 * When I send a GET request to URL: "http://34.223.219.142:1212/ords/hr/employees"
	 * Then status code is 200
	 * And response content should be json
	 * And 110 employees data should be in json response body
	 * And All employee is should be returned
	*/
	
//	@Test
	public void testWithJsonPath() {
		Map<String, Integer> rParamMap = new HashMap<>(); 
		rParamMap.put("limit", 100);
		
		Response response =  given().accept(ContentType.JSON)		//header
							.params(rParamMap)						// query param/request param
							.and().pathParam("employee_id", 177)	//path param
							.when().get("http://34.223.219.142:1212/ords/hr/employees/{employee_id}");
		
		JsonPath json = response.jsonPath();		// get json body and assign to jsonPath object
		
		System.out.println(json.getInt("employee_id"));
		System.out.println(json.getString("first_name"));
		System.out.println(json.getString("last_name"));
		System.out.println(json.getString("salary"));
		System.out.println(json.getString("links[0].href"));
		
		//Storing all href into a list of strings
		List<String> list = json.getList("links.href");
		
		System.out.println(list);
		
		
	}
	
	
	/*
	Given Accept type is Json
	 * And Params are limit 100
	 * And Path param is 110
	 * When I send a GET request to URL: "http://34.223.219.142:1212/ords/hr/employees"
	 * Then status code is 200
	 * And response content should be json
	 * And 110 employees data should be in json response body
	 * And All employee is should be returned
	*/
	@Test
	public void testJsonPathWithList() {
		Map<String, Integer> rParamMap = new HashMap<>(); 
		rParamMap.put("limit", 100);
		
		Response response =  given().accept(ContentType.JSON)		//header
							.params(rParamMap)						// query param/request param
							.when().get("http://34.223.219.142:1212/ords/hr/employees/");
		
		assertEquals(response.statusCode(), 200);
		
		JsonPath json = response.jsonPath();
//		JsonPath json = new JsonPath(new File(FilePath.json));
//		JsonPath json = new JsonPath(String); 					//String wich contains json
		
		List<String> list = json.getList("items.employee_id");
		assertEquals(list.size(), 100);
		
//		List<Map<Object, Object>> listMap = getListMap(json);
//		System.out.println(listMap);
//		System.out.println("WATCH=====>>>" +listMap.get(8).get("links"));
		
		//get all employee_id that are greater than 150
		List<Object> listOfEmpId = json.getList("items.findAll{it.employee_id >= 150}.employee_id");
		System.out.println(listOfEmpId);
		
		List<Object> listSalary = json.getList("items.findAll{it.salary > 15000}.salary");
		System.out.println(listSalary);
		
	}
	
	
	
	public List<Map<String, Object>> getListMap(JsonPath json){
		List <Object> list = json.getList("items");
		int size = list.size();
		
		List<Map<String, Object>> listMap = new ArrayList<>();
		
		for(int i = 0; i< size; i++ ) {
			listMap.add(json.getMap("items["+i+"]"));
		}
		
		return listMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
