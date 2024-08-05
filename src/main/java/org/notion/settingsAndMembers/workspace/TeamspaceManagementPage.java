package org.notion.settingsAndMembers.workspace;

import org.notion.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TeamspaceManagementPage extends WebPage {
    private static final String CSS_SETTINGS_AND_MEMBERS_LINK = "div.notion-sidebar > div:nth-child(3) > div > div:nth-child(2) > div[role='button']:last-child";
    private static final String CSS_TEAMSPACES_BUTTON = "div.notion-dialog > div > div:first-child > div > div > div > div:nth-child(12)";
    private static final String CSS_NEW_TEAMSPACE_BUTTON = "div.notion-new-team";
    private static final String CSS_TEAMSPACE_NAME_INPUT = "input[name='search']";
    private static final String CSS_TEAMSPACE_DESCRIPTION_INPUT = "textarea[placeholder='Details about your teamspace']";
    private static final String CSS_PERMISSIONS_TAB_BUTTON = "div.notion-focusable-within ~ div:nth-child(6)";
    private static final String CSS_PERMISSION_OPTIONS = "div[role='menuitem'] > div > div:last-child > div:first-child";
    private static final String XPATH_CREATE_TEAMSPACE_BUTTON = "//*[contains(text(),'Create teamspace')]";
    private static final String CSS_SEARCH_TEAMSPACE_INPUT = "input[placeholder='Search teamspaces...']";
    private static final String CSS_TEAMSPACE_SEARCH_RESULTS = "div.notion-record-icon.notranslate ~ div > div > div";
    private static final String CSS_SKIP_FOR_NOW_BUTTON = "div.notion-add-members-modal > div > div:last-child > div > div";

    @FindBy(css = CSS_SETTINGS_AND_MEMBERS_LINK)
    protected WebElement settingsAndMembersLink;

    @FindBy(css = CSS_TEAMSPACES_BUTTON)
    protected WebElement teamspacesButton;

    @FindBy(css = CSS_NEW_TEAMSPACE_BUTTON)
    protected WebElement newTeamspaceButton;

    @FindBy(css = CSS_TEAMSPACE_NAME_INPUT)
    protected WebElement teamspaceNameInput;

    @FindBy(css = CSS_TEAMSPACE_DESCRIPTION_INPUT)
    protected WebElement teamspaceDescriptionInput;

    @FindBy(css = CSS_PERMISSIONS_TAB_BUTTON)
    protected WebElement permissionsTabButton;

    @FindBy(css = CSS_PERMISSION_OPTIONS)
    protected List<WebElement> permissionOptions;

    @FindBy(xpath = XPATH_CREATE_TEAMSPACE_BUTTON)
    protected WebElement createTeamspaceButton;

    @FindBy(css = CSS_SEARCH_TEAMSPACE_INPUT)
    protected WebElement searchTeamspaceInput;

    @FindBy(css = CSS_TEAMSPACE_SEARCH_RESULTS)
    protected List<WebElement> teamspaceSearchResults;

    @FindBy(css = CSS_SKIP_FOR_NOW_BUTTON)
    protected WebElement skipForNowButton;

    public TeamspaceManagementPage(WebDriver driver) {
        super(driver);
    }

    protected void navigateToTeamspaceSection(){
        waitForElementToBeVisible(settingsAndMembersLink);
        clickElement(settingsAndMembersLink);
        waitForElementToBeVisible(teamspacesButton);
        clickElement(teamspacesButton);
    }

    protected void createNewTeamspace(String teamspaceName, String description, String permission){
        waitForElementToBeVisible(newTeamspaceButton);
        clickElement(newTeamspaceButton);
        waitForElementToBeVisible(teamspaceNameInput);
        teamspaceNameInput.sendKeys(teamspaceName);
        waitForElementToBeVisible(teamspaceDescriptionInput);
        teamspaceDescriptionInput.sendKeys(description);
        waitForElementToBeVisible(permissionsTabButton);
        clickElement(permissionsTabButton);
        waitForElementListToBeVisible(permissionOptions);
        pauseBrowser(DELAY_TEST_TIME);
        selectPermission(permissionOptions, permission);
        pauseBrowser(DELAY_TEST_TIME);
        clickElement(createTeamspaceButton);
        pauseBrowser(DELAY_TEST_TIME);
        clickElement(skipForNowButton);
        pauseBrowser(DELAY_TEST_TIME);
    }
    private void selectPermission(List<WebElement> selectPermissionOption, String permission) {
        for (WebElement element : selectPermissionOption) {
            if(element.getText().trim().equalsIgnoreCase(permission)){
                clickElement(element);
                break;
            }
        }
    }
    protected boolean isTeamspacePresent(String teamspace){
        waitForElementToBeVisible(searchTeamspaceInput);
        searchTeamspaceInput.sendKeys(teamspace);
        pauseBrowser(DELAY_TEST_TIME);
        waitForElementListToBeVisible(teamspaceSearchResults);
        return teamspaceSearchResults.stream()
                .anyMatch(element -> element.getText().trim().equalsIgnoreCase(teamspace));
    }
}
