package org.notion.teamspaces;

import org.notion.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.TestRunPropertyReader;

public class MeatBallMenuTest extends BaseTest {
    protected MeatballMenu meatballMenu;
    @BeforeMethod
    protected void initializeCreateTaskPage() {
        meatballMenu = PageFactory.initElements(driver, MeatballMenu.class);
    }
    @Test(priority = 1)
    protected void verifyToggleLockDatabase() {
        meatballMenu.openMenu();
        pauseBrowser(3);
        meatballMenu.lockStatusText.getText(); // "Unlocked"

        boolean result = meatballMenu.toggleDatabaseLock();
        if(!result){
            Assert.assertEquals(meatballMenu.getDatabaseLockStatus(),
                    TestRunPropertyReader.getTestRunProperty("lockDatabaseStatus"), "Database status should be 'Locked' after clicking the button.");
        }else{
            Assert.assertTrue(result, "Method should return true when the database is unlocked.");
        }

    }
    @Test(priority = 2)
    protected void verifyCopyLinkOption(){
        pauseBrowser(3);
        meatballMenu.copyLink();
        Assert.assertEquals(meatballMenu.getClipboardText(),
                TestRunPropertyReader.getTestRunProperty("clipboardText"));
    }
    @Test(priority = 5)
    protected void verifyMoveToOption(){
        meatballMenu.openMenu();
        pauseBrowser(3);
        meatballMenu.moveToLink();
        pauseBrowser(3);
        Assert.assertTrue(meatballMenu.isPageMovedToPrivateSection());
    }
    @Test(priority = 3)
    protected void verifyDuplicateWithContent(){
        meatballMenu.openMenu();
        pauseBrowser(3);
        meatballMenu.duplicateWithContent();
        pauseBrowser(3);
        Assert.assertTrue(meatballMenu.isPageDuplicate());
    }
    @Test(priority = 4)
    protected void verifyDuplicateWithoutContent(){
        meatballMenu.openMenu();
        pauseBrowser(3);
        meatballMenu.duplicateWithoutContent();
        pauseBrowser(3);
        Assert.assertTrue(meatballMenu.isPageDuplicate());
    }

    @Test(priority = 5)
    protected void verifyList(){
        pauseBrowser(3);
        meatballMenu.teamspaceListTo();
    }
}
