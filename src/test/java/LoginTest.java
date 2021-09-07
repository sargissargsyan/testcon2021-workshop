import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class LoginTest extends SeleniumBase {
    @Test
    public void loginViaUI() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("testcon2021@gmail.com", "Armenia2021");
    }

    @Test
    public void loginViaApi() {
        login("testcon2021@gmail.com", "Armenia2021");
    }
}

