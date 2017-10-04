package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * Created by maciej on 15.07.2017.
 */
public class PlaygroundTest  extends GUITest{

    private static final By src = By.id("draggableview");
    private static final By trg = By.id("droppableview");

    @Test
    public void simpleGoogleTest(){
        driver.get("http://demoqa.com/resizable/");
        WebElement resize = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));

        WebElement resizable = driver.findElement(By.id("resizable"));

        System.out.println(resizable.getSize().toString());

        new Actions(driver).clickAndHold(resize).moveByOffset(300,300).release().build().perform();

        System.out.println(resizable.getSize().toString());
    }



}
