package application.pages.sections;

import application.pages.general.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.ElementWrapper;

public class Header extends BasePage {
    public void assertSubTitleEquals(String subTitle)
    {
        ElementWrapper title = new ElementWrapper(getDriver(), (By.xpath(String.format("//span[@class='title' and contains(.,'%s')]", subTitle))));
        title.assertTextEquals(subTitle);
    }

    public void clickBasket()
    {
        ElementWrapper basket = new ElementWrapper(getDriver(), (By.className("shopping_cart_link")));
        basket.click();
    }
}
