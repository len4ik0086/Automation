package ua.kolesnyk.practice.l5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WaitsTests extends BaseTest {

    @Test
    public void waiter() {
        String searchValue = "вішак";

        webDriver.get("https://prozorro.gov.ua/en");
        WebElement input = webDriver.findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(searchValue);
        input.sendKeys(Keys.ENTER);

        List<WebElement> itemHeaders = webDriver.findElements(By.xpath("//div[@class='search-result-card']//a[@class='item-title__title']"));

        itemHeaders.get(0).click();
        String actualResult = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//div[contains(@class,'tender--head--title')]")))
                .getText().trim();

        Assert.assertEquals(actualResult, searchValue, "Search value is incorrect");
    }
}