package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://tree.taiga.io";

    public BasePage() {
        this.driver = DriverHelper.get().getDriver();
        PageFactory.initElements(driver, this);
    }

    public abstract String getUrl() throws IOException;



}
