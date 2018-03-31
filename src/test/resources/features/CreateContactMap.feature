Feature: Creating Contact

  @map
  Scenario: Creat a Contact
    Given I logged into suiteCRM
    And I create a new Contact
      | firstName | Sara  |
      | lastName  | Clain |

  @outline
  Scenario Outline: Creat a Contact
    Given I logged into suiteCRM
    And I create a new Contact
      | firstName   | <first name>   |
      | lastName    | <last name>    |
      | cellPhone   | <cell phone>   |
      | officePhone | <office Phone> |
    Then Log out

    Examples: 
      | first name | last name | cell phone   | office phone |
      | Michael    | Jackson   | 888 989 3424 | 474 543 4334 |
      | Michael    | Tyson     | 999 555 4444 | 333 444 5555 |
      | Muhammed   | Ali       | 111 111 1111 | 222 222 2222 |
