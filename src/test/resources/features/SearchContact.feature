Feature: Contact search Functionality

  @search @dev
  Scenario: Validating the contact information in search funtionality
  
    Given I logged into suiteCRM
    When I search for "John Doe"
    Then link for user "John Doe" should be displayed
    Then Log out