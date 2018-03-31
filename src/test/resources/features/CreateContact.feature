Feature: Contact creation

	As a user I should be able to create a contact.

  @create
  Scenario Outline: Title of your scenario outline
  	Given I logged into suiteCRM 
  	And  I go to create contact page
    Then I create new contact using "<prefix>" "<firstName>" "<lastName>" "<phoneNumber>"  "<title>"
    And I validate new created contact info "<prefix>" "<firstName>" "<lastName>" "<phoneNumber>"  "<title>"
    Then Log out
    
    Examples: 
 		|prefix| firstName  | lastName | phoneNumber  |  title  								 |
    |Mr.   | Donald     | Trump	   | 773 555 5555 | Make America Great again |
    |Dr.   | House		  | Gred     | 011 111 1111 | Brain										 |
    |Ms.	 |Zholdosh    | Oligarh  | 111 222 3333 | Money										 |