package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class DriverHelper {

    public WebDriver driver;
    private static ThreadLocal<WebDriver> driverThread  = new ThreadLocal<>();
    private ChromeOptions options;


}
