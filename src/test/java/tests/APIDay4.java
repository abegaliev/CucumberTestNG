package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Config;

public class APIDay4 {

	@Test
	public void checkRegionsAPI() {
		Response response = given().accept(ContentType.JSON).and().param("limit", 100)
				.get("http://34.223.219.142:1212/ords/hr/regions");
		assertEquals(response.statusCode(), 200);

		JsonPath jPath = response.jsonPath();

		// List<Map> listOfMaps = response.jsonPath().getList("items", Map.class);
		List<Map> listOfMaps = jPath.getList("items", Map.class);

		assertEquals(listOfMaps.size(), 4);
		assertEquals(listOfMaps.get(0).get("region_name"), "Europe");

	}

	@Test
	public void testWarmUpVer2() {
		String baseUrl = Config.getProperty("hrapp.baseRestUrl");
		Response response = given().accept(ContentType.JSON).and().params("limit", 10).when().get(baseUrl+"/regions");
		List<String> regions = new ArrayList<>();
		JsonPath json = response.jsonPath();
		Map<Integer, String> expectedValues = new HashMap<>();
		expectedValues.put(1, "Europe");
		expectedValues.put(2, "Americas");
		expectedValues.put(3, "Asia");
		expectedValues.put(4, "Middle East and Africa");
		assertEquals(response.statusCode(), 200);
		for (int i = 0; i < 4; i++) {
			assertEquals(json.getInt("items[" + i + "].region_id"), i + 1);
			assertEquals(json.getString("items[" + i + "].region_name"), expectedValues.get(i + 1));
		}
	}

	@Test
	public void testRegions2() {
		String baseUrl = Config.getProperty("hrapp.baseRestUrl");
		Response response = given().accept(ContentType.JSON).and().params("limit", 10).when().get(baseUrl + "/regions");
		assertEquals(response.statusCode(), 200);
		JsonPath json = response.jsonPath();
		List<Map> regions = json.getList("items", Map.class);
		Map<Integer, String> expectedRegions = new HashMap<>();
		expectedRegions.put(1, "Europe");
		expectedRegions.put(2, "Americas");
		expectedRegions.put(3, "Asia");
		expectedRegions.put(4, "Middle East and Africa");
		for (int i = 0; i < regions.size(); i++) {
			assertEquals(regions.get(i).get("region_id"), i + 1);
			assertEquals(regions.get(i).get("region_name"), expectedRegions.get(i + 1));
		}
	}

}
