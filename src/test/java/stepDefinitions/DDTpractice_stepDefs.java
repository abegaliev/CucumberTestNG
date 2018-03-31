package stepDefinitions;

import java.util.List;

import cucumber.api.java.en.Then;

public class DDTpractice_stepDefs  {

	
	@Then("^I pass info$")
	public void i_pass_info(List<String> data) {
	   System.out.println(data);
	   System.out.println("hi");
	}
	
	
}
