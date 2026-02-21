@PassingTests
Feature: Logout out of the Swag Labs page

  Background:

    Given the customer is on the "Swag Labs" page

  Scenario: Customer logs out through product page
    Given I enter "standard_user" and "secret_sauce" on login
    And I am on the "Products" page
    When I click the logout button
    Then the customer is on the login page