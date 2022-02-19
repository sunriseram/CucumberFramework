@Regression @DemoQATests
Feature: DemoQA is a good website
  Allows us to practice our skills with test automation

  @RadioTests
  Scenario Outline: User is able to select a radio button
    Given I am on the radio button webpage "https://demoqa.com/radio-button"
    When I select "<radioOptions>"
    Then A text "<textResults>" displays

    Examples: 
      | radioOptions | textResults                  |
      | Yes          | You have selected Yes        |
      | Impressive   | You have selected Impressive |

  @DemoAlert1
  Scenario: I am able to handle Alert
    Given I am on the Alerts page "https://demoqa.com/alerts"
    When I click on AlertButton element
    Then I am able to accept the Alert @DemoAlert1   