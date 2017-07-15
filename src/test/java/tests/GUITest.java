package tests;


import commons.ParametersReaders;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 *  Main class for GUITests, all of test classes should inherit this as superclass, we have implemented here basics setup for each Selenium test such as setting up browser based on properties
 *  file , tearing up browser after tests, saving screenshots on test fail, etc.
 *  Several browsers are avaible by setting up parameter: Chrome, Firefox, PhantomJS, HtmlUnit
 *  I've tried to make this main test class cross platform, for different OS (Windos & Linux) webdrivers need to be set up separated.
 *  This class Assumes that in Your TestNG project, classes will hold up test logic from begining of opening browsers to its close.
 */
public class GUITest {

    protected WebDriver driver;
    private final static String BROWSER = ParametersReaders.getPropByName("browser");

    @BeforeClass
    protected void setUpBrowser(){
        setUpWebDriverBasedOnOS();

        switch (BROWSER){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "PhantomJS":
                driver = new PhantomJSDriver();
                break;
            case "HtmlUnit":
                driver = new HtmlUnitDriver();
                break;
        }

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    protected void tearUpBrowser(){
        driver.manage().deleteAllCookies();
        driver.close();
    }


    private void setUpWebDriverBasedOnOS(){
        if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC){
            System.setProperty("webdriver.chromne.driver","WebDrivers/linux/chromedriver");
            System.setProperty("webdriver.gecko.driver", "WebDrivers/linux/geckodriver");
            System.setProperty("phantomjs.binary.path", "WebDrivers/linux/phantomjs");

        }
        if(SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver","WebDrivers/windows/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "WebDrivers/windows/geckodriver.exe");
            System.setProperty("phantomjs.binary.path", "WebDrivers/windows/phantomjs.exe");
        }
    }


}
