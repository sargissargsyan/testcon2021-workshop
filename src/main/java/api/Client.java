package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class Client {
    private static final String BASE_URL = "https://api.taiga.io/api/v1";
    private static String accessToken = null;

    public static JsonObject login(String email, String password) {
        OkHttpClient client = new OkHttpClient();

        JsonObject bodyJson = new JsonObject();
        bodyJson.addProperty("username", email);
        bodyJson.addProperty("password", password);
        bodyJson.addProperty("type", "normal");

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, bodyJson.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + "/auth")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        JsonObject responseObject = null;
        try {
            Response response = client.newCall(request).execute();
            String responseJson = response.body().string();
            responseObject = JsonParser.parseString(responseJson).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        accessToken = responseObject.get("auth_token").getAsString();
        return responseObject;
    }

    public static Response post(String url, JsonObject jsonObject) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonObject.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        if (!response.isSuccessful()) {
            throw new Error("HTTP error Code: " + response.code());
        }
        return response;
    }

    public static void delete(String url, JsonObject object) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + url + object.get("id").getAsString())
                .delete()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        if (!response.isSuccessful()) {
            throw new Error("HTTP error Code: " + response.code());
        }
    }

    public static Response get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + url)
                .get()
                .addHeader("Content-type", "application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        if(!response.isSuccessful()) {
            throw  new Error("HTTP error code: " + response.code());
        }
        return response;
    }

}
