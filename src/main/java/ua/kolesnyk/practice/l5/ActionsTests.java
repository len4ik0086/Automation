package ua.kolesnyk.practice.l5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ActionsTests extends BaseTest {
    @Test
    public void dragAndDrop() {

        webDriver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
        WebElement header = webDriver.findElement(By.id("header"));
        WebElement capitalBox = webDriver.findElement(By.id("box1"));
        WebElement country = webDriver.findElement(By.id("box106"));

        Actions action = new Actions(webDriver);
        action.dragAndDrop(capitalBox,country).build().perform();

    }
}
