Feature: Attempted login with invalid credentials

  Background:

    Given the customer is on the "Swag Labs" page

  @expected-to-fail
  Scenario Outline: User enters invalid username or password but expects successful login
    When I enter "<username>" and "<password>" on login
    Then I am on the "Products" page

    Examples:

      | username         | password        |

      | standard_ovsaaer | secret_sasaspre |
