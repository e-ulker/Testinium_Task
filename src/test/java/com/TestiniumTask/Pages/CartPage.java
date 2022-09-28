package com.TestiniumTask.Pages;


import com.TestiniumTask.Tests.BaseTest;
import com.TestiniumTask.Utilities.ElementHelper;
import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;
    ExtentTest logger;


    public static By orderSummaryTitle = By.xpath("//*[@class='m-orderSummary__title']");
    public static By productSalePrice = By.xpath("//*[@class='m-productPrice__salePrice']");
    public static By quantityDropdown = By.xpath("//*[@id='quantitySelect0-key-0']");
    public static By removeCartItemBtn = By.xpath("//*[@id='removeCartItemBtn0-key-0']");
    public static By removeCartNotifTitle = By.xpath("//*[@id='notifyTitle']");
    public static By emptyMessageBtn = By.xpath("//*[@class='m-empty__messageBtn']");


    public CartPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
        this.logger = BaseTest.logger;
    }

    public void increaseQuantity(String quantity) throws IOException {
        try {
            Select select = new Select(elementHelper.findElement(CartPage.quantityDropdown));
            List<WebElement> options = select.getOptions();
            int optionSize = options.size();
            for (int i = 0; i < optionSize; i++) {
                if (options.get(i).getText().contains(quantity)) {
                    select.selectByValue(quantity);
                    break;
                }
            }
            String actualQuantity = select.getFirstSelectedOption().getText();
            if ((quantity + " adet").equals(actualQuantity)) {
                Assert.assertEquals(quantity + " adet", actualQuantity);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void deleteFromBasket() throws IOException {
        try {
            elementHelper.click(CartPage.removeCartItemBtn);
            elementHelper.checkElementVisible(CartPage.removeCartNotifTitle);
            elementHelper.checkElementVisible(CartPage.emptyMessageBtn);
            String actualTitle = elementHelper.getAttribute(CartPage.emptyMessageBtn, "title");
            String expectedTitle = "Alışverişe Devam Et";
            if (expectedTitle.equals(actualTitle)) {
                Assert.assertEquals(expectedTitle, actualTitle);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

}