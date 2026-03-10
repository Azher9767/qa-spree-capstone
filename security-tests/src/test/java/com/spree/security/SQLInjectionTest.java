package com.spree.security;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SQLInjectionTest extends BaseTest {

    // Common SQL injection payloads
    private static final String[] SQL_PAYLOADS = {
        "' OR '1'='1",
        "' OR 1=1--",
        "'; DROP TABLE users;--",
        "' UNION SELECT null,null--",
        "1' AND '1'='1"
    };

    @Test(description = "SQL Injection on Login Email Field")
    public void testSQLInjectionOnLoginEmail() {
        driver.get(LOGIN_URL);

        for (String payload : SQL_PAYLOADS) {
            try {
                WebElement emailField    = driver.findElement(By.id("user_email"));
                WebElement passwordField = driver.findElement(By.id("user_password"));
                WebElement loginButton   = driver.findElement(By.cssSelector("input[value='Login']"));

                emailField.clear();
                emailField.sendKeys(payload);
                passwordField.clear();
                passwordField.sendKeys("fakepassword");
                loginButton.click();

                String pageSource = driver.getPageSource().toLowerCase();

                // Should NOT see database errors
                Assert.assertFalse(pageSource.contains("sql"),
                    "SQL error exposed for payload: " + payload);
                Assert.assertFalse(pageSource.contains("syntax error"),
                    "Syntax error exposed for payload: " + payload);
                Assert.assertFalse(pageSource.contains("mysql"),
                    "MySQL error exposed for payload: " + payload);
                Assert.assertFalse(pageSource.contains("pg::"),
                    "PostgreSQL error exposed for payload: " + payload);

                // Should NOT be logged in
                Assert.assertFalse(driver.getCurrentUrl().contains("/account"),
                    "SQL Injection allowed login with payload: " + payload);

                driver.get(LOGIN_URL);

            } catch (Exception e) {
                driver.get(LOGIN_URL);
            }
        }
    }

    @Test(description = "SQL Injection on Product Search Field")
    public void testSQLInjectionOnSearch() {
        driver.get(PRODUCTS_URL);

        for (String payload : SQL_PAYLOADS) {
            try {
                // Spree search: /products?keywords=...
                driver.get(PRODUCTS_URL + "?keywords=" + URLEncoder.encode(payload, StandardCharsets.UTF_8));

                String pageSource = driver.getPageSource().toLowerCase();

                Assert.assertFalse(pageSource.contains("sql syntax"),
                    "SQL syntax error in search for payload: " + payload);
                Assert.assertFalse(pageSource.contains("unclosed quotation"),
                    "DB error in search for payload: " + payload);
                Assert.assertFalse(pageSource.contains("pg::"),
                    "PostgreSQL error in search for payload: " + payload);
                Assert.assertFalse(pageSource.contains("activerecord"),
                    "ActiveRecord error in search for payload: " + payload);

            } catch (Exception e) {
                driver.get(PRODUCTS_URL);
            }
        }
    }
}