package Pages;

import Utils.SeleniumUtil;
import org.openqa.selenium.By;

public class ProductPage {
    private By addToCartBtn = By.id("add-to-cart");
    private By itemName = By.xpath("//div[@data-test='inventory-item-name']");
    private By removeBtn = By.id("remove");
    private By itemPriceText = By.xpath("//div[@data-test='inventory-item-price']");

    public void clickOnAddToCartBtn(){
        SeleniumUtil.click(this.addToCartBtn);}

    public String getItemName(){
        return SeleniumUtil.getElementText(this.itemName);
    }

    public Float getItemPrice(){
        return Float.parseFloat(SeleniumUtil.getElementText(this.itemPriceText).replace("$",""));}

    public void waitForRemoveBtnToVisible(){
        SeleniumUtil.waitForElementToBeVisible(this.removeBtn);
    }

    public void waitForAddToCartBtnToBeInVisible(){
        SeleniumUtil.waitForElementToBeInVisible(this.addToCartBtn);
    }

}
