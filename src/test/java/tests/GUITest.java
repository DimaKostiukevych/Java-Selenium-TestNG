package tests;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static commons.ParametersReaders.*;

/**
 * Main class for GUITests, all of test classes should inherit this as superclass, we have implemented here basics setup for each Selenium test such as setting up browser based on properties
 * file , tearing up browser after tests, saving screenshots on test fail, etc.
 * Several browsers are avaible by setting up parameter: Chrome, Firefox, PhantomJS, HtmlUnit
 * I've tried to make this main test class cross platform, for different OS (Windos & Linux) webdrivers need to be set up separated.
 * This class Assumes that in Your TestNG project, classes will hold up test logic from begining of opening browsers to its close.
 */
public class GUITest {

    protected WebDriver driver;
    private final static String BROWSER = getPropertyByName("browser");

    @BeforeClass
    protected void setUpBrowser() {
        setUpWebDriverBasedOnOS();

        switch (BROWSER) {
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

//    @AfterClass
//    protected void tearUpBrowser() {
//        driver.manage().deleteAllCookies();
//        driver.close();
//    }

    /**
     * Setting up system property with executable binaries for WebDrivers, It must been separated to different
     * directories because there is issue at least on Windows when You have linux binary and Windows executable
     * in one directory.
     */
    private void setUpWebDriverBasedOnOS() {
        if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC) {
            System.setProperty("webdriver.chromne.driver", "WebDrivers/linux/chromedriver");
            System.setProperty("webdriver.gecko.driver", "WebDrivers/linux/geckodriver");
            System.setProperty("phantomjs.binary.path", "WebDrivers/linux/phantomjs");

        }
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "WebDrivers/windows/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "WebDrivers/windows/geckodriver.exe");
            System.setProperty("phantomjs.binary.path", "WebDrivers/windows/phantomjs.exe");
        }
    }
    /**
     * Screenshot taken on test failure saved in ./screenshots directory. and attach to report as image
     * TODO: Move this functionality to listener and attach it in testng.xml config file
     *
     * There is strange behaviour when taking screenshots on chrome, on my local machine Windows, there is blank screenshots
     * when exeucting tests via Jenkins, something wrong with Jeknins as Windows Service
     *
     */
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File toFile = new File(".\\TestResults\\Screenshots\\" + testResult.getName() +
                    ".jpg");
            File sureFireFile = new File(".\\target\\surefire-reports\\Screenshots\\" + testResult.getName() +
                    ".jpg");
            FileUtils.copyFile(scrFile, toFile);
            FileUtils.copyFile(scrFile,sureFireFile);
            Reporter.setCurrentTestResult(testResult);
            Reporter.log("Method failed screenshot below: ");
            Reporter.log("<br> <a href=\"./Screenshots/"+testResult.getName()+".jpg\" >" +
                    "<img style=\"width:150px\" src=./Screenshots/"+testResult.getName()+".jpg /></a><br>");

        }
    }

        }

