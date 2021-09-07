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

    public static JsonObject createProject() throws IOException {
        JsonObject project = new JsonObject();
        project.addProperty("name",
                "TestCon Workshop Project " + new Date().toString());
        project.addProperty("description", "TestCon Description");
        project.addProperty("creation_template", 1);
        project.addProperty("is_private", false);
        return createProject(project);
    }

    public static void deleteProject(JsonObject project) {
        Client.delete("/projects/", project);
    }

    public static JsonObject getCurrentUser() throws IOException {
        Response response;
        response = Client.get("/users/me");
        String jsonString = response.body().string();
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }
}
