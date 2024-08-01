package org.notion.settingsAndMembers;

import com.github.javafaker.Faker;
import org.notion.base.WebPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class UpgradePlan extends WebPage {
    private static Faker faker  = new Faker();
    private static final Random random = new Random();
    private static final String LETTERS = "ABCEGHJKLMNPRSTVWXYZ";
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
    protected WebElement switchToPaymentIframe;

    @FindBy(css = "div.p-Input > input[name='number']")
    protected WebElement enterCardNumber;

    @FindBy(css = "p#Field-numberError")
    protected WebElement getCardNumberErrorMsg;

    @FindBy(css = "input#Field-expiryInput")
    protected WebElement enterExpirationDate;

    @FindBy(css = "p#Field-expiryError")
    protected WebElement getExpirationDateErrorMsg;

    @FindBy(css = "input#Field-cvcInput")
    protected WebElement enterSecurityCode;

    @FindBy(css = "p#Field-cvcError")
    protected WebElement getSecurityCodeErrorMsg;

    @FindBy(css = "input#Field-postalCodeInput")
    protected WebElement enterPostalCode;

    @FindBy(css = "p#Field-postalCodeError")
    protected WebElement getPostalCodeErrorMsg;

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

    @FindBy(css = "svg.closeSmall")
    protected WebElement closeButton;

    public UpgradePlan(WebDriver driver) {
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

    protected void enterPaymentDetails() {
        try {
            waitForElementToBeVisible(switchToPaymentIframe);
            driver.switchTo().frame(switchToPaymentIframe);

            enterCardNumberField();
            pauseBrowser(DELAY_TEST_TIME);
            // Check for card number error message
            if (isElementPresent(getCardNumberErrorMsg)) {
                String errorMessage = getCardNumberErrorMsg.getText();
                if (errorMessage.equals("Your card number is invalid.")) {
                    System.out.println("Card number was invalid. Re-entering a new card number.");
                    enterCardNumber.clear(); // Clear the existing input if needed
                    enterCardNumberField();
                }
            }
            enterExpirationDateField();
            pauseBrowser(DELAY_TEST_TIME);
            // Check for expiration date error message
            if (isElementPresent(getExpirationDateErrorMsg)) {
                String errorMessage = getExpirationDateErrorMsg.getText();
                if (errorMessage.equals("Your card's expiration date is incomplete.")) {
                    System.out.println("Expiration date was incomplete. Re-entering a new expiration date.");
                    enterExpirationDate.clear(); // Clear the existing input if needed
                    enterExpirationDateField();
                }
            }
            enterSecurityCodeField();
            pauseBrowser(DELAY_TEST_TIME);

            // Check for security code error message
            if (isElementPresent(getSecurityCodeErrorMsg)) {
                String errorMessage = getSecurityCodeErrorMsg.getText();
                if (errorMessage.equals("Your card's security code is incomplete.")) {
                    System.out.println("Security code was incomplete. Re-entering a new security code.");
                    enterSecurityCode.clear(); // Clear the existing input if needed
                    enterSecurityCodeField();
                }
            }
            enterPostalCodeField();
            pauseBrowser(DELAY_TEST_TIME);

            // Check for postal code error message
            if (isElementPresent(getPostalCodeErrorMsg)) {
                String errorMessage = getPostalCodeErrorMsg.getText();
                if (errorMessage.equals("Your postal code is invalid.")) {
                    System.out.println("Postal code was invalid. Re-entering a new postal code.");
                    enterPostalCode.clear(); // Clear the existing input if needed
                    enterPostalCodeField();
                }
            }
            // Handle Phone Number if present
            if (isElementPresent(enterPhoneNumber)) {
                String phoneNumber = generateRandomPhoneNumber();
                enterPhoneNumber.sendKeys(phoneNumber);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void enterCardNumberField() {
        String cardNumber = generateCardNumber();
        waitForElementToBeVisible(enterCardNumber);
        enterCardNumber.clear();
        enterCardNumber.sendKeys(cardNumber);
    }

    private void enterExpirationDateField() {
        String cardExpirationDate = generateExpirationDate();
        waitForElementToBeVisible(enterExpirationDate);
        enterExpirationDate.clear();
        enterExpirationDate.sendKeys(cardExpirationDate);
    }

    private void enterSecurityCodeField() {
        String cardSecurityCode = generateSecurityCode();
        waitForElementToBeVisible(enterSecurityCode);
        enterSecurityCode.clear();
        enterSecurityCode.sendKeys(cardSecurityCode);
    }

    private void enterPostalCodeField() {
        String areaPostalCode = generatePostalCode();
        waitForElementToBeVisible(enterPostalCode);
        enterPostalCode.clear();
        enterPostalCode.sendKeys(areaPostalCode);
    }

    protected static String generateCardNumber(){
        return faker.finance().creditCard();
    }

    protected static String generateExpirationDate(){
        String month = String.format("%02d",random.nextInt(12) + 1);
        String year = String.format("%02d",random.nextInt(10) + 25);
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
        int areaCode = random.nextInt(1000);         // Area code: 000 to 999
        int centralOfficeCode = random.nextInt(1000); // Central office code: 000 to 999
        int lineNumber = random.nextInt(10000);      // Line number: 0000 to 9999

        // Format and return the phone number - (XXX) XXX-XXXX
        return String.format("(%03d) %03d-%04d", areaCode, centralOfficeCode, lineNumber);
    }
    protected void chooseBillingOption(){
        driver.switchTo().defaultContent();
        for(int i = 0; i < selectMonthlyBillingOption.size(); i++){
            clickElement(selectMonthlyBillingOption.get(BILLING_OPTION));
        }
    }
    private boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
    protected String getBillingOption(){
        String monthlyString = billingOptionPayment.get(BILLING_OPTION)
                .getText().replace(" / member", "");
        return monthlyString;
    }
    protected String getSummarizedPayment(){
        driver.switchTo().defaultContent();
        waitForElementListToBeVisible(totalPayment);
        return totalPayment.get(TOTAL_PAYMENT).getText();
    }

    protected void closePopup(){
        clickElement(closeButton);
    }
}
