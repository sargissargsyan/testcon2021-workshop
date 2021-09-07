package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Response;

import java.io.IOException;

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
}
