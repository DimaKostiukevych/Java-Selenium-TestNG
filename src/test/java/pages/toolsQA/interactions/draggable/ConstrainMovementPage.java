package pages.toolsQA.interactions.draggable;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import pages.BasicPage;

import java.util.Random;

/**
 * Created by maciej on 04.10.2017.
 */
public class ConstrainMovementPage extends BasicPage {

    private final By constrain_tab = By.xpath("//li[@aria-labelledby='ui-id-2']");
    private final By axis_vertical = By.id("draggabl");
    private final By axis_horizontal = By.id("draggabl2");
    private final By inside_dom = By.id("draggabl3");
    private final By container = By.id("containment-wrapper");
    private final By inside_parent = By.id("draggabl5");
    private final By parent = By.xpath("//div[@class='draggable ui-widget-content']");


    public ConstrainMovementPage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demoqa.com/draggable/");
        waitPageLoad();
        getElement(constrain_tab).click();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public Point getVerticalEleCoords(){
        return getElement(axis_vertical).getLocation();
    }

    public Point getHorizontalEleCoords(){
        return getElement(axis_horizontal).getLocation();
    }

    public void moveVerticalEle(){
        new Actions(driver).dragAndDropBy(getElement(axis_vertical),50,50).build().perform();
    }

    public void moveHorizontalEle(){
        new Actions(driver).dragAndDropBy(getElement(axis_horizontal),100,100).build().perform();
    }

    public Point getInsideDomCoords(){
        return getElement(inside_dom).getLocation();
    }

    public void moveInsideDomEle(){
        Random r = new Random();

        for(int i = 0;i<5;i++){
            new Actions(driver).clickAndHold(getElement(inside_dom)).moveByOffset(200,r.nextInt(150)).release().build().perform();
        }
    }

    public WebElement getContainer(){
        return getElement(container);
    }

    private boolean isInContainer(WebElement ele, WebElement container){

        Point p = ele.getLocation();

        int x1 = container.getLocation().getX();
        int x2 = container.getLocation().getX() + container.getSize().getWidth();

        int y1 = container.getLocation().getY();
        int y2 = container.getLocation().getY() + container.getSize().getHeight();

        int px = p.getX();
        int py = p.getY();

        boolean inBoundries = px >= x1 && px <= x2 && py >= y1 && py <= y2;

        return inBoundries;
    }

    public boolean isInsideDomEleWithinContainer(){
        return isInContainer(getElement(inside_dom),getContainer());
    }

    public void moveInsideParentEle(){
        new Actions(driver).clickAndHold(getElement(inside_parent)).moveByOffset(50,70).release().build().perform();
    }

    public boolean isInsideParentEleWithingContainer(){
        return isInContainer(getElement(inside_parent),getElement(parent));
    }

}
