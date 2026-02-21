package application.pages;

import application.pages.general.BasePage;
import org.openqa.selenium.By;
import utilities.ElementWrapper;

public class LoginPage extends BasePage {
   public void assertErrorMessageEquals(String expectedMessage)
   {
        ElementWrapper actualMessage = new ElementWrapper(getDriver(), (By.tagName("h3")));
        actualMessage.assertTextEquals(expectedMessage);
   }

    public void assertLoginButtonIsVisible()
    {
        ElementWrapper loginButtonVisibleCheck = new ElementWrapper(getDriver(), (By.id("login-button")));
        loginButtonVisibleCheck.assertTrue(loginButtonVisibleCheck.isDisplayed(), "The login button is being displayed");
    }

    public void enterLoginDetails(String username, String password)
    {
        ElementWrapper usernameElement = new ElementWrapper(getDriver(), (By.id("user-name")));
        ElementWrapper passwordElement = new ElementWrapper(getDriver(), (By.id("password")));
        ElementWrapper loginButtonClick = new ElementWrapper(getDriver(), (By.id("login-button")));

        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButtonClick.click();
    }
}
