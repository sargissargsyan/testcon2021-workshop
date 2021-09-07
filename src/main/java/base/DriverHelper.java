package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class DriverHelper {

    public WebDriver driver;
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private ChromeOptions options;

    public WebDriver getDriver() {
        if (driverThread.get() == null) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            options = new ChromeOptions();
            List<String> arguments = new ArrayList<>();
            arguments.add("--disable-notifications");
            options.addArguments(arguments);
            driver = new ChromeDriver(options);
            driverThread.set(driver);
        }
        return driverThread.get();
    }

    public void quitDriver(WebDriver driver) {
        driver.quit();
        driverThread.remove();
    }
}
