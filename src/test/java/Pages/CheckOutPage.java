package Pages;

import Utils.SeleniumUtil;
import org.openqa.selenium.By;

public class CheckOutPage {
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By backToHome = By.id("back-to-products");
    private By totalPrice = By.xpath("//div[@data-test='total-label']");
    private By taxPrice = By.xpath("//div[@data-test='tax-label']");
    private By itemPrice = By.xpath("//div[@data-test='subtotal-label']");
    private By paymentTypeInfo = By.xpath("//div[@data-test='payment-info-value']");
    private By checkOutCompleteMsgText = By.xpath("//h2[@data-test='complete-header']");

    public void enterFirstname(String fName){
        SeleniumUtil.sendKeys(this.firstNameInput,fName);
    }
    public void enterLastname(String lName){
        SeleniumUtil.sendKeys(this.lastNameInput,lName);
    }
    public void enterPostalCode(String postalCode){
        SeleniumUtil.sendKeys(this.postalCodeInput,postalCode);
    }
    public void clickOnFinishBtn(){SeleniumUtil.click(this.finishBtn);}
    public void clickOnContinueBtn(){SeleniumUtil.click(this.continueBtn);}
    public void clickOnBackToHomeBtn(){SeleniumUtil.click(this.backToHome);}

    public Float getTotalPrice(){
        return Float.parseFloat(SeleniumUtil.getElementText(totalPrice).split(":")[1].trim().replace("$",""));
    }

    public Float getTaxPrice(){
        return Float.parseFloat(SeleniumUtil.getElementText(taxPrice).split(":")[1].trim().replace("$",""));
    }
    public Float getItemPrice(){
        return Float.parseFloat(SeleniumUtil.getElementText(itemPrice).split(":")[1].trim().replace("$",""));
    }
    public String getPaymentTypeInfo(){
        return SeleniumUtil.getElementText(paymentTypeInfo);
    }

    public String getCheckOutCompleteMsgText(){
        return SeleniumUtil.getElementText(checkOutCompleteMsgText);
    }
}
