Feature: Add product to cart on SauceDemo
  Background:
    Given User is logged in to SauceDemo

  @web @positive
  Scenario: Add an existing product to cart
    When User adds the first product to the cart
    Then Cart badge shows 1 item

  @web @negative
  Scenario: Attempt to checkout with empty cart
    When User clicks the cart icon with empty cart
    And User proceeds to checkout
    Then User sees error message for empty cart
