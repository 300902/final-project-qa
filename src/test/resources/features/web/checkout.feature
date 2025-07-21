Feature: Checkout flow on SauceDemo
  As a user
  I want to complete a purchase
  So that I can verify end-to-end checkout process

  @web @positive
  Scenario: Full checkout process and logout
    Given I am on the SauceDemo login page
    And I have valid username "standard_user" and password "secret_sauce"
    When I login with valid credentials
    And I open the menu
    Then the Logout link should be visible
    And I should see the page title "Products"

    When I navigate to the first product detail page
    Then I should see the product name on detail page

    When I add the product to the cart
    Then I should see a "Remove" button for that product

    When I go to the cart page
    Then I should see the product "Sauce Labs Backpack" in the cart

    When I click the checkout button
    Then I should be on the checkout information page

    When I enter first name "Jan" and last name "Novak" and postal code "10400"
    And I continue to the overview page
    Then I should be on the checkout overview page
    And I should see the product "Sauce Labs Backpack" in overview

    When I logout from the application
    Then I should be on the login page
