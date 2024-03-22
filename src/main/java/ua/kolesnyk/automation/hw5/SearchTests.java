package ua.kolesnyk.automation.hw5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchTests extends ua.kolesnyk.example.BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @DataProvider(name = "searchValues")
    public Object[][] searchData() {
        return new Object[][]{
                {"меблі"},
                {"тумба"},
                {"майдан"},

        };
    }

    @Test(dataProvider = "searchValues")
    public void searchValueCompareInDifferentResults(String searchValue) {

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

        List<WebElement> companyResult = webDriver.findElements(By.xpath("// table [@ class='tender--customer margin-bottom']/tbody/tr[1]/td [@ class='col-sm-6']"));
        List<WebElement> cityResult = webDriver.findElements(By.xpath("// table [@ class='tender--customer margin-bottom']/tbody/tr[3]/td [@ class='col-sm-6']"));
        String[] cityParts = cityResult.get(0).getText().split(",");
        String resultCompany = companyResult.get(0).getText() + " • " + cityParts[3].trim();

        softAssert.assertEquals(searchName, resultName, "Name value is incorrect");
        softAssert.assertEquals(searchStatus, resultStatus, "Status value is incorrect");
        softAssert.assertEquals(searchPrice, resultPrice, "Price value is incorrect");
        softAssert.assertEquals(searchCompany, resultCompany, "Company name value is incorrect");
        softAssert.assertAll();

    }

    @Test
    public void positiveSearchFieldIsAvailable() {

        webDriver.get("https://prozorro.gov.ua/en");
        int countXpath = webDriver.findElements(By.xpath("//input[contains(@class,'search')]")).size();

        Assert.assertEquals(countXpath, 1, "Search field is absent");
    }

    @Test
    public void positiveSearchWithEmptyValue() {
        String searchValue = "";

        webDriver.get("https://prozorro.gov.ua/en");
        WebElement input = webDriver.findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(searchValue);
        input.sendKeys(Keys.ENTER);

        int countXpath = webDriver.findElements(By.xpath("//li [@ class='search-result-card__wrap']/div/div/div/div/a[@class='item-title__title']")).size();

        Assert.assertNotEquals(countXpath, 0, "Should find all results");
    }

    @Test
    public void positiveSearchBarAbilityAfterClick() {

        webDriver.get("https://prozorro.gov.ua/en");
        WebElement input = webDriver.findElement(By.xpath("//label [@ for='buyer']"));
        input.click();
        ;


        int countXpath = webDriver.findElements(By.xpath("//input[@ id='buyer']")).size();

        Assert.assertNotEquals(countXpath, 0, "Search bar unavailable for Buyer section");
    }

    @DataProvider(name = "searchSymbols")
    public Object[][] searchSymbolData() {
        return new Object[][]{
                {"#"},
                {"@"},
                {"%"},

        };
    }

    @Test(dataProvider = "searchSymbols")
    public void negativeSearchWithSpecialSymbols(String searchValue) {

        webDriver.get("https://prozorro.gov.ua/en");
        WebElement input = webDriver.findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(searchValue);
        input.sendKeys(Keys.ENTER);

        int countXpath = webDriver.findElements(By.xpath("//li [@ class='search-result-card__wrap']/div/div/div/div/a[@class='item-title__title']")).size();

        Assert.assertEquals(countXpath, 0, "Should not search with: " + searchValue);
    }

}