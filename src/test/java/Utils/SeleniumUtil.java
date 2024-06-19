package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtil {

    private static WebDriverWait wait;
    public static void goToUrl( String url){
        WebDriver driver = WebDriverFactory.getDriver();
        driver.navigate().to(url);
    }

    public static WebDriverWait webDriverWait(WebDriver driver, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Wait for an element to be visible
    public static WebElement waitForElementToBeVisible(By locator) {
        try{
        wait = SeleniumUtil.webDriverWait(WebDriverFactory.getDriver(), 30);
        ReportUtil.getTest().info("Waiting for visible:" + locator.toString());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }        catch(Exception e){
        ReportUtil.getTest().error(e.getMessage());
        throw new RuntimeException(e.getMessage());
    }
    }

    // Wait for an element to be Invisible
    public static Boolean waitForElementToBeInVisible(By locator) {
        try{
            wait = SeleniumUtil.webDriverWait(WebDriverFactory.getDriver(), 30);
            ReportUtil.getTest().info("Waiting for Invisible:" + locator.toString());
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }        catch(Exception e){
            ReportUtil.getTest().error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    // Wait for an element to be present in the DOM
    public WebElement waitForElementToBePresent(By locator) {
        try{
            wait = SeleniumUtil.webDriverWait(WebDriverFactory.getDriver(),30);
            ReportUtil.getTest().info("Waiting for present:"+locator.toString());
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        catch(Exception e){
            ReportUtil.getTest().error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    // Click an element
    public static void click(By locator) {
        waitForElementToBeVisible(locator).click();
        ReportUtil.getTest().info("Click action is executed on:"+locator.toString());
    }

    // Send keys to an element
    public static void sendKeys(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
        ReportUtil.getTest().info("entered "+text+"into:"+locator.toString());
    }

    public static String getElementText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        String text = element.getText();
        ReportUtil.getTest().info("extracted text:"+text+" from"+locator.toString());
        return text;
    }
}
