package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.IOException;

public class ReportUtil {
    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public synchronized static ExtentReports getReportInstance() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/extent-report.html");
            htmlReporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Tester", "Jayaraju");
        }
        return extent;
    }


    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static synchronized void removeTest() {
        extentTest.remove();
    }

    public static void attachScreenshotToReport(ExtentTest test,ITestResult result) throws IOException {
        String base64Screenshot = ScreenshotUtil.getBase64Screenshot(WebDriverFactory.getDriver());
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            test.log(Status.FAIL,result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Step executed successfully",MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        }
    }
}
