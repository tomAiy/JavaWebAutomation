package application.pages.general;

import org.openqa.selenium.WebDriver;
import utilities.ConfigLoader;
import utilities.ElementWrapper;
import utilities.webdrivermanager.WebAppDriver;

public class BasePage {
    public void assertTitleEquals(String expectedTitle){
        ElementWrapper titleWrapper = new ElementWrapper(getDriver(), null);
        titleWrapper.assertPageTitleEquals(expectedTitle);
    }

    public WebDriver getDriver(){
        return WebAppDriver.getDriver();
    }

    public void navigateToHomePage(){
        getDriver().get(ConfigLoader.getBaseUrl());
    }
}
