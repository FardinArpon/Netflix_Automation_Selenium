package TestRunner;

import Pages.Login;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class LoginTestRunner extends Setup {
    Login objLogin;
    Utils utils;

     @Test(enabled =true, priority = 1, description = "Login with registered user ID or email and password",groups = "login")
    public void doLogin() throws Exception {
        driver.get("https://www.netflix.com/bd/");
        objLogin = new Login(driver);

        utils =new Utils(driver);
        utils.readJSONArray(0);
        String user = objLogin.doLogin(utils.getEmail(), utils.getPassword());
        Assert.assertEquals(user, "Welcome back!");
        sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
        sleep(2000);
         utils.addDescription("User can logged in successfully with registered user ID or email and password");
    }
    @Test(enabled = true, priority = 3, description = "Login with wrong password", groups = "login")
    public void doLoginForWrongPassword() throws Exception {
        driver.get("https://www.netflix.com/bd/");
        objLogin = new Login(driver);

        utils =new Utils(driver);
        utils.readJSONArray(2);

        String authError=objLogin.doLoginForWrongPassword(utils.getEmail(),utils.getPassword());
       // Assert.assertEquals(authError,"Incorrect password. Please try again or you can reset your password.");
        Assert.assertEquals(authError,"Forgot Email/Password");
        sleep(2000);
        //driver.findElement(By.xpath("//a[contains(text(),'reset your password.')]")).click();
        utils.addDescription("Authentication error shows when user provides wrong Password");

    }
    @Test(enabled = true, priority = 2, description = "Login with Non-registered user ID or email", groups = "login")
    public void doLoginInvalidEmail() throws Exception {
        driver.get("https://www.netflix.com/bd/");
        objLogin = new Login(driver);

        utils =new Utils(driver);
        utils.readJSONArray(1);

        String error=objLogin.doLoginForInvalidEmail(utils.getEmail(),utils.getPassword());
        Assert.assertEquals(error,"Sorry, we can't find an account with this email address. Please try again or create a new account.");
        sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'create a new account')]")).click();
        sleep(2000);
        utils.addDescription("Authentication error shows when user provides Non-registered User ID or email");

    }

    @Test(enabled = true, priority = 4, description = " Login with his/her facebook ID",groups = "login")
    public void loginWithFB() throws Exception {
        driver.get("https://www.netflix.com/bd/");
        objLogin = new Login(driver);
        utils =new Utils(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//span[@class='fbBtnText']")).click();
        sleep(2000);
        ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles());
        //switch to open tab
        driver.switchTo().window(w.get(1));

        Boolean status =
                driver.findElement(By.xpath("//span[@class='_50f7']")).isDisplayed();
        org.junit.Assert.assertEquals(true, status);
        driver.close();
        driver.switchTo().window(w.get(0));
        utils.addDescription("User can Login with his/her facebook ID ");
    }



    @Test(enabled = true, priority = 5, description = "Check all footer links and login Modals", groups= "others")
    public void doCheckModalsAndOtherLinks() throws Exception {
         driver.get("https://www.netflix.com/bd/");
         objLogin = new Login(driver);
        utils =new Utils(driver);

         String footer = objLogin.checkModals_and_Link();
         Assert.assertEquals(footer,"Save settings");
        sleep(2000);

        utils.addDescription("User can check all Footer links and Modals option");

    }




}
