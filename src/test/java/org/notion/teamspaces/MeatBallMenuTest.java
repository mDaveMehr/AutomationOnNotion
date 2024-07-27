package org.notion.teamspaces;

import org.notion.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MeatBallMenuTest extends BaseTest {
    protected MeatballMenu meatballMenu;
    @BeforeMethod
    public void initializeCreateTaskPage() {
        meatballMenu = PageFactory.initElements(driver, MeatballMenu.class);
        meatballMenu.openMeatballMenu();
    }

    @Test(priority = 1)
    public void verifyLockDatabase(){
        pauseBrowser(3);
        if(meatballMenu.lockDatabaseBtn.isDisplayed()){
            Assert.assertEquals(meatballMenu.getLockDatabaseStatusText(), "Locked");
        }else{
            Assert.assertNull(meatballMenu.getLockDatabaseStatusText());
        }
    }

}
