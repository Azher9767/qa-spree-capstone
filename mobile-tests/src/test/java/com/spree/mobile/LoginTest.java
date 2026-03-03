package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLogin() {

        driver.get("http://10.0.2.2:3000/user/sign_in");

        // Wait for email and password fields
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))
        );
        emailField.sendKeys("test_account@example.com");

        WebElement passwordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))
        );
        passwordField.sendKeys("password123");

        // Click login button
        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("login-button"))
        );
        loginButton.click();

        // Wait for URL to change as login success
        wait.until(ExpectedConditions.not(
            ExpectedConditions.urlContains("/user/sign_in")
        ));

        // Optional: check Logout text inside turbo frame
        WebElement turboFrame = driver.findElement(By.id("login")); // frame container
        boolean isLoggedIn = turboFrame.getText().contains("Logout");
        Assert.assertTrue(isLoggedIn, "User login failed!");
    }
}