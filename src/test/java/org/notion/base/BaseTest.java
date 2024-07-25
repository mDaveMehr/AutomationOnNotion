package org.notion.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void classSetUpInBase() {

        String userProfile = "C:\\Users\\Singh\\AppData\\Local\\Google\\Chrome\\User Data\\";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--profile-directory=Profile 2");
        driver= new ChromeDriver(options);
        pauseBrowser(3);
        driver.manage().window().maximize();
        driver.get("https://www.notion.so/login");
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
