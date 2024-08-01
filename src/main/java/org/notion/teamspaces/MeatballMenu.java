package org.notion.teamspaces;

import org.notion.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class MeatballMenu extends WebPage {
    @FindBy(css = ".notion-outliner-team > div > div > div:nth-child(1) > a")
    protected WebElement taskLink;

    @FindBy(css = "div.notion-topbar-action-buttons > div > div:last-child")
    protected WebElement meatballMenuBtn;

    @FindBy(css = "div[role='dialog'] > div > div > div[role='menu'] > div:first-child > div > div > div:last-child")
    protected WebElement lockDatabaseBtn;

    @FindBy(css = "div.notion-topbar > div > div > div:nth-child(2)")
    protected WebElement lockStatusText;

    @FindBy(css = "div[role='dialog'] > div > div > div[role='menu'] > div:nth-child(2) > div:first-child")
    protected WebElement copyLinkElement;

    @FindBy(css = "div.notion-overlay-container.notion-default-overlay-container > div:last-child > div > div")
    protected WebElement clipboardText;

    @FindBy(css = "div[role='dialog'] > div > div > div[role='menu'] > div:nth-child(2) > div:nth-child(2)")
    protected WebElement duplicateElementLink;

    @FindBy(css = "div[role='dialog'] > div > div > div[role='menu'] > div:nth-child(2) > div:nth-child(3)")
    protected WebElement moveToElementLink;

    @FindBy(css = "div[role='dialog'] > div > div.notion-scroller.vertical > div > div > div > div:nth-child(2) > div:first-child")
    protected WebElement privatePageLocationOption;

    @FindBy(css = "div.notion-dialog-renderer-accept-item")
    protected WebElement moveToPrivateBtn;

    @FindBy(css = "div.notion-dialog > div > div:last-child > div > div:nth-child(2)")
    protected WebElement moveTasksOnlyButton;

    @FindBy(css = "div.notion-dialog > div > div:last-child > div > div:nth-child(2)")
    protected WebElement duplicateTasksOnlyButton;

    @FindBy(css = "div[role='tree'] > div > div > div.notion-selectable.notion-collection_view_page-block")
    protected List<WebElement> teamspacesListElements;

    @FindBy(css = "div.notranslate.shadow-cursor-breadcrumb > div[role='button'] > div.notranslate")
    protected WebElement pageTitleText;

    @FindBy(css = "div.notion-overlay-container.notion-default-overlay-container > div > div > div:nth-child(1)")
    protected WebElement movedPageNotification;

    @FindBy(css = "div.notion-outliner-private > div > div")
    protected List<WebElement> privateSectionElements;

    @FindBy(css = "div[role='dialog'] > div > div:last-child > div > div > div > div:first-child")
    protected WebElement duplicateWithContentMenu;

    @FindBy(css = "div[role='dialog'] > div > div:last-child > div > div > div > div:last-child")
    protected WebElement duplicateWithoutContentMenu;

    public MeatballMenu(WebDriver driver) {
        super(driver);
    }



    protected void openMenu(){
        clickElement(taskLink);
        delayTest(DELAY_TEST_TIME);
        clickElement(meatballMenuBtn);
    }

    protected boolean toggleDatabaseLock(){
        waitForElementToBeVisible(lockDatabaseBtn);
        if(lockStatusText.getText().equals("Locked")){
            return false;
        }else{
            clickElement(lockDatabaseBtn);
            return true;
        }
    }

    protected String getDatabaseLockStatus(){
        return lockStatusText.getText();
    }

    protected String getClipboardText(){
        return clipboardText.getText();
    }

    protected void copyLink() {
        waitForElementToBeVisible(copyLinkElement);
        copyLinkElement.click();
    }
    protected void moveToLink(){
        waitForElementToBeVisible(moveToElementLink);
        moveToElementLink.click();
        delayTest(DELAY_TEST_TIME);
        privatePageLocationOption.click();
        delayTest(DELAY_TEST_TIME);
        moveToPrivateBtn.click();
        delayTest(DELAY_TEST_TIME);
        moveTasksOnlyButton.click();
        delayTest(DELAY_TEST_TIME);
    }

    private boolean isTextPresentInList(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPageMovedToPrivateSection() {
        return isTextPresentInList(privateSectionElements, "Tasks (1)");
    }

    public boolean isPageDuplicate() {
        return isTextPresentInList(teamspacesListElements, "Tasks");
    }

   protected void duplicateWithContent(){
       waitForElementToBeVisible(duplicateElementLink);
       clickElement(duplicateElementLink);
       pauseBrowser(DELAY_TEST_TIME);
       duplicateWithContentMenu.click();
       pauseBrowser(DELAY_TEST_TIME);
       duplicateTasksOnlyButton.click();
       pauseBrowser(DELAY_TEST_TIME);
   }
    protected void duplicateWithoutContent(){
        waitForElementToBeVisible(duplicateElementLink);
        clickElement(duplicateElementLink);
        pauseBrowser(DELAY_TEST_TIME);
        duplicateWithoutContentMenu.click();
        pauseBrowser(DELAY_TEST_TIME);
        duplicateTasksOnlyButton.click();
        pauseBrowser(DELAY_TEST_TIME);
    }

    protected void teamspaceListTo(){
        for(int i = 0; i < teamspacesListElements.size(); i++){
            if(teamspacesListElements.get(i).getText().equals("Tasks (1)")){
                pauseBrowser(DELAY_TEST_TIME);
                clickElement(teamspacesListElements.get(i));
                pauseBrowser(DELAY_TEST_TIME);
                openMenu();
                pauseBrowser(DELAY_TEST_TIME);
            }
        }
    }
}
