package org.notion.teamspaces;

import org.notion.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MeatballMenu extends WebPage {

    @FindBy(css = ".notion-outliner-team > div > div > div:nth-child(1) > a")
    protected WebElement taskLink;

    @FindBy(css = "div.notion-topbar-action-buttons > div > div:last-child")
    protected WebElement meatballMenuBtn;

    @FindBy(css = "div[role='dialog'] > div > div > div[role='menu'] > div:first-child > div > div > div:last-child")
    protected WebElement lockDatabaseBtn;

    @FindBy(css = "div.notion-topbar > div > div > div:nth-child(2)")
    protected WebElement lockStatusText;

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
        toggleLockDatabase();
        delayTest(DELAY_TEST_TIME);

    }

    public void toggleLockDatabase(){
        waitForElementToBeVisible(lockDatabaseBtn);
        if(!lockDatabaseBtn.isSelected()){
            clickElement(lockDatabaseBtn);
        }
    }

    public String getLockDatabaseStatusText(){
        return lockStatusText.getText();
    }
}
