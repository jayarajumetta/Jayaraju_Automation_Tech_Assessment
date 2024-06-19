package Utils;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected ExtentReports extent;

    @BeforeClass(alwaysRun = true)
    public void setUp()
    {
        WebDriverFactory.initializeDriver("chrome");
        WebDriver driver = WebDriverFactory.getDriver();
        extent = ReportUtil.getReportInstance();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        extent.flush();
    }
}
