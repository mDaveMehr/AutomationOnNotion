package org.notion.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public void login() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notion-email-input-2")));
        email.sendKeys("john.mird@gmail.com");
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("form > div[role='button']")));
        continueBtn.click();

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#notion-password-input-1")));
        password.sendKeys("123123Aa@");
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("form > div[role='button']")));
        loginBtn.click();

        WebElement createPageBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg[class='createPage']")));
        createPageBtn.click();

        WebElement pageHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class='notranslate']")));
        pageHeading.sendKeys("Shopping List");

        WebElement pageParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[class='checkboxSquareChecked']")));
        pageParagraph.click();
    }
}
