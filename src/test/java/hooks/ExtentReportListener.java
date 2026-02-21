package hooks;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.webdrivermanager.WebAppDriver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtentReportListener implements ConcurrentEventListener {
    private static final String reportPath = System.getProperty("user.dir") + "/TestResults/";

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::handleStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleStepFinished);
    }

    private void handleStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();

            String stepText = step.getStep().getText();
            String stepKeyword = step.getStep().getKeyword();

            try {
                if (Hooks.getScenarioTest() != null) {
                    ExtentTest stepNode = Hooks.getScenarioTest()
                            .createNode(new GherkinKeyword(stepKeyword.trim()), stepText);
                    Hooks.setLastStepNode(stepNode);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleStepFinished(TestStepFinished event) {
        if (Hooks.getLastStepNode() != null) {
            if (event.getResult().getStatus().is(Status.PASSED)) {
                Hooks.getLastStepNode().pass("Step passed.");
            } else if (event.getResult().getStatus().is(Status.FAILED)) {
                Throwable error = event.getResult().getError();
                String errorMessage = error != null ? error.getMessage().toString() : "Unknown error occurred.";

                try {
                    byte[] screenshot = ((TakesScreenshot) WebAppDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
                    String screenshotPath = reportPath + "failed-step-" + System.currentTimeMillis() + ".png";
                    Files.write(Paths.get(screenshotPath), screenshot);

                    Hooks.getLastStepNode()
                            .fail("Step failed: " + errorMessage,
                                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } catch (IOException e) {
                    Hooks.getLastStepNode().fail("Failed to capture screenshot: " + e.getMessage());
                }
            } else if (event.getResult().getStatus().is(Status.SKIPPED)) {
                Hooks.getLastStepNode().skip("Step was skipped.");
            }
        }
    }
}