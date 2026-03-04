package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
        public void userCanNavigateFromHomeToProductAndBack() {

        driver.get("http://10.0.2.2:3000");

        // Click first product
        WebElement firstProductLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".product-card a")
                )
        );

        firstProductLink.click();

        // Verify product page
        WebElement productTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.tagName("h1")
                )
        );

        Assert.assertTrue(productTitle.isDisplayed(),
                "Product title not visible");

        // Navigate back (only once)
        driver.navigate().back();

        // Wait until URL changes from product page
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/products/")
        ));

        // Store element in variable properly
      WebElement productLink = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[id^='product-']")
        )
);

        Assert.assertTrue(productLink.isDisplayed(),
        "Failed to return to homepage product list");
        }
}