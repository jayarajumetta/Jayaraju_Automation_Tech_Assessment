package Pages;

import Utils.SeleniumUtil;
import org.openqa.selenium.By;

public class CartPage {
    private By cartLink = By.xpath("//a[@data-test='shopping-cart-link']");
    private By cartItemsNumber= By.xpath("//span[@data-test='shopping-cart-badge']/parent::a");
    private By checkOutBtn = By.id("checkout");
    private By itemPriceText = By.xpath("//div[@data-test='inventory-item-price']");
    private By removeBtn = By.xpath("//button[.='Remove']");

    private By itemName = By.xpath("//div[@data-test='inventory-item-name']");

    public String getItemName(){
        return SeleniumUtil.getElementText(this.itemName);
    }
    public void waitForRemoveBtnToVisible(){
        SeleniumUtil.waitForElementToBeVisible(this.removeBtn);
    }
    public void clickOnRemoveBtn(){
        SeleniumUtil.click(this.removeBtn);}
    public void clickOnCartLink(){
        SeleniumUtil.click(this.cartLink);}

    public void clickOnCheckOutBtn(){
        SeleniumUtil.click(this.checkOutBtn);}

    public Float getCartItemPrice(){
        return Float.parseFloat(SeleniumUtil.getElementText(itemPriceText).replace("$",""));
    }

    public int getCartItemsNumber(){
        return Integer.parseInt(SeleniumUtil.getElementText(cartItemsNumber));
    }

    public void waitForCartItemToBeClearedOut(){
        SeleniumUtil.waitForElementToBeInVisible(itemName);
        SeleniumUtil.waitForElementToBeInVisible(removeBtn);
    }
}

