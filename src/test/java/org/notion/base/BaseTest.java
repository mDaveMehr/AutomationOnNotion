package org.notion.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.TestRunPropertyReader;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    private static final String USER_PROFILE_PATH = "C:\\Users\\Singh\\AppData\\Local\\Google\\Chrome\\User Data\\";
    private static final String PROFILE_NAME = "Profile 2";
    private static final int PAUSE_DURATION = 3;

    @BeforeClass
    protected void classSetUpInBase() {
        terminateExistingChromeProcesses();

        TestRunPropertyReader.loadTestRunProperties();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + USER_PROFILE_PATH);
        options.addArguments("--profile-directory=" + PROFILE_NAME);

        driver= new ChromeDriver(options);
        pauseBrowser(PAUSE_DURATION);

        driver.manage().window().maximize();
        driver.get(TestRunPropertyReader.getTestRunProperty("url"));
        pauseBrowser(PAUSE_DURATION);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        boolean isLoggedIn = loginPage.isUserLoggedIn();
        if(!isLoggedIn){
            loginPage.login(TestRunPropertyReader.getTestRunProperty("email"), TestRunPropertyReader.getTestRunProperty("password"));
        }else {
            System.out.println("User is already logged in.");
        }
    }

    @AfterClass
    protected void tearDownForMethodInBase()  {
        pauseBrowser(1);
        driver.quit();
    }

    protected void pauseBrowser(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception occuring during thread sleep call.");
        }
    }
    protected void terminateExistingChromeProcesses() {
        String os = System.getProperty("os.name").toLowerCase();
        String command = os.contains("win") ? "taskkill /F /IM chrome.exe" : "pkill chrome";
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.err.println("Failed to terminate existing Chrome processes: " + e.getMessage());
        }
    }
}
