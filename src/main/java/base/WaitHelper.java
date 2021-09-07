package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class WaitHelper {
    public static WaitHelper getWait(){
        return new WaitHelper();
    }

    public void waitForElementToBeVisible(By location) {
        new WebDriverWait(DriverHelper.get().getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(location));
    }

    public void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(DriverHelper.get().getDriver(), 10)
                .until(ExpectedConditions.visibilityOf(element));
    }
}
