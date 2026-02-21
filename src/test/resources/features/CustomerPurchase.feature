@PassingTests
Feature: Purchase an item through Swag labs using login

  Background:

    Given the customer is on the "Swag Labs" page

  Scenario Outline: Customer purchases a single item
    Given I enter "<username>" and "<password>" on login
    And I am on the "Products" page
    And I select the "<product>" product
    When I select the add to cart button
    And click the basket icon
    And I checkout with "<firstname>" , "<lastname>" and "<postalcode>"
    Then the confirmation message "<message>" will be displayed

    Examples:

      | username      | password     | product             | firstname | lastname | postalcode | message                   |

      | standard_user | secret_sauce | Sauce Labs Backpack | secret    | sauce    | 12345      | Thank you for your order! |