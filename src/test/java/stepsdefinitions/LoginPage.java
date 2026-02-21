package stepsdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import stepsdefinitions.general.BaseDefinition;

public class LoginPage extends BaseDefinition {
    @Given("the customer is on the {string} page")
    public void theCustomerIsOnTheSwagLabsPage(String title) {
        WebApp.Login.navigateToHomePage();
        WebApp.Login.assertTitleEquals(title);
    }

    @Given("I enter {string} and {string} on login")
    public void iEnterUsernameAndPasswordOnLogin(String username, String password) {
        WebApp.Login.enterLoginDetails(username, password);
    }

    @Then("an error message {string} will be displayed")
    public void anErrorMessageWillBeDisplayed(String message)
    {
        WebApp.Login.assertErrorMessageEquals(message);
    }

    @Then("the customer is on the login page")
    public void theCustomerIsOnTheLoginPage() {
        WebApp.Login.assertLoginButtonIsVisible();
    }
}
