package pojo;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class RegionResponse {

	@SerializedName("region_id")
	private Integer region_id;
	
	@SerializedName("region_name")
	private String region_name;
	
	@SerializedName("links")
	private ArrayList<Map<String, String>> links;
	
	
	public ArrayList<Map<String, String>> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Map<String, String>> links) {
		this.links = links;
	}

	public Integer getRegion_id() {
		return region_id;
	}
	
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}
	
	public String getRegion_name() {
		return region_name;
	}
	
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	
	
	
	
	
}
