package org.notion.settingsAndMembers.workspace;

import org.notion.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TeamspaceManagementPageTest extends BaseTest {
    protected TeamspaceManagementPage teamspaceManagementPage;
    @BeforeMethod
    protected void initializeSettingsAndMembersPage(){
        teamspaceManagementPage = PageFactory.initElements(driver, TeamspaceManagementPage.class);
    }
    @DataProvider(name = "teamspaceData")
    public Object[][] provideTeamspaceData() {
        return new Object[][] {
                { "Teamspace1", "Description for Teamspace1", "Closed" },
                { "Teamspace2", "Description for Teamspace2", "Default" },
        };
    }

    @Test(priority = 1, dataProvider = "teamspaceData")
    public void shouldCreateAndVerifyNewTeamspace(String name, String description, String status){
        teamspaceManagementPage.navigateToTeamspaceSection();

        teamspaceManagementPage.createNewTeamspace(name, description, status);
        Assert.assertTrue(teamspaceManagementPage.isTeamspacePresent(name));
    }
}
