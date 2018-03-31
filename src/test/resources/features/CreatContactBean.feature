Feature: Creation of Contact

Background: 
	Then dsfdf
# Runs Before each scenario

@run
Scenario Outline: Creat a Contact
    Given I logged into suiteCRM
    And I have created a new contact
      | firstName | lastName | cellPhone    | title       |
      | Michael   | Jackson  | 888 989 3424 | Dancer      |
      | Michael   | Tyson    | 999 555 4444 | Boks King   |
      | Muhammed  | Ali      | 111 111 1111 | Boks legend |
    Then Log out
    
    Examples:
    
    |||
    |||