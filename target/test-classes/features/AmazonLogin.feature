@Regression
@tag
Feature: Amazon Login
  I want to be able to login with valid credentials.
  I sould not be logged in with invalid credentials.

  @amazonLogin 
  Scenario: User should be directed to log in page
  Given I am on amazon home page
  And The sign in button displays
  When I click on the sign in button
  Then I should be directed to log in page
   
  @amazonLogin @amazonInvalidUser 
  Scenario: User should not be directed to password field when invalid email is given 
  Given I am on the login page
  When I enter invalid email "jon.dow@gmail.com"
  And I click the continue button
  Then I should get error message says "We cannot find an account with that email address" 
  And I should still in the log in page
   
   
   