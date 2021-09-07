package pages;

import base.BasePage;
import base.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class LoginPage extends BasePage {
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = ".login-form button")
    private WebElement loginButton;

    public LoginPage() {
        driver.get(getUrl());
    }

    @Override
    public String getUrl() {
        return "https://tree.taiga.io/login";
    }

    public void login(String username, String password) {
        WaitHelper.getWait().waitForElementToBeVisible(By.name("username"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        WaitHelper.getWait().waitForElementToBeVisible(By.cssSelector(".user-avatar"));
    }
}
