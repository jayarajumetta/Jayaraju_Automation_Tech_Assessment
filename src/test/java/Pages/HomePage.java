package Pages;

import Utils.SeleniumUtil;
import org.openqa.selenium.By;

public class HomePage {
    private By logoutSidebarLink = By.id("logout_sidebar_link");
    private By burgerMenuBtn = By.id("react-burger-menu-btn");
    private By appLogoText = By.xpath("//div[@class='app_logo']");
    public void clickBurgerMenu(){
        SeleniumUtil.click(this.burgerMenuBtn);}
    public void clickLogout(){SeleniumUtil.click(this.logoutSidebarLink);}

    public void waitForAppLogoTextToVisible(){
        SeleniumUtil.waitForElementToBeVisible(this.appLogoText);
    }
    public String getAppLogoText() {return SeleniumUtil.getElementText(this.appLogoText);}
}
