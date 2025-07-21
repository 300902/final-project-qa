Feature: SauceDemo Website Login
  As a user of SauceDemo website
  I want to login to my account
  So that I can access personalized features

  Background:
    Given I am on the SauceDemo login page

  @web @positive
  Scenario: Successful login with valid credentials
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be on the Products page
    And I should see a title "Products"

  @web @negative
  Scenario: Failed login with invalid username
    When I enter username "invalid_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see login error message
    And I should still be on the login page

  @web @negative
  Scenario: Failed login with invalid password
    When I enter username "standard_user"
    And I enter password "wrong_password"
    And I click the login button
    Then I should see login error message
    And I should still be on the login page

  @web @negative
  Scenario: Failed login with empty credentials
    When I clear username field
    And I clear password field
    And I click the login button
    Then I should see validation error message