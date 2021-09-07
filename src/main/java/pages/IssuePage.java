package pages;

import api.ApiHelper;
import base.BasePage;
import base.WaitHelper;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

/**
 * @author Sargis Sargsyan on 9/7/21
 * @project testcon2021
 */
public class IssuePage extends BasePage {
    @FindBy(css = ".detail-status-inner")
    private WebElement issueStatus;

    @FindBy(css = ".user-list-single")
    private WebElement assignee;

    JsonObject issue;
    public IssuePage(JsonObject issue) throws IOException {
        this.issue = issue;
        driver.get(getUrl());
        WaitHelper.getWait().waitForElementToBeVisible(By.cssSelector(".detail-title-text"));
    }

    @Override
    public String getUrl() throws IOException {
        JsonObject project = ApiHelper.getProject(issue.get("project").getAsInt());
        String slugSting = project.get("slug").getAsString();
        return BASE_URL + "/project/" + slugSting + "/issue/"
                + issue.get("ref").getAsString();
    }

    public String getIssueInnerStatus() {
        return issueStatus.getText();
    }

    public String getIssueAssignee() {
        return assignee.getText();
    }
}
