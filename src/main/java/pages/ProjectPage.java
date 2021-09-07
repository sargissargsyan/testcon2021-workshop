package pages;

import base.BasePage;
import com.google.gson.JsonObject;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class ProjectPage extends BasePage {
    JsonObject project;
    public ProjectPage(JsonObject project) {
        this.project = project;
        driver.get(getUrl());
    }
    @Override
    public String getUrl() {
        return BASE_URL + "/project/" + project.get("slug").getAsString();
    }
}
