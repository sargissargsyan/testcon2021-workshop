import api.ApiHelper;
import api.Client;
import com.google.gson.JsonObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.IssuePage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class IssueTest extends SeleniumBase {
    JsonObject project;
    JsonObject issue;

    @Test
    public void issuePageNewStatus() throws IOException {
        Client.login("testcon2021@gmail.com", "Armenia2021");
        project = ApiHelper.createProject();
        issue = ApiHelper.createIssue(project.get("id").getAsInt());
        IssuePage issuePage = new IssuePage(issue);

        assertEquals(issuePage.getIssueInnerStatus(), "NEW", "Issue Status was not correct!");
    }
    @Test
    public void issuePagePostponedStatus() throws IOException {
        Client.login("testcon2021@gmail.com", "Armenia2021");
        project = ApiHelper.createProject();
        JsonObject issue = new JsonObject();
        issue.addProperty("subject", "TestCon Issue");
        issue.addProperty("project", project.get("id").getAsString());
        issue.addProperty("description", "Test Description");
        issue.addProperty("assigned_to",
                ApiHelper.getCurrentUser().get("id").getAsString());
        ApiHelper.createIssue(issue);
        issue = ApiHelper.createIssue(issue);
        IssuePage issuePage = new IssuePage(issue);

        assertEquals(issuePage.getIssueAssignee(), "testcon2021", "Issue Status was not correct!");
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
