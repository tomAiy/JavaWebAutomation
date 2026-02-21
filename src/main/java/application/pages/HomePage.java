package application.pages;

import application.pages.general.BasePage;
import application.pages.sections.Header;
import org.openqa.selenium.By;
import utilities.ElementWrapper;

public class HomePage extends BasePage {
    public Header Header = new Header();

    public void assertProductAndPriceEquals(String productName, String price)
    {
        ElementWrapper productNameAndPrice = new ElementWrapper(getDriver(), (By.xpath(String.format("//div[@class='inventory_item_description' and contains(.,'%s') and contains(.,'%s')]", productName, price))));
        productNameAndPrice.assertTrue(productNameAndPrice.getText().contains(productName) && productNameAndPrice.getText().contains(price), "The product name and price do not match! Expected: Product = [" +
                productName + "], Price = [" + price + "], but got: [" +
                productNameAndPrice.getText() + "]");
    }

    public void selectProduct(String productName)
    {
        ElementWrapper product = new ElementWrapper(getDriver(), (By.xpath(String.format("//a[@id='item_4_title_link' and contains(.,'%s')]", productName))));
        product.click();
    }
}
