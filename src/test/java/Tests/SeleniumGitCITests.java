package Tests;

import Pages.*;
import Utils.*;
import com.aventstack.extentreports.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;

public class SeleniumGitCITests
{
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductsPage productsPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckOutPage checkOutPage;
    private String appUrl = "https://www.saucedemo.com/";

    private ExtentReports extent;

    @BeforeClass(alwaysRun = true)
    public void oneTimeSetUp()
    {
        extent = ReportUtil.getReportInstance();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        extent.flush();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp()
    {
        WebDriverFactory.initializeDriver("chrome");
        SeleniumUtil.goToUrl(appUrl);
        loginPage = new LoginPage();
        homePage = new HomePage();
        productsPage = new ProductsPage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        checkOutPage = new CheckOutPage();
    }

    @Test(description = "verify user login for all accepted credentials",dataProvider = "loginDataProvider")
    public void userLogin(String username, String password)
    {
        ExtentTest test = extent.createTest("userLogin", "verify user login for all accepted credentials");
        ReportUtil.setTest(test);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        String logotext = homePage.getAppLogoText();
        Assert.assertEquals(logotext,"Swag Labs");
        homePage.clickBurgerMenu();
        homePage.clickLogout();
    }

    @Test(description = "verify locked_out_user user error desc")
    public void verifyLockedOutUserLogin()
    {
        String username="locked_out_user";
        String password = "secret_sauce";
        ExtentTest test = extent.createTest("verifyLockedOutUserLogin", "verify locked_out_user user error desc");
        ReportUtil.setTest(test);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getLoginErrText(),"Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(description = "verify adding items into cart")
    public void addItemsToCart()
    {
        String username="standard_user";
        String password = "secret_sauce";
        String productName ="Sauce Labs Backpack";
        ExtentTest test = extent.createTest("addItemsToCart", "verify adding items into cart");
        ReportUtil.setTest(test);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        homePage.waitForAppLogoTextToVisible();
        Float productPrice = productsPage.getProductItemPrice("Sauce Labs Backpack");
        productsPage.clickOnProductLink("Sauce Labs Backpack");
        String itemName = productPage.getItemName();
        Assert.assertEquals(itemName,productName);
        Float itemPrice =  productPage.getItemPrice();
        Assert.assertEquals(itemPrice,productPrice);
        productPage.clickOnAddToCartBtn();
        productPage.waitForAddToCartBtnToBeInVisible();
        productPage.waitForRemoveBtnToVisible();
        int itemsCount = cartPage.getCartItemsNumber();
        Assert.assertEquals(itemsCount,1);
        cartPage.clickOnCartLink();
        Float cartItemPrice = cartPage.getCartItemPrice();
        Assert.assertEquals(cartItemPrice,productPrice);
    }
    @Test(description = "verify removing items into cart")
    public void removeItemsToCart()
    {
        String username="standard_user";
        String password = "secret_sauce";
        String productName ="Sauce Labs Backpack";
        ExtentTest test = extent.createTest("removeItemsToCart", "verify adding items into cart");
        ReportUtil.setTest(test);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        homePage.waitForAppLogoTextToVisible();
        Float productPrice = productsPage.getProductItemPrice("Sauce Labs Backpack");
        productsPage.clickOnProductLink("Sauce Labs Backpack");
        String itemName = productPage.getItemName();
        Assert.assertEquals(itemName,productName);
        Float itemPrice =  productPage.getItemPrice();
        Assert.assertEquals(itemPrice,productPrice);
        productPage.clickOnAddToCartBtn();
        productPage.waitForRemoveBtnToVisible();
        productPage.waitForAddToCartBtnToBeInVisible();
        int itemsCount = cartPage.getCartItemsNumber();
        Assert.assertEquals(itemsCount,1);
        cartPage.clickOnCartLink();
        Float cartItemPrice = cartPage.getCartItemPrice();
        Assert.assertEquals(cartItemPrice,productPrice);
        cartPage.clickOnRemoveBtn();
        cartPage.waitForCartItemToBeClearedOut();
    }
    @Test(description = "verify adding items and checkout from cart")
    public void addItemsToCartAndCheckOut()
    {
        String username="standard_user";
        String password = "secret_sauce";
        String productName ="Sauce Labs Backpack";
        ExtentTest test = extent.createTest("addItemsToCartAndCheckOut", "verify adding items into cart");
        ReportUtil.setTest(test);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        homePage.waitForAppLogoTextToVisible();
        Float productPrice = productsPage.getProductItemPrice("Sauce Labs Backpack");
        productsPage.clickOnProductLink("Sauce Labs Backpack");
        String itemName = productPage.getItemName();
        Assert.assertEquals(itemName,productName);
        Float itemPrice =  productPage.getItemPrice();
        Assert.assertEquals(itemPrice,productPrice);
        productPage.clickOnAddToCartBtn();
        productPage.waitForRemoveBtnToVisible();
        productPage.waitForAddToCartBtnToBeInVisible();
        int itemsCount = cartPage.getCartItemsNumber();
        Assert.assertEquals(itemsCount,1);
        cartPage.clickOnCartLink();
        Float cartItemPrice = cartPage.getCartItemPrice();
        Assert.assertEquals(cartItemPrice,productPrice);
        cartPage.clickOnCheckOutBtn();
        checkOutPage.enterFirstname("Jayaraju");
        checkOutPage.enterLastname("Metta");
        checkOutPage.enterPostalCode("532242");
        checkOutPage.clickOnContinueBtn();
        Assert.assertEquals(checkOutPage.getItemPrice(),productPrice);
        Assert.assertEquals(checkOutPage.getTotalPrice(),checkOutPage.getTaxPrice()+checkOutPage.getItemPrice());
        Assert.assertTrue(checkOutPage.getPaymentTypeInfo().contains("#"));
        checkOutPage.clickOnFinishBtn();
        Assert.assertEquals(checkOutPage.getCheckOutCompleteMsgText(),"Thank you for your order!");
        checkOutPage.clickOnBackToHomeBtn();
    }

    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) throws IOException {
        ReportUtil.attachScreenshotToReport(ReportUtil.getTest(),result);
        WebDriverFactory.quitDriver();
    }

    @DataProvider(name = "loginDataProvider")
    public  Object[][] loginDataProvider() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }
}