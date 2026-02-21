package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.webdrivermanager.WebAppDriver;

import java.time.Duration;

public class WaitUtils extends WebAppDriver {
    Duration defaultTimeout = Duration.ofSeconds(5);
    public WaitUtils(Duration defaultTimeout)
    {
        this.defaultTimeout = defaultTimeout;
    }
    public WebElement waitForElement(WebElement webElement, Duration timeout)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForElement(By elementBy)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }
}
