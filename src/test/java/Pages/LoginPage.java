package Pages;

import Utils.SeleniumUtil;
import org.openqa.selenium.By;

public class LoginPage {
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginBtn = By.id("login-button");
    private By loginErr = By.xpath("//h3[@data-test='error']");

    public void enterUsername(String username){
        SeleniumUtil.sendKeys(this.usernameInput,username);
    }

    public void waitForUsernameToVisible(){
        SeleniumUtil.waitForElementToBeVisible(this.usernameInput);
    }
    public void enterPassword(String password){
        SeleniumUtil.sendKeys(this.passwordInput,password);
    }
    public void clickLogin(){SeleniumUtil.click(this.loginBtn);}

    public String getLoginErrText(){
        return SeleniumUtil.getElementText(loginErr);
    }

}
