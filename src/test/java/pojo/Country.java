package pojo;

import java.util.List;
import java.util.Map;

public class Country {

	private String country_id;
	private String country_name;
	private Integer region_id;
	private List<Map<String, String>> links;
	
	
	public List<Map<String, String>> getLinks() {
		return links;
	}
	public void setLinks(List<Map<String, String>> links) {
		this.links = links;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public Integer getRegion_id() {
		return region_id;
	}
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}
	
	
	
}
