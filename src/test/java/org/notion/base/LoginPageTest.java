package org.notion.base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @BeforeClass
    public void classSetUpInLoginPageTest() {
        System.out.println("Before class from Child class");
    }

    @Test
    public void verifyLogin(){
        pauseBrowser(3);
        System.out.println("Test run");
    }

}
