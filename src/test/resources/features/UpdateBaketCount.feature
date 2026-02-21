@PassingTests
Feature: Customer cart is being updated

  Background:

    Given the customer is on the "Swag Labs" page

  Scenario Outline: Customer adds an item to the basket
    Given I enter "<username>" and "<password>" on login
    And I am on the "Products" page
    When I select the "<product>" product
    And I select the add to cart button
    Then the basket is updated to "1"

    Examples:

      | username      | password     | product             |

      | standard_user | secret_sauce | Sauce Labs Backpack |