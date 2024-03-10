package ua.kolesnyk.automation.hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task3 {

    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
    }

    @AfterMethod()
    public void destroy() {
        webDriver.quit();
    }

    @Test
    public void task3() {
        webDriver.get("https://rozetka.com.ua/");
        WebElement image = webDriver.findElement(By.xpath("//img[@ alt='Google Play']"));
        Assert.assertTrue(image.isDisplayed(), "Element is not opened successfully");
    }
}