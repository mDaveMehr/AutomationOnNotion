package org.notion.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends WebPage{
    private static final String HOME_PAGE_TITLE= "Concordia";
    @FindBy(css = "input#notion-email-input-2")
    protected WebElement userEmail;

    @FindBy(css = "form > div[role='button']")
    protected WebElement continueBtn;

    @FindBy(css = "input#notion-password-input-1")
    protected WebElement passWrd;

    @FindBy(css = ".notion-sidebar-switcher > div > div:nth-child(2) > div > div > div:first-child")
    protected WebElement HomePageText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    protected void login(String email, String password) {
        waitForElementToBeVisible(userEmail);
        userEmail.sendKeys(email);
        delayTest(DELAY_TEST_TIME);
        waitForElementToBeClickable(continueBtn);
        continueBtn.click();
        delayTest(DELAY_TEST_TIME);
        waitForElementToBeVisible(passWrd);
        passWrd.sendKeys(password);
        delayTest(DELAY_TEST_TIME);
        waitForElementToBeClickable(continueBtn);
        continueBtn.click();
        delayTest(DELAY_TEST_TIME);
    }

    protected boolean isUserLoggedIn() {
        try {
            String pageTitle = HomePageText.getText();
            return pageTitle.equalsIgnoreCase(HOME_PAGE_TITLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
