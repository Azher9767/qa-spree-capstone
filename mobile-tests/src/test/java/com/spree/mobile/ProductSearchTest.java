package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSearchTest extends BaseTest {

    @Test
    public void userCanSearchProductSuccessfully() {

        driver.get("http://10.0.2.2:3000/products?keywords=shirt");

        // Wait until turbo frame loads
        WebElement productList = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("products_list")
            )
        );

        Assert.assertTrue(productList.isDisplayed(), "Product list not visible");

        // Ensure "No products found" is NOT present
        boolean noProductMessage =
                driver.getPageSource().contains("No products found");

        Assert.assertFalse(noProductMessage, "Unexpected: No products found message displayed");
    }

    @Test
    public void userSeesMessageWhenNoProductFound() {

        driver.get("http://10.0.2.2:3000/search?q=randomnonexistingproduct123xyz");

        // Wait for page title
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Search Results')]")
            )
        );

        // Verify "No products found" message appears
        WebElement noProductText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'No products found')]")
            )
        );

        Assert.assertTrue(noProductText.isDisplayed(), "Expected no product message not displayed");
    }
}