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
    public void initializeCreateTaskPage() {
        meatballMenu = PageFactory.initElements(driver, MeatballMenu.class);
    }
    @Test(priority = 1)
    public void verifyToggleLockDatabase() {
        meatballMenu.openMeatballMenu();
        pauseBrowser(3);
        meatballMenu.lockStatusText.getText(); // "Unlocked"

        boolean result = meatballMenu.toggleLockDatabase();
        if(!result){
            Assert.assertEquals(meatballMenu.getLockDatabaseStatusText(),
                    TestRunPropertyReader.getTestRunProperty("lockDatabaseStatus"), "Database status should be 'Locked' after clicking the button.");
        }else{
            Assert.assertTrue(result, "Method should return true when the database is unlocked.");
        }

    }
    @Test(priority = 2)
    public void verifyCopyLinkOption(){
        pauseBrowser(3);
        meatballMenu.copyLink();
        Assert.assertEquals(meatballMenu.getClipboardText(),
                TestRunPropertyReader.getTestRunProperty("clipboardText"));
    }
    @Test(priority = 3)
    public void verifyMoveToOption(){
        meatballMenu.openMeatballMenu();
        meatballMenu.moveToLink();
        Assert.assertEquals(meatballMenu.getMessageAfterMovingPage(),
                TestRunPropertyReader.getTestRunProperty("movedTasksToPrivate"));
    }
}
