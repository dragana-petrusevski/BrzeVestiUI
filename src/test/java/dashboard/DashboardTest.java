/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import base.BaseTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SignaturesPage;


public class DashboardTest extends BaseTest {
    
    private static DashboardPage dashboardPage;
    
    @Before
    public void setUp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfValidUser();
        loginPage.enterPasswordOfValidUser();
        loginPage.clickLoginButton();
        
        dashboardPage = new DashboardPage(driver);
    }
    
    @After
    public void tearDown() {
        dashboardPage.logout();
    }

    @Test
    public void testDashboardLink() {
    //1. click on Dashboard link in navbar
    dashboardPage.clickOnDashboardNavLink();
    //expected result: user is on dashboard 
    
    String expectedPanelHeadingText = "Dashboard";
    String actualPanelHeadingText = dashboardPage.getPanelHeadingText();
    
        assertTrue("Failed - panel heading texts don't match", expectedPanelHeadingText.equals(actualPanelHeadingText));
    
}
    @Test
    public void testSignaturesLink() {
    dashboardPage.clickOnSignaturesNavLink();
    SignaturesPage signaturesPage = new SignaturesPage(driver);
    
    ///
    //signaturesPage signaturesPage = dashboardPage.clickOnSignaturesNavLink();
    ///
    String expectedPanelHeadingText = "Signatures";
    String actualPanelHeadingText = signaturesPage.getPanelHeadingText();
    
    assertTrue("Failed - panel heading texts don't match", expectedPanelHeadingText.equals(actualPanelHeadingText));

    }
    
    @Test
    public void testCategoriesLink() {
    dashboardPage.clickOnCategoriesNavLink();
  
    }
}
