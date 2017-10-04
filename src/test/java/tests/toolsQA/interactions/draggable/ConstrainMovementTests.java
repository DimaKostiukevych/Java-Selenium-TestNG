package tests.toolsQA.interactions.draggable;

import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.toolsQA.interactions.draggable.ConstrainMovementPage;
import tests.GUITest;


public class ConstrainMovementTests extends GUITest {

    private ConstrainMovementPage cmp;

    @BeforeClass
    public void openPage(){
        cmp = new ConstrainMovementPage(driver);
    }

    @Test
    public void verifyPage(){
        Assert.assertEquals("Draggable | Demoqa",cmp.getPageTitle());
    }

    @Test(priority = 1)
    public void verifyMoveOnlyVerticaly(){
        Point start = cmp.getVerticalEleCoords();
        cmp.moveVerticalEle();
        Point end = cmp.getVerticalEleCoords();
        boolean cordschange = start.getX() == end.getX() && end.getY() > start.getY();
        Assert.assertTrue(cordschange);
    }

    @Test(priority = 2)
    public void verifyMoveOnlyHorizontaly(){
        Point start = cmp.getHorizontalEleCoords();
        cmp.moveHorizontalEle();
        Point end = cmp.getHorizontalEleCoords();
        boolean cordschange = start.getX() < end.getX() && end.getY() == start.getY();
        Assert.assertTrue(cordschange);
    }

    @Test(priority = 3)
    public void verifyInsideDomElementBoundries(){

        cmp.moveInsideDomEle();
        Assert.assertTrue(cmp.isInsideDomEleWithinContainer());

    }

    @Test(priority = 4)
    public void verifyInsideParentElementBoundries(){
        cmp.moveInsideParentEle();
        Assert.assertTrue(cmp.isInsideParentEleWithingContainer());
    }
}
