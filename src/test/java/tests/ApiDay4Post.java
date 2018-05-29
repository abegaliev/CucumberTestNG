package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Country;
import pojo.Region;
import pojo.RegionResponse;
import utilities.Config;

public class ApiDay4Post {

	@Test
	public void postNewRegion() {
		String url = Config.getProperty("hrApp.BaseUrl") + "regions/";

		// String postJson = "{\"region_id\": 900, \"region_name\": \"Almazbek's
		// regions\"}";

		Map requestMap = new HashMap();
		requestMap.put("region_id", 5398);
		requestMap.put("region_name", "Almazbek's region");

		Response response = given().accept(ContentType.JSON).and()
						.contentType(ContentType.JSON).and().body(requestMap)
						.when().post(url);

		assertEquals(response.statusCode(), 201);

		Map responseMap = response.body().as(Map.class);

		assertEquals(responseMap.get("region_id"), requestMap.get("region_id"));
		assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));

	}

	@Test
	public void postUsingPojo() {
		String url = Config.getProperty("hrApp.BaseUrl") + "regions/";
		Region region = new Region();
		region.setRegion_id(269);
		region.setRegion_name("Almazbek's pojo");

		Response response = given().accept(ContentType.JSON).and()
				.contentType(ContentType.JSON).and().body(region)
				.when().post(url);
		
		assertEquals(response.statusCode(), 201);
		
		RegionResponse responseRegion = response.body().as(RegionResponse.class);
		
		assertEquals(region.getRegion_id(), responseRegion.getRegion_id());
		assertEquals(region.getRegion_name(), responseRegion.getRegion_name());
		
		
		
	}
	
	
	@Test
	public void postCountryByPojo() {
		String url = Config.getProperty("hrApp.BaseUrl") + "countries/";
		Country countryReq = new Country();
		countryReq.setCountry_id("AB");
		countryReq.setRegion_id(2);
		countryReq.setCountry_name("Kyrgyzstan");
		

		Response response = given().accept(ContentType.JSON).and()
				.contentType(ContentType.JSON).and().body(countryReq)
				.when().post(url);
		
		assertEquals(response.getStatusCode(), 201);
		
		Country countryRes = response.body().as(Country.class);
		
		assertEquals(countryReq.getCountry_id(), countryRes.getCountry_id());
		assertEquals(countryReq.getCountry_name(), countryRes.getCountry_name());
		assertEquals(countryReq.getRegion_id(), countryRes.getRegion_id());
		
	}

	
	
	
	
	
	
}
