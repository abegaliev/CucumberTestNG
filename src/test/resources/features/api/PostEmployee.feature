
Feature: Post Employee by API

  Scenario: Post and employee method test
    Given Content type and Accept type is Json
    When I post a new Employee with "random" id
    Then Status code is 201
    And Response Json should contain Employee info
    When I send a Get request with "random"
  	Then Status code is 200
    And employee Json Response Data should match the posted Json data

   @API
   Scenario: Post an Employee and Verify in UI
    Given Content type and Accept type is Json
    When I post a new Employee with "random" id
    Then Status code is 200
    And Response Json should contain Employee info
    And I search for Employee with "random" id
    Then UI search results must match API post employee id
    		