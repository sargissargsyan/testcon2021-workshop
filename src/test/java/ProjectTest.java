import api.ApiHelper;
import api.Client;
import com.google.gson.JsonObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.ProjectPage;

import java.io.IOException;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class ProjectTest extends SeleniumBase {
    JsonObject project;

    @Test
    public void projectPage() throws IOException {
        Client.login("testcon2021@gmail.com", "Armenia2021");
        project = ApiHelper.createProject();
        login("testcon2021@gmail.com", "Armenia2021");
        ProjectPage projectPage = new ProjectPage(project);
    }

    @AfterMethod
    public void tearDown() {
        if (project != null) {
            ApiHelper.deleteProject(project);
        }
    }
}
