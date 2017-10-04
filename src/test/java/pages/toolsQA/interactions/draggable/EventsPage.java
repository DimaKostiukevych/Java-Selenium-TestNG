package pages.toolsQA.interactions.draggable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BasicPage;



public class EventsPage extends BasicPage {

    private final By events_label = By.xpath("//li[@aria-labelledby='ui-id-4']/a");
    private final By start_counter = By.xpath("//li[@id='event-start']/span[2]");
    private final By drag_counter = By.xpath("//li[@id='event-drag']/span[2]");
    private final By stop_counter = By.xpath("//li[@id='event-stop']/span[2]");
    private final By container = By.id("dragevent");

    private Actions action;


    public EventsPage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demoqa.com/draggable/");
        waitPageLoad();
        getElement(events_label).click();
        action = new Actions(driver);
    }

    public int getStartCounter(){
        return Integer.parseInt(getElement(start_counter).getText());
    }

    public int getDragCounter(){
        return Integer.parseInt(getElement(drag_counter).getText());
    }

    public int getStopCounter(){
        return Integer.parseInt(getElement(stop_counter).getText());
    }

    public void startClickAndHold(){
        action.clickAndHold(getElement(container)).moveByOffset(1,0).build().perform();
    }

    public void moveContainerByOffset(int x, int y){
        action.clickAndHold(getElement(container)).moveByOffset(x,y).build().perform();
        new Actions(driver).moveByOffset(100,100).build().perform();
    }

    public void releaseMouse(){
        action.release().build().perform();
    }

    public String getPageTitle(){
       return driver.getTitle();
    }

}
