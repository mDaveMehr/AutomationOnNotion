package org.notion.base;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    protected LoginPage loginPage;

    @BeforeClass
    public void classSetUpInLoginPageTest() {
        System.out.println("Before class from Child class");
    }

    @Test
    public void runTest(){
        pauseBrowser(10);
        System.out.println("Running base test");
    }

}
