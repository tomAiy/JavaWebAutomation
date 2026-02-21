package stepsdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepsdefinitions.general.BaseDefinition;

public class CheckoutPage extends BaseDefinition {

    @When("I checkout with {string} , {string} and {string}")
    public void iCheckoutWithFirstNameLastNameAndPostCode(String firstName, String lastName, String postCode)
    {
        WebApp.Checkout.clickCheckoutButton();
        WebApp.Checkout.completeCheckout(firstName, lastName, postCode);
        WebApp.Checkout.clickContinueButton();
        WebApp.Checkout.clickFinishButton();
    }

    @Then("the confirmation message {string} will be displayed")
    public void theConfirmationMessageWillBeDisplayed(String message)
    {
        WebApp.Checkout.assertConfirmationMessageEquals(message);
    }
}
