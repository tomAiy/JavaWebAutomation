package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.time.Duration;

public class ElementWrapper {
    private WebDriver driver;
    private By locator;
    private WaitUtils wait;
    public ElementWrapper(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
        this.wait = new WaitUtils(Duration.ofSeconds(5));
    }

    public void assertPageTitleEquals(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle,
                "Page title mismatch! Expected: [" + expectedTitle + "] but found: [" + actualTitle + "]");
    }

    public void assertTextEquals(String expectedText){
        WebElement element = getElement();
        String actualText = element.getText();
        Assert.assertEquals(actualText, expectedText,
                "Text does not match! Expected: [" + expectedText + "] but found: [" + actualText + "]");
    }

    public void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public void click() {
        WebElement element = getElement();
        wait.waitForElement(element, Duration.ofSeconds(5));
        element.click();
    }

    public void sendKeys(String text) {
        WebElement element = getElement();
        element.clear();
        element.sendKeys(text);
    }

    public String getText() {
        return getElement().getText();
    }

    public boolean isDisplayed() {
        try {
            return getElement().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement getElement() {
        return wait.waitForElement(locator);
    }
}