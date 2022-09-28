package com.TestiniumTask.Pages;


import com.TestiniumTask.Tests.BaseTest;
import com.TestiniumTask.Utilities.ElementHelper;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Random;

public class ProductListPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;
    ExtentTest logger;


    public static By productList = By.xpath("//*[contains(@class, 'o-productList__itemWrapper')]");



    public ProductListPage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
        this.logger = BaseTest.logger;
    }

    public void getRandomProduct() throws IOException {
        try {
            Random random = new Random();
            int productSize = elementHelper.findElements(ProductListPage.productList).size();
            int count = random.nextInt(productSize);
            WebElement product = elementHelper.findElements(ProductListPage.productList).get(count);
            elementHelper.scrollToElement(product);
            product.click();
            elementHelper.checkElementVisible(ProductDetailPage.productDescription);
            elementHelper.testCasePassed();
        } catch (Exception e) {
            elementHelper.testCaseFailed();
            throw new RuntimeException(e);
        }

    }

}