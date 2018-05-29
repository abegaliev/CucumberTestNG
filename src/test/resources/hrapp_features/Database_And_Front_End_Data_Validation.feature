Feature: Hr Application Database and UI data verification
  
  Validating data from UI vs Database

  Background: 
    Given User is on DepEmpPage


  Scenario: Department data UI and Database verification
    When User searchs fro department id 10
    Then User queries database with sql "SELECT department_name, manager_id, location_id from departments where department_id=10"
    Then UI data and Database data must match

  Scenario: First name and last name search by email-UI vs DB verification
    When User searchs for email "MWEISS" to see firstName and lastName
    Then User queries database with sql "SELECT first_name, manager_id, last_name From employees where email='MWEISS'"
    Then UI data and Database data must match

  Scenario Outline: First name and last name search by email-UI vs DB verification using Outline
    When User searchs for email "<email>" to see firstName and lastName
    Then User queries database with sql "SELECT first_name, manager_id, last_name From employees where email='<email>'"
    Then UI data and Database data must match

    Examples: 
      | email    |
      | MWEISS   |
      | JRUSSEL  |
      | SKING    |
      | NGREENBE |

 @database
  Scenario Outline: Verify Number of employees for departments-UI vs DB verification
    When User searchs for department id <department_id> and get number of employees
    Then User queries database with sql "SELECT COUNT(*) from employees WHERE department_id=<department_id>"
    Then UI data and Database data must match
    
    Examples:
    |department_id|
    |50|
    |60|
    |80|
    |30|
    
 
    
    
    
    
    
