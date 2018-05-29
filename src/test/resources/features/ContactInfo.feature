Feature: Contact Information

@contact
Scenario:  Acceptance Criteria
        Given I logged into suiteCRM
        When I search for "John Doe"
        And  I open contact "John Doe"
        Then contact name should be "JOHN DOE"
        And  contact email should be "johnDoe@example.org"
        Then Log out