package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Base64;

public class ScreenshotUtil {
    public static String getBase64Screenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String src = ts.getScreenshotAs(OutputType.BASE64);
        return "data:image/png;base64," + src;
    }
}
