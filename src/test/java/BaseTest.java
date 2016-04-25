import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.UUID;
import java.nio.file.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends Wrappers {



    protected  WebDriver setChromeDriver(){
        String pathToChromeDriver = Paths.get("./src/ChromeDriver/chromedriver.exe").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriver chromeDriver = new ChromeDriver(options);
        return chromeDriver;
    }

    WebDriver driver;

    //public static int timeout = 10;

    static protected String getUniqueValue(){
        return UUID.randomUUID().toString().substring(1, 7);
    }

    static protected String getUniqueId(){
        int var  = new Random(System.currentTimeMillis()).nextInt(9999999 - 111);
        return String.valueOf(var);
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp(){
        driver = setChromeDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
