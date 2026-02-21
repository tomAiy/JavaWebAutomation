package application.pages;

import application.pages.general.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.ElementWrapper;

public class CheckoutPage extends BasePage {
    public void assertConfirmationMessageEquals(String message)
    {
        ElementWrapper confirmMessage = new ElementWrapper(getDriver(), (By.className("complete-header")));
        confirmMessage.assertTextEquals(message);
    }

    public void clickCheckoutButton()
    {
        ElementWrapper checkoutButton = new ElementWrapper(getDriver(), (By.id("checkout")));
        checkoutButton.click();
    }

    public void clickContinueButton()
    {
        ElementWrapper continueButton = new ElementWrapper(getDriver(), (By.id("continue")));
        continueButton.click();
    }

    public void clickFinishButton()
    {
        ElementWrapper finishButton = new ElementWrapper(getDriver(), (By.id("finish")));
        finishButton.click();
    }

    public void completeCheckout(String firstName, String lastName, String postalCode)
    {
        ElementWrapper firstNameElement = new ElementWrapper(getDriver(), (By.id("first-name")));
        ElementWrapper lastNameElement = new ElementWrapper(getDriver(), (By.id("last-name")));
        ElementWrapper postalCodeElement = new ElementWrapper(getDriver(), (By.id("postal-code")));

        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        postalCodeElement.sendKeys(postalCode);
    }
}
