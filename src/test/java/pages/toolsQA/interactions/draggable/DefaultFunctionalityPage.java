package pages.toolsQA.interactions.draggable;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BasicPage;


public class DefaultFunctionalityPage extends BasicPage {

        private static final By dragsource = By.id("draggable");

        public DefaultFunctionalityPage(WebDriver driver) {
                this.driver = driver;
                driver.get("http://demoqa.com/draggable/");
                waitPageLoad();
        }

        public Point getElementCoords(){
                return getElement(dragsource).getLocation();
        }

        public String getPageTitle(){
              return driver.getTitle();
        }

        public void moveElement(){
                new Actions(driver).dragAndDropBy(getElement(dragsource),50,50).build().perform();
        }




}
