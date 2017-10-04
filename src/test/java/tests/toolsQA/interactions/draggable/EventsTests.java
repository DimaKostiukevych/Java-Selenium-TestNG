package tests.toolsQA.interactions.draggable;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.toolsQA.interactions.draggable.EventsPage;
import tests.GUITest;


public class EventsTests extends GUITest {

    EventsPage ep;

    @BeforeClass
    public void openPage(){
        ep = new EventsPage(driver);
    }

    @Test(priority = 0)
    public void verifyPage(){
        Assert.assertEquals("Draggable | Demoqa",ep.getPageTitle());
    }

    @Test(priority = 1)
    public void counterCheck(){

    }

}
