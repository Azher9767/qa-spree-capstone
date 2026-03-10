package com.spree.security;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SensitiveDataTest extends BaseTest {

    @Test(description = "Password Field Should Be Masked")
    public void testPasswordFieldIsMasked() {
        driver.get(LOGIN_URL);

        WebElement passwordField = driver.findElement(By.id("user_password"));
        String fieldType = passwordField.getAttribute("type");

        Assert.assertEquals(fieldType, "password",
            "Password field is not masked! Type is: " + fieldType);
    }

    @Test(description = "No Sensitive Data in Page Source on Login Page")
    public void testNoSensitiveDataInLoginPageSource() {
        driver.get(LOGIN_URL);

        String pageSource = driver.getPageSource().toLowerCase();

        Assert.assertFalse(pageSource.contains("api_key"),      "API key found in login page source!");
        Assert.assertFalse(pageSource.contains("secret_key"),   "Secret key found in login page source!");
        Assert.assertFalse(pageSource.contains("db_password"),  "DB password found in login page source!");
        Assert.assertFalse(pageSource.contains("aws_secret"),   "AWS secret found in login page source!");
    }

    @Test(description = "No Sensitive Data in Product Page Source")
    public void testNoSensitiveDataInProductPage() {
        driver.get(PRODUCTS_URL);

        String pageSource = driver.getPageSource().toLowerCase();

        Assert.assertFalse(pageSource.contains("api_key"),    "API key exposed on products page!");
        Assert.assertFalse(pageSource.contains("private_key"), "Private key exposed on products page!");
    }

    @Test(description = "Error Pages Should Not Expose Stack Trace")
    public void testErrorPageDoesNotExposeStackTrace() {
        driver.get(BASE_URL + "/this-page-does-not-exist-12345");

        String pageSource = driver.getPageSource().toLowerCase();

        Assert.assertFalse(pageSource.contains("stack trace"),   "Stack trace exposed in error page!");
        Assert.assertFalse(pageSource.contains("activerecord"),  "ActiveRecord info exposed in error page!");
    }

    @Test(description = "Signup Form - Password Fields Should Be Masked")
    public void testRegistrationPasswordFieldsMasked() {
        driver.get(SIGNUP_URL);

        WebElement passwordField        = driver.findElement(By.id("user_password"));
        WebElement passwordConfirmField = driver.findElement(By.id("user_password_confirmation"));

        Assert.assertEquals(passwordField.getAttribute("type"), "password",
            "Password field not masked on signup!");
        Assert.assertEquals(passwordConfirmField.getAttribute("type"), "password",
            "Password confirmation field not masked on signup!");
    }
}