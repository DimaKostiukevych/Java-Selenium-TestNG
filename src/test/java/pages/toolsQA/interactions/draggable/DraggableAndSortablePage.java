package pages.toolsQA.interactions.draggable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasicPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by maciej on 04.10.2017.
 */
public class DraggableAndSortablePage extends BasicPage {

    private final By draggable_and_sortable_label = By.xpath("//li[@aria-labelledby='ui-id-5']/a");
    private final By list_selector = By.xpath("//ul[@id='sortablebox']/li");
    private final By drag_me_down = By.id("draggablebox");
    private final By item3 = By.xpath("//li[text()='Item 3']");
    private final By item5 = By.xpath("//li[text()='Item 5']");


    public DraggableAndSortablePage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demoqa.com/draggable/");
        getElement(draggable_and_sortable_label).click();
        waitPageLoad();
    }

    public List<WebElement> getListItems(){
        return getElements(list_selector);
    }

    public void moveDragMeDownToList(){
        dragAndDropOnElementWithOffset(drag_me_down,item3,0,8);
        sleep(2, TimeUnit.SECONDS);
    }

    public void changeItemOrder(){
        dragAndDropOnElementWithOffset(item3,item5,0,8);
        sleep(2,TimeUnit.SECONDS);
    }

}
