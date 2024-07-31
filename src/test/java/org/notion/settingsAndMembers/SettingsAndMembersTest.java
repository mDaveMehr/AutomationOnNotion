package org.notion.settingsAndMembers;

import org.notion.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SettingsAndMembersTest extends BaseTest {

    protected SettingsAndMembers settingsAndMembers;

    @BeforeMethod
    protected void initializeSettingsAndMembersPage(){
        settingsAndMembers = PageFactory.initElements(driver,SettingsAndMembers.class);
    }

    @Test(priority = 1)
    protected void shouldDisplaySettingsAndMembersLinkAfterNavigation(){
        settingsAndMembers.navigateToSettingsAndMembers();
        Assert.assertTrue(settingsAndMembers.isUpgradePlanButtonVisible(), "Upgrade Plan Button is not displayed.");
    }

    @Test(priority = 2)
    protected void shouldDisplayAddToPlanLinkAfterAction(){
        settingsAndMembers.clickAddToPlan();
        Assert.assertTrue(settingsAndMembers.isAddToPlanButtonVisible(), "Add To Plan Button is not displayed.");
    }

    @Test(priority = 3)
    protected void verifyMonthlyPaymentDetails(){
        settingsAndMembers.enterPaymentDetails();
        settingsAndMembers.chooseBillingOption();
        Assert.assertEquals(settingsAndMembers.getBillingOption(), settingsAndMembers.getSummarizedPayment());
    }
    @Test(priority = 4)
    protected void verifyAnnuallyPaymentDetails(){
        settingsAndMembers.enterPaymentDetails();
        Assert.assertEquals(settingsAndMembers.getSummarizedPayment(), "$96 / year");
    }
}
