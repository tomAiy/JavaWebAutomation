@PassingTests
Feature: Verify product prices with CSV file

  Background:

    Given the customer is on the "Swag Labs" page

  Scenario: Valid product prices displayed
    Given I enter "standard_user" and "secret_sauce" on login
    When I am on the "Products" page
    Then I check that the price of product is price
