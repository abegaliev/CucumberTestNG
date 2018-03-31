Feature: Note on Dashboard
@now
  Scenario: Post a not on Dashboard
    Given I logged into suiteCRM
    When I post "Hello World, I am doing great"
    Then Post should be displayed
    Then Log out
