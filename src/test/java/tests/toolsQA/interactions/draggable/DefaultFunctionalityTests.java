package tests.toolsQA.interactions.draggable;


import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.toolsQA.interactions.draggable.DefaultFunctionalityPage;
import tests.GUITest;


public class DefaultFunctionalityTests extends GUITest {

    DefaultFunctionalityPage dfp;

    @BeforeClass
    public void openSite(){
        dfp = new DefaultFunctionalityPage(driver);
    }

    @Test(priority = 0)
    public void verifyPage(){
        Assert.assertEquals("Draggable | Demoqa",dfp.getPageTitle());
    }

    @Test(priority = 1)
    public void moveElementAndCheckCoords(){
        Point start = dfp.getElementCoords();
        dfp.moveElement();
        Assert.assertNotEquals(start,dfp.getElementCoords());
    }


}
