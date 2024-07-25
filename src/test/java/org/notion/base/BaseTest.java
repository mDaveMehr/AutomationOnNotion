package org.notion.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.TestRunPropertyReader;
import java.util.Set;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    @BeforeClass
    public void classSetUpInBase() {
        TestRunPropertyReader.loadTestRunProperties();
        String userProfile = "C:\\Users\\Singh\\AppData\\Local\\Google\\Chrome\\User Data\\";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--profile-directory=Profile 2");

        driver= new ChromeDriver(options);
        pauseBrowser(3);

            driver.manage().window().maximize();
            driver.get(TestRunPropertyReader.getTestRunProperty("url"));
            pauseBrowser(3);

            loginPage = PageFactory.initElements(driver, LoginPage.class);
            boolean isLoggedIn = loginPage.isUserLoggedIn();
            if(!isLoggedIn){
                loginPage.login(TestRunPropertyReader.getTestRunProperty("email"), TestRunPropertyReader.getTestRunProperty("password"));
            }else {
                System.out.println("User is already logged in.");
            }
    }

    @AfterClass
    public void tearDownForMethodInBase()  {
        pauseBrowser(1);
        driver.quit();
    }

    public void pauseBrowser(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception occuring during thread sleep call.");
        }
    }
}
