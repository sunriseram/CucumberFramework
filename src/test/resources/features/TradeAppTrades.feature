@Regression
Feature: As a user, I am able to perform Trade app functions
  I want to add, delete and update trades

  @AddTrade 
  Scenario: As a user, I want to add a trade
  Given I am on the Trade App log in page
  When I enter username "helil" password "SuperHelil123!"
  And I click on Trade login button
  Then I should be on Trade homepage
  When I click on Add Trade button
  Then I should be on Save Trade page
  When I select "Buy to Open" and I enter symbol "MU" entryDate "04/04/2021" entryPrice "10.0" exitDate "01/11/2022" exitPrice "18"
  And I click Save button
  Then The trade is displayed on the trade table
  And The data is deleted on the Database
  
  
  @AddTradeWithDataTable @SmokeTests
  Scenario: As a user, I want to add a trade
  Given I am on the Trade App log in page
  When I enter username "helil" password "SuperHelil123!"
  And I click on Trade login button
  Then I should be on Trade homepage
  When I click on Add Trade button
  Then I should be on Save Trade page
  When I enter the following data
  |Buy to Open|VHDA|04/04/2021|10.0|01/11/2022|18|
  And I click Save button
  Then The trade is displayed on the trade table
  And The data is deleted on the Database
  
  
  @AddTradeAndValidate @SmokeTests
  Scenario: As a user, I want to add a trade
  Given I am on the Trade App log in page
  When I enter username "helil" password "SuperHelil123!"
  And I click on Trade login button
  Then I should be on Trade homepage
  When I click on Add Trade button
  Then I should be on Save Trade page
  When I enter the following data
  |Buy to Open|VHDA|04/04/2021|10.0|01/11/2022|18.0|
  And I click Save button
  Then The trade is displayed on the trade table
  And The trade data resides in data base correctly
  And The data is deleted on the Database
  
  @InsertDB
  Scenario: As a user, I am able to insert a trade to Database
  Given I executed Insert query with the following data to Database
  |'890'|'1'|'0'|'HOOD'|'2021-10-10'|'50.0'|'2022-02-11'|'33.0'|'0'|
  Given I am on the Trade App log in page
  When I enter username "helil" password "SuperHelil123!"
  And I click on Trade login button
  Then I should be on Trade homepage
  When I search the "HOOD"
  And I click the Search button
  Then The trade input displayed on the trade table
  And The data is deleted on the Database
  The trade is displayed on the trade table