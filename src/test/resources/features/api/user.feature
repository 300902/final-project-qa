Feature: User API Testing
  As an API consumer
  I want to perform CRUD operations on User endpoints
  So that I can manage user data effectively

  Background:
    Given the DummyAPI base URL is set

  @api @positive
  Scenario: Get user by valid ID - Positive Test
    Given I have a valid user ID "60d0fe4f5311236168a10a01"
    When I send a GET request to get user by ID
    Then the response status code should be 200
    And the response should contain user details

  @api @negative
  Scenario: Get user by invalid ID - Negative Test
    Given I have an invalid user ID "invalid123"
    When I send a GET request to get user by ID
    Then the response status code should be 404
    And the response should contain error message

  @api @positive
  Scenario: Create new user with valid payload - Positive Test
    Given I have a valid user payload
      | firstName | lastName | email                |
      | John      | Doe      | john.doe@example.com |
    When I send a POST request to create user
    Then the response status code should be 200
    And the response should contain the created user details

  @api @negative
  Scenario: Create user with invalid payload - Negative Test
    Given I have an invalid user payload
    When I send a POST request to create user
    Then the response status code should be 400
    And the response should contain validation error

  @api @negative
  Scenario: Create user with invalid email format - Negative Test
    When I create a new user with name "Test User" and email "invalid-email"
    Then the response status code should be 400
    And the response should contain validation error

  @api @negative
  Scenario: Update user with valid ID and payload - Negative Test
    # Use the user ID created in the previous scenario
    And I have a valid update payload
      | firstName | lastName |
      | Jane      | Smith    |
    When I send a PUT request to update user
    Then the response status code should be 400
    And the response should contain error message

  @api @negative
  Scenario: Update user with invalid ID - Negative Test
    Given I have an invalid user ID "invalid123"
    And I have a valid update payload
      | firstName | lastName |
      | Jane      | Smith    |
    When I send a PUT request to update user
    Then the response status code should be 400
    And the response should contain error message

  @api @negative
  Scenario: Delete user with valid ID - Negative Test
    # Use the user ID created in the previous scenario
    When I send a DELETE request to delete user
    Then the response status code should be 400
    And the response should contain error message

  @api @negative
  Scenario: Delete user with invalid ID - Negative Test
    Given I have an invalid user ID "invalid123"
    When I send a DELETE request to delete user
    Then the response status code should be 400
    And the response should contain error message

  @api @positive
  Scenario: Get all tags - Positive Test
    When I send a GET request to get all tags
    Then the response status code should be 200
    And the response should contain list of tags
