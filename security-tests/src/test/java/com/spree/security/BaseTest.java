package com.spree.security;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String BASE_URL      = "http://localhost:3000";
    protected static final String LOGIN_URL     = BASE_URL + "/user/sign_in";
    protected static final String SIGNUP_URL    = BASE_URL + "/user/sign_up";
    protected static final String LOGOUT_URL    = BASE_URL + "/user/sign_out";
    protected static final String PRODUCTS_URL  = BASE_URL + "/products";
    protected static final String ACCOUNT_URL   = BASE_URL + "/account";
    protected static final String ADMIN_URL     = BASE_URL + "/admin";

    protected static final String TEST_EMAIL    = "test_account@example.com";
    protected static final String TEST_PASSWORD = "password123";

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}