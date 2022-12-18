Feature: User Interface for SuiteCRM

User Story US001
@hi @dev
Scenario: CRM Name and Modules 
	Given  I logged into suiteCRM
	Then  CRM name should be SuiteCRM 
	And  All modules should be displayed
	Then Log out