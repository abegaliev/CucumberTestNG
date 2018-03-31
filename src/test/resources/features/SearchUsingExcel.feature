Feature: Search contact functionality
  
  @Excel
  Scenario: Search for Contact with data from Excell Sheet
  Given I logged into suiteCRM
  When I search for Contact reading data from Excell sheet "./src/test/resources/testData/contacts.xlsx" "Sheet1"
  Then Log out