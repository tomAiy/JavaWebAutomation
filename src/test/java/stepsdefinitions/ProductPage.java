package stepsdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepsdefinitions.general.BaseDefinition;

public class ProductPage extends BaseDefinition {
    @When("I select the add to cart button")
    public void iSelectTheAddToCartButton()
    {
        WebApp.Product.clickAddToCart();
    }

    @When("click the basket icon")
    public void clickTheBasketIcon()
    {
        WebApp.Product.Header.clickBasket();
    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        WebApp.Product.clickMenuButton();
        WebApp.Product.clickLogoutButton();
    }

    @Then("the basket is updated to {string}")
    public void theBasketIsUpdatedTo(String basketCount) {
        WebApp.Product.assertBasketItemCountIsEqualTo(basketCount);
    }
}
