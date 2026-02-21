@PassingTests
Feature: Attempted login with incorrect credentials

  Background:

    Given the customer is on the "Swag Labs" page

  Scenario Outline: User enter invalid username or password
    When I enter "<username>" and "<password>" on login
    Then an error message "<message>" will be displayed

    Examples:

      | username        | password       | message                                                                   |

      | standard_useddr | secret_saussce | Epic sadface: Username and password do not match any user in this service |