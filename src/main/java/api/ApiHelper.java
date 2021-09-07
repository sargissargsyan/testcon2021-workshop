package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Response;

import java.io.IOException;
import java.util.Date;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class ApiHelper {

    public static JsonObject createProject(JsonObject jsonProject) throws IOException {
        Response response;
        response = Client.post("/projects", jsonProject);
        String jsonString = response.body().string();
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

    public static JsonObject createIssue(JsonObject jsonIssue) throws IOException {
        Response response;
        response= Client.post("/issues", jsonIssue);
        String jsonString = response.body().string();
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

    public static JsonObject createProject() throws IOException {
        JsonObject project = new JsonObject();
        project.addProperty("name",
                "TestCon Workshop Project " + new Date().toString());
        project.addProperty("description", "TestCon Description");
        project.addProperty("creation_template", 1);
        project.addProperty("is_private", false);
        return createProject(project);
    }

    public static JsonObject createIssue(int projectId) throws IOException {
        JsonObject issue = new JsonObject();
        issue.addProperty("subject", "TestCon Issue");
        issue.addProperty("project", projectId);
        issue.addProperty("description", "Test Description");
        return createIssue(issue);
    }

    public static void deleteProject(JsonObject project) {
        Client.delete("/projects/", project);
    }
    public static void deleteIssue(JsonObject issue) {
        Client.delete("/issues/", issue);
    }

    public static JsonObject getCurrentUser() throws IOException {
        Response response;
        response = Client.get("/users/me");
        String jsonString = response.body().string();
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

}
