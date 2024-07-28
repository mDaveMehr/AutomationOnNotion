package org.notion.teamspaces;

import org.notion.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
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

    @FindBy(css = "div[role='dialog'] > div > div > div[role='menu'] > div:nth-child(2) > div:nth-child(3)")
    protected WebElement moveToElementLink;

    @FindBy(css = "div[role='dialog'] > div > div.notion-scroller.vertical > div > div > div > div:nth-child(2) > div:first-child")
    protected WebElement moveToPrivatePageLocation;

    @FindBy(css = "div.notion-dialog-renderer-accept-item")
    protected WebElement moveToPrivateBtn;

    @FindBy(css = "div.notion-dialog > div > div:last-child > div > div:nth-child(2)")
    protected WebElement onlyMoveTasksBtn;

    @FindBy(css = "a[role='treeitem']")
    protected List<WebElement> taskList;

    @FindBy(css = "div.notranslate.shadow-cursor-breadcrumb > div[role='button'] > div.notranslate")
    protected WebElement pageTitleText;

    @FindBy(css = "div.notion-overlay-container.notion-default-overlay-container > div > div > div:nth-child(1)")
    protected WebElement movedPageMessage;

    public MeatballMenu(WebDriver driver) {
        super(driver);
    }

    private void clickElement(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    public void openMeatballMenu(){
        clickElement(taskLink);
        delayTest(DELAY_TEST_TIME);
        clickElement(meatballMenuBtn);
    }

    protected boolean toggleLockDatabase(){
        waitForElementToBeVisible(lockDatabaseBtn);
        if(lockStatusText.getText().equals("Locked")){
            return false;
        }else{
            clickElement(lockDatabaseBtn);
            return true;
        }
    }

    public String getLockDatabaseStatusText(){
        return lockStatusText.getText();
    }

    public String getClipboardText(){
        return clipboardText.getText();
    }

    public void copyLink() {
        waitForElementToBeVisible(copyLinkElement);
        copyLinkElement.click();
    }
    public void moveToLink(){
        waitForElementToBeVisible(moveToElementLink);
        moveToElementLink.click();
        delayTest(DELAY_TEST_TIME);
        moveToPrivatePageLocation.click();
        delayTest(DELAY_TEST_TIME);
        moveToPrivateBtn.click();
        delayTest(DELAY_TEST_TIME);
        onlyMoveTasksBtn.click();
        delayTest(DELAY_TEST_TIME);
    }

    boolean selectPageStatus = false;
    public boolean selectPageToMove(){
        for(WebElement w : taskList){
            if(w.getText().equals(pageTitleText.getText())){
                selectPageStatus = true;
            }
        }
        return selectPageStatus;
    }
   public String getMessageAfterMovingPage(){
        String originalString = movedPageMessage.getText();
        String modifiedString = originalString.replace("Undo", "");
       System.out.println(originalString);
       System.out.println(modifiedString);
        return modifiedString;
   }

}
