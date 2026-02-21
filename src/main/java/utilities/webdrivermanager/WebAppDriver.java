package utilities.webdrivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.ConfigLoader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WebAppDriver {
    private static ThreadLocal<org.openqa.selenium.WebDriver> webDriver = ThreadLocal.withInitial(() -> null);

    public static void createDriver() {
        if (webDriver.get() == null) {
            String browser = ConfigLoader.getBrowser().toLowerCase();

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-extensions", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.password_manager_leak_detection", false);
                    prefs.put("credentials_enable_service", false);
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    if (ConfigLoader.isHeadless()) {
                        chromeOptions.addArguments("--headless");
                    }
                    if (ConfigLoader.isRemote()) {
                        try {
                            webDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions));
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        webDriver.set(new ChromeDriver(chromeOptions));
                    }
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-extensions", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                    if (ConfigLoader.isHeadless()) {
                        edgeOptions.addArguments("--headless");
                    }
                    if (ConfigLoader.isRemote()) {
                        try {
                            webDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), edgeOptions));
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        webDriver.set(new EdgeDriver(edgeOptions));
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser specified: " + ConfigLoader.getBaseUrl());
            }
        }
        webDriver.get().manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1070));
    }

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Please call WebAppDriver.createDriver() first.");
        }
        return webDriver.get();
    }

    public static void quitDriver(){
        if (webDriver.get() != null) {
             webDriver.get().quit();
             webDriver.remove();
        }
    }
}
