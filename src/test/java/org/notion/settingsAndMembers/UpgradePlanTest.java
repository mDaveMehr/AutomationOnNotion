package org.notion.settingsAndMembers;

import org.notion.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestRunPropertyReader;

public class UpgradePlanTest extends BaseTest {

    protected UpgradePlan upgradePlan;

    @BeforeMethod
    protected void initializeSettingsAndMembersPage(){
        upgradePlan = PageFactory.initElements(driver, UpgradePlan.class);
    }

    @Test(priority = 1)
    protected void verifyMonthlyPaymentDetails(){
        upgradePlan.navigateToSettingsAndMembers();
        upgradePlan.clickAddToPlan();
        upgradePlan.enterPaymentDetails();
        upgradePlan.chooseBillingOption();
        Assert.assertEquals(upgradePlan.getBillingOption(), upgradePlan.getSummarizedPayment());
    }
    @Test(priority = 2)
    protected void verifyAnnuallyPaymentDetails(){
        upgradePlan.clickAddToPlan();
        upgradePlan.enterPaymentDetails();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("totalYearlyPayment"));
    }
    @AfterMethod
    protected void closePopupAfterTest(){
        upgradePlan.closePopup();
    }
}
