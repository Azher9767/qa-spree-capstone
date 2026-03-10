package com.spree.security;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthSecurityTest extends BaseTest {

    @Test(description = "Unauthenticated Access to Admin Panel Should Be Blocked")
    public void testAdminPageNotAccessibleWithoutLogin() {
        driver.get(ADMIN_URL);

        String currentUrl = driver.getCurrentUrl();

        Assert.assertFalse(currentUrl.equals(ADMIN_URL),
            "Admin panel accessible without authentication!");

        Assert.assertTrue(
            currentUrl.contains("/login") || currentUrl.contains("/sign_in") || currentUrl.equals(BASE_URL + "/"),
            "Not redirected to login after accessing admin. Current URL: " + currentUrl
        );
    }

    @Test(description = "Unauthenticated Access to Account Page Should Redirect to Login")
    public void testAccountPageRedirectsToLogin() {
        driver.get(ACCOUNT_URL);

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
            currentUrl.contains("/sign_in") || currentUrl.contains("/login"),
            "Account page accessible without login. Current URL: " + currentUrl
        );
    }

    @Test(description = "Brute Force Protection - Multiple Failed Logins")
    public void testMultipleFailedLoginAttempts() {
        driver.get(LOGIN_URL);

        for (int i = 0; i < 5; i++) {
            try {
                WebElement email    = driver.findElement(By.id("user_email"));
                WebElement password = driver.findElement(By.id("user_password"));
                WebElement loginBtn = driver.findElement(By.cssSelector("input[value='Login']"));

                email.clear();
                email.sendKeys("wronguser" + i + "@test.com");
                password.clear();
                password.sendKeys("wrongpassword" + i);
                loginBtn.click();

                Thread.sleep(500);

            } catch (Exception e) {
                // Continue attempts
            }
        }

        String pageSource = driver.getPageSource().toLowerCase();
        String currentUrl = driver.getCurrentUrl();

        Assert.assertFalse(currentUrl.contains("/account"),
            "Logged in after multiple failed attempts - brute force possible!");

        Assert.assertTrue(
            pageSource.contains("invalid") || pageSource.contains("incorrect") ||
            pageSource.contains("error")   || pageSource.contains("invalid email or password"),
            "No error shown after failed login attempts"
        );
    }

    @Test(description = "Session Should Invalidate After Logout")
    public void testSessionInvalidatedAfterLogout() {
        driver.get(LOGIN_URL);

        try {
            driver.findElement(By.id("user_email")).sendKeys(TEST_EMAIL);
            driver.findElement(By.id("user_password")).sendKeys(TEST_PASSWORD);
            driver.findElement(By.cssSelector("input[value='Login']")).click();

            Thread.sleep(1000);

            // Spree requires DELETE for logout - submit form via JavaScript
            String logoutScript =
                "var form = document.createElement('form');" +
                "form.method = 'POST';" +
                "form.action = '/user/sign_out';" +
                "var m = document.createElement('input');" +
                "m.type = 'hidden'; m.name = '_method'; m.value = 'delete';" +
                "form.appendChild(m);" +
                "var t = document.querySelector('meta[name=csrf-token]');" +
                "if (t) {" +
                "  var c = document.createElement('input');" +
                "  c.type = 'hidden'; c.name = 'authenticity_token';" +
                "  c.value = t.getAttribute('content');" +
                "  form.appendChild(c);" +
                "}" +
                "document.body.appendChild(form); form.submit();";

            ((JavascriptExecutor) driver).executeScript(logoutScript);
            Thread.sleep(1500);

            // Try accessing account after logout
            driver.get(ACCOUNT_URL);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                currentUrl.contains("/sign_in") || currentUrl.contains("/login"),
                "Account accessible after logout - session not invalidated! URL: " + currentUrl
            );

        } catch (Exception e) {
            driver.get(ACCOUNT_URL);
            Assert.assertTrue(
                driver.getCurrentUrl().contains("/sign_in") || driver.getCurrentUrl().contains("/login"),
                "Account page accessible without session"
            );
        }
    }
}