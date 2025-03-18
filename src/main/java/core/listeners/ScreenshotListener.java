package core.listeners;

import core.TestContextManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String testName = result.getName();
        String fileName = "screenshots/" + testName + "_" + timestamp + ".png";

        File screenshot = ((TakesScreenshot) TestContextManager.getContext().getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(screenshot.toPath(), Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
