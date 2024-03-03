package ua.kolesnyk.automation.hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Task1 extends ua.kolesnyk.practice.l5.BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void waiter() {
        String searchValue = "тумба";

        webDriver.get("https://prozorro.gov.ua/en");
        WebElement input = webDriver.findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(searchValue);
        input.sendKeys(Keys.ENTER);

        List<WebElement> headerElement = webDriver.findElements(By.xpath("//li[1][@ class='search-result-card__wrap']/div/div/div/div/a[@class='item-title__title']"));
        String searchName = headerElement.get(0).getText();
        String link = headerElement.get(0).getAttribute("href");

        List<WebElement> priceElement = webDriver.findElements(By.xpath("//li[1][@ class='search-result-card__wrap']/div/div/div [@ class='search-result-card__col search-result-card__col_second']/div/ p[@ class='c_success app-price__amount']"));
        String searchPrice = priceElement.get(0).getText();

        List<WebElement> statusElement = webDriver.findElements(By.xpath("//li[1][@ class='search-result-card__wrap']/div/div/div/p/span [@class='_setter __v_isRef __v_isReadonly effect _cacheable']"));
        String searchStatus = statusElement.get(0).getText();

        List<WebElement> companyElement = webDriver.findElements(By.xpath("//li[1][@ class='search-result-card__wrap']/div/div/div/div[@ class='search-result-card__description']"));
        String searchCompany = companyElement.get(0).getText();

        headerElement.get(0).click();

        webDriver.get(link);
        List<WebElement> headerResult = webDriver.findElements(By.xpath("//div [@ class='tender--head--title col-sm-9']"));
        String resultName = headerResult.get(0).getText();

        List<WebElement> priceResult = webDriver.findElements(By.xpath("//div [@ class='green tender--description--cost--number']/strong"));
        String resultPrice = priceResult.get(0).getText();

        List<WebElement> statusResult = webDriver.findElements(By.xpath("//span [@ class='marked']"));
        String resultStatus = statusResult.get(0).getText();

        List<WebElement> companyResult = webDriver.findElements(By.xpath("//td [@ class='col-sm-6']"));
        String resultCompany = companyResult.get(0).getText();

        softAssert.assertEquals(searchName, resultName, "Name value is incorrect");
        softAssert.assertEquals(searchStatus, resultStatus, "Status value is incorrect");
        softAssert.assertEquals(searchPrice, resultPrice, "Price value is incorrect");
        softAssert.assertEquals(searchCompany, resultCompany, "Company name value is incorrect");
        softAssert.assertAll();

    }
}