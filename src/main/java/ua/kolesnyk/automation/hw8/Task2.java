package ua.kolesnyk.automation.hw8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class Task2 {

    @BeforeClass
    public void InitBaseURL() {
        RestAssured.baseURI = "https://api.github.com/";
    }

    @Test
    public void keyCheck() {
        var response = when().get("emojis").then()
                .contentType(ContentType.JSON)
                .extract().response().jsonPath().getString("articulated_lorry");
        System.out.println(response);
        Assert.assertNotNull(response, "Defined key - 'articulated_lorry' is absent");
    }
}
