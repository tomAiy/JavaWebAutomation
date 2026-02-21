package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilities.webdrivermanager.WebAppDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class Hooks {
    private static final ExtentReports extent;
    private static final String reportPath = System.getProperty("user.dir") + "/TestResults/";
    private static final ConcurrentHashMap<String, ExtentTest> featureTestMap = new ConcurrentHashMap<>();
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> lastStepNode = new ThreadLocal<>();

    static {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath + "index.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }
    @BeforeAll
    public static void beforeTestRun() {
        try {
            Files.createDirectories(Paths.get(reportPath));
        } catch (IOException e) {
            System.out.println("Error creating directory for report: " + e.getMessage());
        }
    }

    @Before
    public void beforeScenario(Scenario scenarioContext) {
        WebAppDriver.createDriver();

        String featureName = getFeatureNameFromScenario(scenarioContext);
        ExtentTest featureTest = featureTestMap.computeIfAbsent(featureName, extent::createTest);

        ExtentTest test = featureTest.createNode(scenarioContext.getName());
        scenarioTest.set(test);
    }

    @After
    public void afterScenario() {
        WebAppDriver.quitDriver();

        scenarioTest.remove();
        lastStepNode.remove();
    }

    @AfterAll
    public static void afterTestRun() {
        extent.flush();

        File htmlFile = new File(reportPath + "index.html");
        if (!java.awt.GraphicsEnvironment.isHeadless()) {
            try {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException e) {
                System.out.println("Error opening report automatically: " + e.getMessage());
            }
        } else {
            System.out.println("Headless environment detected. Report generated at: " + htmlFile.getAbsolutePath());
        }
    }

    public static ExtentTest getScenarioTest() {
        return scenarioTest.get();
    }

    public static void setLastStepNode(ExtentTest stepNode) {
        lastStepNode.set(stepNode);
    }

    public static ExtentTest getLastStepNode() {
        return lastStepNode.get();
    }

    private String getFeatureNameFromScenario(Scenario scenario) {
        String rawFeatureUri = scenario.getUri().toString();
        String featureFile = rawFeatureUri.substring(rawFeatureUri.lastIndexOf("/") + 1);
        return featureFile.replace(".feature", "");
    }
}
