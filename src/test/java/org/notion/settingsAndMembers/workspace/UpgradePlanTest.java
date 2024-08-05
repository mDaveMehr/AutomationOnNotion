package org.notion.settingsAndMembers.workspace;

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
        upgradePlan.chooseBillingOption(0);
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("totalMonthlyPayment"));
    }
    @Test(priority = 2)
    protected void verifyAnnuallyPaymentDetails(){
        upgradePlan.clickAddToPlan();
        upgradePlan.enterPaymentDetails();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("totalYearlyPayment"));
    }
    @Test(priority = 3)
    protected void verifyPlusMonthlySubscription(){
        upgradePlan.subscribeToPlusPlan();
        upgradePlan.enterPaymentDetails();
        upgradePlan.chooseBillingOption(0);
        upgradePlan.additionalAddOn();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("plusAccountMonthlyPayment"));
    }
    @Test(priority = 4)
    protected void verifyPlusYearlySubscription(){
        upgradePlan.subscribeToPlusPlan();
        upgradePlan.enterPaymentDetails();
        upgradePlan.chooseBillingOption(1);
        upgradePlan.additionalAddOn();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("plusAccountYearlyPayment"));
    }
    @Test(priority = 5)
    protected void verifyBusinessYearlySubscription(){
        upgradePlan.subscribeToBusinessPlan();
        upgradePlan.enterPaymentDetails();
        upgradePlan.chooseBillingOption(1);
        upgradePlan.additionalAddOn();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("businessAccountYearlyPayment"));
    }
    @Test(priority = 6)
    protected void verifyMonthlyYearlySubscription(){
        upgradePlan.subscribeToBusinessPlan();
        upgradePlan.enterPaymentDetails();
        upgradePlan.chooseBillingOption(0);
        upgradePlan.additionalAddOn();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("businessAccountMonthlyPayment"));
    }
    @Test(priority = 7)
    protected void verifyEnterpriseYearlySubscriptionWithAddOnFeature(){
        upgradePlan.subscribeToEnterprisePlan();
        upgradePlan.enterPaymentDetails();
        upgradePlan.chooseBillingOption(1);
        upgradePlan.additionalAddOn();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("enterpriseAccountYearlyPayment"));
    }
    @Test(priority = 8)
    protected void verifyEnterpriseMonthlySubscriptionWithAddOnFeature(){
        upgradePlan.subscribeToEnterprisePlan();
        upgradePlan.enterPaymentDetails();
        upgradePlan.chooseBillingOption(0);
        upgradePlan.additionalAddOn();
        Assert.assertEquals(upgradePlan.getSummarizedPayment(), TestRunPropertyReader.getTestRunProperty("enterpriseAccountMonthlyPayment"));
    }
    @AfterMethod
    protected void closePopupAfterTest(){
        upgradePlan.closePopup();
    }
}
