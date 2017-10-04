package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Basic Page with "boosted" up Selenium methods, making operating on DOM more stable, methods have been implemented with conditional waiting and other
 * mechanism helping operating on WebElement without throwing Exceptions. The idea is to each page used in tests (Page Object approach) should inherit
 * from this BasicPage class.
 */
public class BasicPage {

    protected WebDriver driver;

    /**
     * Get element using conditional waiting and custom timeout.
     *
     * @param by     By selector of WebElement
     * @param timout Timeout in seconds
     * @return WebElement when found or null
     */
    protected WebElement getElement(By by, int timout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timout);

        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (WebDriverException e) {
            System.out.printf("Can't find element by: %s", by.toString());
        }
        return element;
    }

    /**
     * Get element using conditional waiting with timeout of 5 seconds
     *
     * @param by By selector of WebElement
     * @return WebElement when found or null
     */
    protected WebElement getElement(By by) {
        return getElement(by, 5);
    }


    /**
     * Safe return page title after waiting page to load
     *
     * @return Page Title
     */
    public String getPageTitle() {
        waitPageLoad();
        return driver.getTitle();
    }


    protected List<WebElement> getElements(By by, int timeout) {
        List<WebElement> elements = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            elements = driver.findElements(by);
        } catch (Exception e) {
            System.out.println("Couldn't found elements");
        }
        return elements;
    }

    protected List<WebElement> getElements(By by) {
        return getElements(by, 10);
    }

    /**
     * Make WebDriver wait for page to load, mechanism is implemented using JavascriptExecutor which check
     * statement 'document.readyState' every 300 milliseconds for time passed in parameter until it return value "complete"
     *
     * @param time Time in seconds
     */
    protected void waitPageLoad(int time) {

        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis() + time * 1000;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            while (start < end) {
                String result = (String) js.executeScript("return document.readyState;");
                if (result.equals("complete")) {
                    System.out.println("Page loaded");
                    return;
                }
                sleep(200, TimeUnit.MILLISECONDS);
                start = System.currentTimeMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Make WebDriver wait for page to load, mechanism is implemented using JavascriptExecutor which check
     * statement 'document.readyState' every 300 milliseconds for 5 seconds until it return value "complete"
     */

    protected void waitPageLoad() {
        waitPageLoad(5);
    }

    /**
     * Thread sleep according to value and TimeUnit passed as parameter
     *
     * @param value    Value of TimeUnit
     * @param timeunit TimeUnit
     */
    protected void sleep(int value, TimeUnit timeunit) {

        long time = timeunit.toMillis(value);

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean isVisible(By by, int timeout) {

        boolean visible = false;
        WebElement element;
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if(element != null){
                visible = true;
            }
        } catch (WebDriverException e) {
            System.out.printf("Can't find element by: %s", by.toString());
        }
        return visible;
}

    protected boolean isVisible(By by) {
        return isVisible(by, 5);

    }

    /**
     * Highlight WebElement with red border
     * @param ele WebElement to be highlighted
     */

    protected void  highlightElement(WebElement ele){
        executeJSOnElement(ele,"style.border='10px solid red'");
    }

    /**
     * Execute Javascript statement on selected WebElement, i.e modifying styles.
     * @param ele WebElement on which js will be executed
     * @param script Javascript statement
     */
    protected void executeJSOnElement( WebElement ele, String script){
        WebElement element = ele;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("arguments[0].%s", script), element);
    }

    /**
     * Drag and drop element from source onto target
     * @param source By selector of source WebElement
     * @param target By selector of target WebElement
     */
    protected void dragAndDropOnElement(By source, By target){
        WebElement src = getElement(source);
        WebElement trg = getElement(target);

        (new Actions(driver)).dragAndDrop(src,trg).build().perform();
    }

    /**
     * Drag and drop source element onto target element with x,y offset. Staring coordinates point to upper left corner of element.
     * @param source By selector of source WebElement
     * @param target By selector of target WebElement
     * @param x X axis offset
     * @param y Y axis offset
     */
    protected void dragAndDropOnElementWithOffset(By source, By target,int x, int y){
        WebElement src = driver.findElement(source);
        WebElement trg = driver.findElement(target);

        int xoffset = trg.getLocation().getX() - src.getLocation().getX() + x;
        int yoffset = trg.getLocation().getY() - src.getLocation().getY() + y;

        (new Actions(driver)).dragAndDropBy(src,xoffset,yoffset).build().perform();

    }



}
