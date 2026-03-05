package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void userCanAddProductToCart() {

        // 1️⃣ Open specific product page
        driver.get("http://10.0.2.2:3000/products/digital-download");

        // 2️⃣ Wait for Add to Cart button
        WebElement addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".add-to-cart-button")
                )
        );

        Assert.assertTrue(addToCartButton.isDisplayed(), 
                "Add to Cart button not visible");

        // 3️⃣ Click Add to Cart
        addToCartButton.click();

        // 4️⃣ Navigate to Cart page
        driver.get("http://10.0.2.2:3000/cart");

        // 5️⃣ Wait for cart page to load
        WebElement body = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.tagName("body")
                )
        );

        // 6️⃣ Verify cart is NOT empty
        String pageText = body.getText();

        Assert.assertFalse(
                pageText.contains("Your cart is empty"),
                "Cart is still empty after adding product"
        );
    }
}