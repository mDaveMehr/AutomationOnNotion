package org.notion.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebPage {
    public static final int DELAY_TEST_TIME = 3;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public WebPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public WebElement waitForElementToBeVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element).click();
    }
    protected void delayTest(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }
    public void waitForElementListToBeVisible(List<WebElement> webElements){
         wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }
}
