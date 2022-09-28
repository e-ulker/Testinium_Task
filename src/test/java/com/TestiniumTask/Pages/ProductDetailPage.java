package com.TestiniumTask.Pages;


import com.TestiniumTask.Tests.BaseTest;
import com.TestiniumTask.Utilities.ElementHelper;
import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Random;

public class ProductDetailPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;
    ExtentTest logger;



    public static By productDescription = By.xpath("//*[@class='o-productDetail__description']");
    public static By productColor = By.xpath("//*[@class='m-colorsSlider__top']//label");
    public static By productPrice = By.xpath("//*[@id='priceNew']");
    public static By productSizes = By.xpath("//*[contains(@class, 'm-variation__item')]");
    public static By addBasketBtn = By.xpath("//*[@id='addBasket']");
    public static By addedToCartNotifTitle = By.xpath("//*[@class='m-notification__title']");
    public static By addedToCartNotifBtn = By.xpath("//*[@class='m-notification__button btn']");




    private String detailPrice;

    public ProductDetailPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
        this.logger = BaseTest.logger;
    }

    public void writeProductInformation() throws IOException {
        try {
            elementHelper.writeToTxt(ProductDetailPage.productDescription, ProductDetailPage.productColor, ProductDetailPage.productPrice);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void addToBasket() throws IOException {
        try {
            detailPrice = elementHelper.getText(ProductDetailPage.productPrice);
            int bodySizeCount = elementHelper.findElements(ProductDetailPage.productSizes).size();
            while (true) {
                Random random = new Random();
                int count = random.nextInt(bodySizeCount);
                WebElement selectedBody = elementHelper.findElements(ProductDetailPage.productSizes).get(count);
                if (!selectedBody.getAttribute("class").contains("-disabled")) {
                    selectedBody.click();
                    break;
                }
            }
            elementHelper.click(ProductDetailPage.addBasketBtn);
            elementHelper.checkElementVisible(ProductDetailPage.addedToCartNotifTitle);
            String actualText = elementHelper.getText(ProductDetailPage.addedToCartNotifTitle);
            String expectedText = "Sepete Eklendi";
            if (expectedText.equals(actualText)) {
                Assert.assertEquals(expectedText, actualText);
                elementHelper.testCasePassed();
            } else {
                elementHelper.testCaseFailed();
            }
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }
    }

    public void checkPriceInBasket() throws IOException {
        try {
            elementHelper.click(ProductDetailPage.addedToCartNotifBtn);
            elementHelper.checkElementVisible(CartPage.orderSummaryTitle);
            String salePrice = elementHelper.getText(CartPage.productSalePrice);
            if (detailPrice.equals(salePrice)) {
                Assert.assertEquals(detailPrice, salePrice);
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