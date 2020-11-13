package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {

    private static WebDriver driver;
    private String URL = "https://umlify.com";


    public static void setUpChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/blackflash/Documents/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.setHeadless(true);
        chromeOptions.addArguments("['start-maximized']");
        driver = new ChromeDriver(chromeOptions);

    }

    public static void setUpFirefoxWebDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static void teardownWebDriver() {
        driver.close();
    }
}
