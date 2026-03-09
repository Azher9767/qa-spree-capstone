package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutFormTest extends BaseTest {

    private static final String BASE_URL = "http://10.0.2.2:3000";
    private static final String EMAIL = "test_account@example.com";
    private static final String PASSWORD = "password123";

    @Test
    public void userCanCompleteCheckoutWithCheckPayment() throws InterruptedException {

        // Step 1: Login
        driver.get(BASE_URL + "/user/sign_in");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")))
            .sendKeys(EMAIL);
        driver.findElement(By.id("user_password")).sendKeys(PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("sign_in")));

        // Step 2: Product add to cart
        driver.get(BASE_URL + "/products/digital-download");
        Thread.sleep(1500);
        WebElement addToCartBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector(".add-to-cart-button")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
        Thread.sleep(2000);

        // Step 3: Cart page token extract
        driver.get(BASE_URL + "/cart");
        WebElement checkoutLink = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a[data-cart-target='checkoutButton']")
            )
        );
        String checkoutHref = checkoutLink.getAttribute("href");
        // href = http://10.0.2.2:3000/checkout/TOKEN
        String token = checkoutHref.split("/checkout/")[1].split("/")[0];
        System.out.println("Checkout token: " + token);

        // Step 4: Address page visit 
        driver.get(BASE_URL + "/checkout/" + token + "/address");
        wait.until(ExpectedConditions.urlContains("/address"));
        Thread.sleep(1500);

       // Step 5: Address page - Save and Continue (logged-in user, address pre-filled)
        wait.until(ExpectedConditions.urlContains("/address"));
        Thread.sleep(1500);

        WebElement saveBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector(".checkout-content-save-continue-button")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);

        // Step 7: Payment page wait
        wait.until(ExpectedConditions.urlContains("/payment"));
        Thread.sleep(2000);
        System.out.println("Payment page URL: " + driver.getCurrentUrl());

        // Step 8: Check payment method select (id=3)
        WebElement checkPaymentLink = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href*='payment_method_id=3']")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkPaymentLink);
        Thread.sleep(1500);

        // Step 9: Pay button click
        WebElement payBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("checkout-payment-submit"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payBtn);

        // Step 10: Confirmation verify 
        wait.until(ExpectedConditions.urlContains("/complete"));
        WebElement confirmation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Your order is confirmed')]")
            )
        );
        Assert.assertTrue(confirmation.isDisplayed(), "Order confirmation not found!");
        System.out.println("Checkout test PASSED!");
    }
}