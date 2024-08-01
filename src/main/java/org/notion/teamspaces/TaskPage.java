package org.notion.teamspaces;

import org.checkerframework.checker.units.qual.A;
import org.notion.base.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TaskPage extends WebPage {

    @FindBy(css = ".notion-outliner-team > div > div > div:nth-child(1) > a")
    protected WebElement taskLink;

    @FindBy(css = "div.notion-collection-view-item-add > div:first-child")
    protected WebElement createNewTaskBtn;

    @FindBy(css = "div.notion-selectable.notion-page-block > h1.notranslate")
    protected WebElement taskInputField;

    @FindBy(css = "div[role='table'] > div:first-child > div > div:nth-child(2) > div")
    protected WebElement assigneeInputTextField;

    @FindBy(css = "input[placeholder='Search for a person…']")
    protected WebElement assigneeInputField;

    @FindBy(css = "div[role='menuitem']")
    protected List<WebElement> assigneeDropDown;

    @FindBy(css = "div[role='menu'] > div > div:last-child")
    protected WebElement selectedOptionFromAssigneeList;

    @FindBy(css = "div[role='table'] > div:nth-child(2) > div > div:nth-child(2) > div")
    protected WebElement statusBtn;

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    public void taskCreation(){
        clickElement(taskLink);
        pauseBrowser(DELAY_TEST_TIME);
        clickElement(createNewTaskBtn);
        pauseBrowser(DELAY_TEST_TIME);
        clearAndType(taskInputField,"Retrospective");
        pauseBrowser(DELAY_TEST_TIME);
        clickElement(assigneeInputTextField);

        assigneeInputField.sendKeys("John Mird");
        Actions action = new Actions(driver);
        action.moveToElement(selectedOptionFromAssigneeList);
        pauseBrowser(DELAY_TEST_TIME);
        action.click().perform();
        pauseBrowser(DELAY_TEST_TIME);
        WebElement body = driver.findElement(By.tagName("body"));
        action.moveToElement(body).click().perform();
        statusBtn.click();

    }

    protected void selectOptionFromAssigneeList(List<WebElement> options, String optionText) {
        for (WebElement option : options) {
            if (option.getText().trim().equals(optionText)) {
                option.click();
                break;
            }
        }
    }
    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    private void clearAndType(WebElement element, String text) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }
    private void selectAssignee(String assigneeName) {
        clickElement(assigneeInputTextField);
        assigneeInputField.sendKeys(assigneeName);
        new Actions(driver)
                .moveToElement(selectedOptionFromAssigneeList)
                .click()
                .perform();
    }
}
