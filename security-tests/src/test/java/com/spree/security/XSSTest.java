package com.spree.security;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class XSSTest extends BaseTest {

    // Common XSS payloads
    private static final String[] XSS_PAYLOADS = {
        "<script>alert('XSS')</script>",
        "<img src=x onerror=alert('XSS')>",
        "javascript:alert('XSS')",
        "<svg onload=alert('XSS')>",
        "'\"><script>alert('XSS')</script>"
    };

    @Test(description = "XSS on Login Form Fields")
    public void testXSSOnLoginForm() {
        driver.get(LOGIN_URL);

        for (String payload : XSS_PAYLOADS) {
            try {
                WebElement emailField = driver.findElement(By.id("user_email"));
                emailField.clear();
                emailField.sendKeys(payload);

                WebElement passwordField = driver.findElement(By.id("user_password"));
                passwordField.clear();
                passwordField.sendKeys(payload);

                driver.findElement(By.cssSelector("input[value='Login']")).click();

                // Check no alert dialog popped up (XSS executed)
                boolean alertPresent = false;
                try {
                    Alert alert = driver.switchTo().alert();
                    alertPresent = true;
                    alert.dismiss();
                } catch (Exception e) {
                }

                Assert.assertFalse(alertPresent,
                    "XSS vulnerability found with payload: " + payload);

                String pageSource = driver.getPageSource();
                Assert.assertFalse(pageSource.contains("<script>alert('XSS')</script>"),
                    "Unescaped script tag found in page for payload: " + payload);

                driver.get(LOGIN_URL);

            } catch (Exception e) {
                driver.get(LOGIN_URL);
            }
        }
    }

    @Test(description = "XSS on Product Search Field")
    public void testXSSOnSearchField() {
        for (String payload : XSS_PAYLOADS) {
            try {
                // Spree search via URL param
                driver.get(PRODUCTS_URL + "?keywords=" + URLEncoder.encode(payload, StandardCharsets.UTF_8));

                boolean alertPresent = false;
                try {
                    Alert alert = driver.switchTo().alert();
                    alertPresent = true;
                    alert.dismiss();
                } catch (Exception e) {
                }

                Assert.assertFalse(alertPresent,
                    "XSS vulnerability in search for payload: " + payload);

            } catch (Exception e) {
                driver.get(PRODUCTS_URL);
            }
        }
    }
}