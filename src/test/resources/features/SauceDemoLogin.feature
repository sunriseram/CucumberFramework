
@Regression
Feature: Sauce Demo Login
         This feature focues on positive and nagetive 
         log in tests

  Background: 
    Given I am on the SauceDemo login page

  @SauceValidLogin @SmokeTests
  Scenario: Valid login test
    When I enter valid username "standard_user" password "secret_sauce"
    And I click on the login button
    Then I should be directed to inventory page
    And There should be 6 items in the page

  @SauceInvalidLogin
  Scenario: Invalid login test
    When I enter invalid username "standard_user" password "secret_invalid"
    And I click on the login button
    Then I should not be logged in
    And Error message should display "Epic sadface: Username and password do not match any user in this service"

  @SauceInvalidTests
  Scenario Outline: User is not able to log in with invalid credentials
    When I enter invalid username "<username>" password "<password>"
    And I click on the login button
    Then I should not be logged in
    And Error message should display "Epic sadface: Username and password do not match any user in this service"

    Examples: Credentials
      | username      | password       |
      | standard_user | secret_invalid |
      | standard      | standard===    |
      | standard123   | standard652    |

