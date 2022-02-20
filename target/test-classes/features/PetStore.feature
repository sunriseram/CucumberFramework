Feature: As a user I am able to perform HTTP request
         I want to perform GET, POST, PUT and DELETE 
         requests on Pet Store API.

@CreateAPet   
Scenario: I am able to create a Pet using Post request
When I am able to perform Post request to create a pet with valid request body


@PostToAddPet
  Scenario: As a user, I should be able to perform POST request to add new pet to store
    Given I have the POST request URL and valid request body
    When I perform POST request to URL with request body
    Then Response status code should be 200
    And Content type is "application/json"
    And Response body match the request body

  @invalidPostRequest
  Scenario: As a user, I should not be able to perform POST request to invalid data structure
    Given I have the POST request URL and invalid request body
    When I perform POST request to URL with request body
    Then Response status code should be 500 invalid input
    And Content type is "application/json"
    And message should be "something bad happened"

  @getRequestByStatus
  Scenario Outline: Find a pet by status
    Given valid endpoint exist
    When I send a GET request by status "<status>" to valid endpoint
    Then Response status code should be 200
    And Content type should be "application/json"

    Examples: 
      | status    |
      | available |
      | pending   |
      | sold      |

  @invalid_URL_Get
  Scenario: Find a pet by invalid url using status available
    Given Invalid endpoint exist
    When I send a GET request by status "available" to invalid endpoint
    Then Response status code should be 404
    And Content type should be "application/json"

  @getByPetID
  Scenario: As a user, I should be able to perform GET request to find a pet by id
    Given I have the GET request URL
    When I perform GET request to URL with pet id 6622456
    Then Response status code should be 200
    And Content type should be "application/json"
    And Pet id is 6622456, pet name is "doggie" status is "available"
    And Category id is 22232, category name is "Booboo"
    And Tags id is 22232, and tags name is "dog"

  @getAPetByInvalidID
  Scenario: As a user, I should be able to perform GET request to find a pet by invalid id
    Given I have the GET request URL
    When I perform GET request to URL with invalid id 66224
    Then Response status code should be 404
    And Content type should be "application/json"
    And message should be "Pet not found"

  @deleteByID
  Scenario: As a user, I am able to delete a pet
    Given I have the Delete request URL
    When I perform DELETE request to URL with valid id 662
    Then Response status code should be 200
    And Content type should be "application/json"
    And message should be "662"

  @updateAPet
  Scenario: As a user, I am able to update a pet
    Given I have the Update request URL
    When I perform UPDATE request to update Name to existing Pet
    Then Response status code should be 200
    And Content type should be "application/json"
    And Id is 667788 and is "Meow"