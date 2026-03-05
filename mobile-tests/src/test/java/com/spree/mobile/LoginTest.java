package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test
    public void userCanLoginAndLogout() {

        driver.get("http://10.0.2.2:3000/user/sign_in");

        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))
        );
        emailField.sendKeys("test_account@example.com");

        WebElement passwordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))
        );
        passwordField.sendKeys("password123");

        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("login-button"))
        );
        loginButton.click();

        // Wait until we are redirected away from login page
        wait.until(ExpectedConditions.not(
            ExpectedConditions.urlContains("sign_in")
        ));

        // Click hamburger menu
        WebElement menuButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-toggle-menu-target='button']")
            )
        );
        menuButton.click();

        // Click Sign out
        WebElement signOutLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.linkText("Sign out"))
        );
        signOutLink.click();

        // Verify login page appears again
        WebElement loginPageCheck = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))
        );
        Assert.assertTrue(loginPageCheck.isDisplayed(), "Logout failed!");
    }
}