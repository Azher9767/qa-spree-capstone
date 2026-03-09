package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginAndLogout() throws InterruptedException {

        driver.get("http://10.0.2.2:3000/user/sign_in");

        // Enter email
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))
        );
        emailField.sendKeys("test_account@example.com");

        // Enter password
        WebElement passwordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))
        );
        passwordField.sendKeys("password123");

        // Click login
        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("login-button"))
        );
        loginButton.click();

        // Wait until redirected away from login page
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

        // Wait for menu to fully load (turbo stream settle hone do)
        Thread.sleep(2000);

        // Sign out - xpath with contains to handle whitespace
        WebElement signOutLink = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href, '/user/sign_out')]")
            )
        );

        // JavaScript click use karo (turbo-method wala link hai)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signOutLink);

        // Wait for redirect back to home page page
        wait.until(ExpectedConditions.urlToBe("http://10.0.2.2:3000/"));

       Assert.assertTrue(driver.getCurrentUrl().contains("10.0.2.2:3000"), "Logout failed!");
    }
}
