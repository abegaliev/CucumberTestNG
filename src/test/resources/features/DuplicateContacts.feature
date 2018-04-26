Feature: Contact Information
@duplicate
  Scenario: Merging duplicate contacts
    
    Given I logged into suiteCRM
    When I search for "John Doe"
    And I open contact "John Doe"
    And If duplicated contact "John Doe" exists
    And remove duplicates for this contact
    Then there should be only 1 "John Doe" in the contacts page
    Then Log out