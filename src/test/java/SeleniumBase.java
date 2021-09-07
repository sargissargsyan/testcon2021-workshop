import api.Client;
import base.DriverHelper;
import com.google.gson.JsonObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import pages.LoginPage;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class SeleniumBase {
    private WebDriver driver = DriverHelper.get().getDriver();


    public void login(String email, String password) {
        JsonObject userJson = Client.login(email, password);
        new LoginPage();
        ((JavascriptExecutor) driver)
                .executeScript("window.localStorage.setItem('token','" +
                        userJson.get("auth_token") + "');");
        ((JavascriptExecutor) driver)
                .executeScript("window.localStorage.setItem('userInfo','" +
                        userJson + "');");
    }

    @AfterMethod
    public void cleanUpBase() {
        DriverHelper.get().quitDriver(DriverHelper.get().getDriver());
    }
}
