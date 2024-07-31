package org.notion.settingsAndMembers;

import com.github.javafaker.Faker;
import org.notion.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SettingsAndMembers extends WebPage {
    private static Faker faker  = new Faker();
    private static final Random random = new Random();
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final int BILLING_OPTION = 0;
    private static final int TOTAL_PAYMENT = 2;
    @FindBy(css = "div.notion-sidebar > div:nth-child(3) > div > div:nth-child(2) > div[role='button']:last-child")
    protected WebElement settingAndMemberLink;

    @FindBy(css = "div.notion-dialog > div > div:first-child > div > div > div > div:nth-child(10)")
    protected WebElement upgradePlanButton;

    @FindBy(css = "div.autolayout-row.autolayout-fill-width.autolayout-center > div:last-child")
    protected WebElement addToPlanButton;

    @FindBy(css ="div.StripeElement > div > iframe")
    protected WebElement switchToIframe;

    @FindBy(css = "div.p-Input > input[name='number']")
    protected WebElement enterCardNumber;

    @FindBy(css = "p#Field-numberError")
    protected WebElement getCardNumberErrorMsg;

    @FindBy(css = "input#Field-expiryInput")
    protected WebElement enterExpirationDate;

    @FindBy(css = "input#Field-cvcInput")
    protected WebElement enterSecurityCode;

    @FindBy(css = "input#Field-postalCodeInput")
    protected WebElement enterPostalCode;

    @FindBy(css = "div.autolayout-row.autolayout-fill-width.autolayout-center-left > span")
    protected List<WebElement> selectMonthlyBillingOption;

    @FindBy(css = "input#Field-linkMobilePhoneInput")
    protected WebElement enterPhoneNumber;

    @FindBy(css = "div.autolayout-col.autolayout-fill-width > div:first-child > div[role='button']:first-child")
    protected WebElement upgradeNowButton;

    @FindBy(css = "div.autolayout-col.autolayout-fill-width > div > div.tx-caption-12-reg")
    protected List<WebElement> billingOptionPayment;

    @FindBy(css = "div.tx-heading-17-semi:last-child")
    protected List<WebElement> totalPayment;

    public SettingsAndMembers(WebDriver driver) {
        super(driver);
    }

    protected void navigateToSettingsAndMembers(){
        waitForElementToBeVisible(settingAndMemberLink);
        clickElement(settingAndMemberLink);
        waitForElementToBeVisible(upgradePlanButton);
        clickElement(upgradePlanButton);
    }

    protected void clickAddToPlan(){
        waitForElementToBeVisible(addToPlanButton);
        clickElement(addToPlanButton);
    }

    protected boolean isUpgradePlanButtonVisible() {
        return upgradePlanButton.isDisplayed();
    }

    protected boolean isAddToPlanButtonVisible() {
        return addToPlanButton.isDisplayed();
    }

    protected void enterPaymentDetails(){
        waitForElementToBeVisible(switchToIframe);
        driver.switchTo().frame(switchToIframe);

        String cardNumber = generateCardNumber();
        waitForElementToBeVisible(enterCardNumber);
        enterCardNumber.sendKeys(cardNumber);

        String cardExpirationDate = generateExpirationDate();
        waitForElementToBeVisible(enterExpirationDate);
        enterExpirationDate.sendKeys(cardExpirationDate);

        String cardSecurityCode = generateSecurityCode();
        waitForElementToBeVisible(enterSecurityCode);
        enterSecurityCode.sendKeys(cardSecurityCode);

        String areaPostalCode = generatePostalCode();
        waitForElementToBeVisible(enterPostalCode);
        enterPostalCode.sendKeys(areaPostalCode);
        if(isElementPresent(enterPhoneNumber)){
            String phoneNumber = generateRandomPhoneNumber();
            enterPhoneNumber.sendKeys(phoneNumber);
        }
    }

    protected static String generateCardNumber(){
        return faker.finance().creditCard();
    }

    protected static String generateExpirationDate(){
        String month = String.format("%02d",random.nextInt(12) + 1);
        String year = String.format("%02d",random.nextInt(10) + 24);
        return month + "/" + year;
    }
    protected static String generateSecurityCode(){
        return String.format("%03d",random.nextInt(900) + 100);
    }
    protected static String generatePostalCode(){
        String partOne = generateRandomString(1, LETTERS) + generateRandomString(1, DIGITS) + generateRandomString(1, LETTERS);
        String partTwo = generateRandomString(1, DIGITS) + generateRandomString(1, LETTERS) + generateRandomString(1, DIGITS);

        return partOne + " " + partTwo;
    }
    protected static String generateRandomString(int length, String characters) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }
    protected static String generateRandomPhoneNumber(){
        long phoneNumber = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.format("(%03d) %03d-%04d",
                (int) (phoneNumber / 10000000),
                (int) ((phoneNumber / 10000) % 1000),
                (int) (phoneNumber % 10000));
    }
    protected void chooseBillingOption(){
        driver.switchTo().defaultContent();
        for(int i = 0; i < selectMonthlyBillingOption.size(); i++){
            delayTest(DELAY_TEST_TIME);
            clickElement(selectMonthlyBillingOption.get(0));
        }
        clickElement(upgradeNowButton);
    }
    private boolean isElementPresent(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    protected String getBillingOption(){
        return billingOptionPayment.get(BILLING_OPTION).getText();
    }
    protected String getSummarizedPayment(){
        return totalPayment.get(TOTAL_PAYMENT).getText() + " / member";
    }

}
