package com.spree.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;

public class MobileChromeTest {

    @Test
    public void openChrome() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("browserName", "Chrome");

        AndroidDriver driver =
                new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

        driver.get("http://10.0.2.2:3000");

        Thread.sleep(5000);

        driver.quit();
    }
}