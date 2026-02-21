package application.pages;

import application.pages.general.BasePage;
import application.pages.sections.Header;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.ElementWrapper;

public class ProductPage extends BasePage {
    public Header Header = new Header();

    public void assertBasketItemCountIsEqualTo(String basketCount)
    {
        ElementWrapper cartBasketCount = new ElementWrapper(getDriver(), (By.className("shopping_cart_badge")));
        cartBasketCount.assertTextEquals(basketCount);
    }

    public void clickAddToCart()
    {
        ElementWrapper addProduct = new ElementWrapper(getDriver(), (By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']")));
        addProduct.click();
    }

    public void clickLogoutButton()
    {
        ElementWrapper logoutButton = new ElementWrapper(getDriver(), (By.id("logout_sidebar_link")));
        logoutButton.click();
    }

    public void clickMenuButton()
    {
        ElementWrapper menuBar = new ElementWrapper(getDriver(), (By.id("react-burger-menu-btn")));
        menuBar.click();
    }
}
