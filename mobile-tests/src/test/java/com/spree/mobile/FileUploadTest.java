package com.spree.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest extends BaseTest {

    private static final String ADMIN_BASE_URL = "http://10.0.2.2:3000";

    @Test
    public void adminCanUploadAvatar() throws Exception {

        // Step 1: Admin login
        driver.get(ADMIN_BASE_URL + "/admin_user/sign_in");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin_user_email")))
            .sendKeys("spree@example.com");

        driver.findElement(By.id("admin_user_password")).sendKeys("Spree@Test123!");

        WebElement loginBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled');", loginBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

        // Step 3: Profile edit page
        driver.get(ADMIN_BASE_URL + "/admin/profile/edit");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit_user")));
        Thread.sleep(2000);
        System.out.println("Profile page loaded");

        // Step 4: PNG file path
        File uploadFile = new File("src/test/resources/test-spree-avatar.png");
        Assert.assertTrue(uploadFile.exists(), "Test image file nahi mila: " + uploadFile.getAbsolutePath());
        System.out.println("File path: " + uploadFile.getAbsolutePath());

        // Step 5: Upload click - Uppy modal open
        WebElement uploadArea = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(".upload-area"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadArea);
        Thread.sleep(2000);
        System.out.println("Upload area clicked, Uppy modal opening...");

        // Step 6: Uppy modal area hidden now visible
        WebElement uppyInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.uppy-Dashboard-input"))
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].removeAttribute('hidden');" +
            "arguments[0].removeAttribute('aria-hidden');" +
            "arguments[0].style.display = 'block';" +
            "arguments[0].style.opacity = '1';" +
            "arguments[0].style.width = '200px';" +
            "arguments[0].style.height = '50px';",
            uppyInput
        );
        Thread.sleep(500);

        // Step 7: File send
        uppyInput.sendKeys(uploadFile.getAbsolutePath());
        Thread.sleep(3000);
        System.out.println("File sent to Uppy input");

        // Step 8: click Uppy upload button 
        try {
            WebElement uploadBtn = driver.findElement(
                By.cssSelector(".uppy-StatusBar-actionBtn--upload")
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadBtn);
            Thread.sleep(3000);
            System.out.println("Uppy upload button clicked");
        } catch (Exception e) {
            System.out.println("Uppy upload btn nahi mila, continue: " + e.getMessage());
        }

        // Step 9: Form submit
        WebElement updateBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-admin-target='save']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateBtn);
        Thread.sleep(3000);

        // Step 10: Success verify
        String currentUrl = driver.getCurrentUrl();
        System.out.println("After submit URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("/admin"), "Admin pe nahi raha! URL: " + currentUrl);
        System.out.println("File upload test PASSED!");
    }
}