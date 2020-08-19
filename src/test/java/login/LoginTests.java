
package login;

import base.BaseTest;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.LoginPage;


public class LoginTests extends BaseTest {
    
    private boolean loggedIn = false;
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
       
    }
    
    @Test
    public void testValidLogin() throws InterruptedException {
       
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfValidUser();
        loginPage.enterPasswordOfValidUser();
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/admin";
        String actualUrl = driver.getCurrentUrl();
        
         assertEquals("Url's don't match.", expectedUrl, actualUrl);
         
         DashboardPage dashboardPage = new DashboardPage(driver);
         String expectedPanelHeadingText = "Dashboard";
         String actualPanelHeadingText = dashboardPage.getPanelHeadingText();
         assertTrue("Failed - panel heading texts don't match", expectedPanelHeadingText.equals(actualPanelHeadingText));

         dashboardPage.logout();
         driver.get("http://bvtest.school.cubes.rs/login");
        
         
         
    }
    
    @Test
    public void testEmptyFieldsLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's don't match.", expectedUrl, actualUrl);
        
        String expectedEmailMessage = "The email field is required.";
        String actualEmailMessage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match" , expectedEmailMessage.equals(actualEmailMessage));
//        WebElement emailRequiredAlert = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/span/strong"));
//        WebElement passwordRequiredAlert = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/span/strong"));
//        
//        if ((emailRequiredAlert.getText().contentEquals("The email field is required."))
//        &&
//        (passwordRequiredAlert.getText().contentEquals("The password field is required.")))
//        {
//        System.out.println("Test passed");
//        } 
//        else {
//        System.out.println("Test failed.");
//        }
            }
    
    @Test
    public void testInvalidEmailInvalidPasswordLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        //1. Enter invalid email in Email Field
        loginPage.enterEmailOfNonExistingUser();
       //2. Enter invalid password
        loginPage.enterPasswordOfNonExistingUser();
       //3. Click on Login Button
        loginPage.clickLoginButton();
        Thread.sleep(4000);
        
       // Expected result: not logged in, message shown
       
       String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        
        assertEquals("Url's don't match.", expectedUrl, actualUrl);
        
        String expectedEmailMessage = "These credentials do not match our records.";
        String actualEmailMessage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match" , expectedEmailMessage.equals(actualEmailMessage));
    }
    
    @Test
    public void testValidEmailInvalidPasswordLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfValidUser();
        loginPage.enterPasswordOfNonExistingUser();
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's don't match.", expectedUrl, actualUrl);
        
        String expectedEmailMessage = "These credentials do not match our records.";
        String actualEmailMessage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match" , expectedEmailMessage.equals(actualEmailMessage));
        
    }
    
    @Test
    public void testInvalidEmailValidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfNonExistingUser();
        loginPage.enterPasswordOfValidUser();
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's don't match.", expectedUrl, actualUrl);
        
        String expectedEmailMessage = "These credentials do not match our records.";
        String actualEmailMessage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match" , expectedEmailMessage.equals(actualEmailMessage));
        
        
    }
    
    @Test
    public void testCustomDataLogin() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterEmail("hello@dragana.rs");
    loginPage.enterPassword("123456");
    loginPage.clickLoginButton();
    
    
    }
    
    @Test
    public void testValidEmailEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfValidUser();
        loginPage.clickLoginButton();
    
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's don't match.", expectedUrl, actualUrl);
        
        String expectedPasswordMessage = "The password field is required.";
        String actualPasswordMessage = loginPage.getPasswordMessageText();
        Assert.assertTrue("Failed - Password message's doesn't match", expectedPasswordMessage.equals(actualPasswordMessage));
    }
}
