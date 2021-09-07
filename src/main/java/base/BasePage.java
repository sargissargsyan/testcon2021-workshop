package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public abstract class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverHelper.get().getDriver();
        PageFactory.initElements(driver, this);
    }

    public abstract String getUrl();



}
