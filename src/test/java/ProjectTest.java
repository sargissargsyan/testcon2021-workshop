import api.ApiHelper;
import api.Client;
import com.google.gson.JsonArray;
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
    JsonObject issue;

    @Test
    public void projectPage() throws IOException {
        Client.login("testcon2021@gmail.com", "Armenia2021");
        project = ApiHelper.createProject();
        issue = ApiHelper.createIssue(project.get("id").getAsInt());
        JsonArray allProjects = ApiHelper.getAllProjects();
        login("testcon2021@gmail.com", "Armenia2021");
        ProjectPage projectPage = new ProjectPage(project);
    }

    @Test
    public void assignIssue() throws IOException {
        Client.login("testcon2021@gmail.com", "Armenia2021");
        project = ApiHelper.createProject();
        JsonObject issue = new JsonObject();
        issue.addProperty("subject", "TestCon Issue");
        issue.addProperty("project", project.get("id").getAsString());
        issue.addProperty("description", "Test Description");
        issue.addProperty("assigned_to",
                ApiHelper.getCurrentUser().get("id").getAsString());
        ApiHelper.createIssue(issue);
        ProjectPage projectPage = new ProjectPage(project);


    }

    @AfterMethod
    public void tearDown() {
        if (issue != null) {
            ApiHelper.deleteIssue(issue);
        }
        if (project != null) {
            ApiHelper.deleteProject(project);
        }
    }
}
