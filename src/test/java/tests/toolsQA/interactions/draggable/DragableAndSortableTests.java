package tests.toolsQA.interactions.draggable;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.toolsQA.interactions.draggable.DraggableAndSortablePage;
import tests.GUITest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by maciej on 04.10.2017.
 */
public class DragableAndSortableTests extends GUITest {

    private DraggableAndSortablePage dsp;


    @BeforeClass
    public void setupPage(){
        dsp = new DraggableAndSortablePage(driver);
    }

    @Test
    public void checkPage(){
        Assert.assertEquals("Draggable | Demoqa",dsp.getPageTitle());
    }

    @Test(priority = 1)
    public void checkInitialOrder(){
        List<WebElement> list = dsp.getListItems();
        int counter = 1;
        for(WebElement ele : list){

            String label = "Item "+counter;
            Assert.assertEquals(label,ele.getText());
            counter++;
        }
    }

    @Test(priority = 2)
    public void checkChangedOrder(){

    }


    @Test(priority = 3)
    public void checkOrderWithDropeDown(){
        dsp.moveDragMeDownToList();

        List<String> neworder = Arrays.asList("Item 1", "Item 2", "Item 3", "Drag me down",  "Item 4", "Item 5");
        List<WebElement> orderlist = dsp.getListItems();


        for(int i = 0;i<orderlist.size();i++){
           Assert.assertEquals(neworder.get(i),orderlist.get(i).getText());
       }

    }

}
