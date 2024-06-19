package Pages;

import Utils.SeleniumUtil;
import org.openqa.selenium.By;

public class ProductsPage {
    private String productLink = "//div[@data-test='inventory-item-name' and text()='@productName']/parent::a";
    private String productPrice ="//div[@data-test='inventory-item-name' and text()='@productName']/ancestor::div[1]/following-sibling::div/div";
//    private String productAddToCart ="//div[@data-test='inventory-item-name' and text()='@productName']/ancestor::div[1]/following-sibling::div/button";

    public void clickOnProductLink(String productName){
        SeleniumUtil.click(By.xpath(this.productLink.replace("@productName",productName)));
    }

    public Float getProductItemPrice(String productName){
        return Float.parseFloat(SeleniumUtil.getElementText(By.xpath(this.productPrice.replace("@productName",productName))).replace("$",""));
    }
}
