package ua.kolesnyk.practice.l5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {
    public WebDriver webDriver;
    @BeforeMethod
    public void  initDriver()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,720");

        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @AfterMethod
    public void destroy() {
        webDriver.quit();
    }
}
