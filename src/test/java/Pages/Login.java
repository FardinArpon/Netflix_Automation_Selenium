package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;

    //###___Login-modals___###
    @FindBy(xpath = "//a[contains(text(),'Sign In')]")
    WebElement linkLogin;
    @FindBy(xpath = "//input[@id='id_userLoginId']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@id='id_password']")
    WebElement txtPassword;
    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    WebElement btnSubmit;
    @FindBy(xpath = "//a[contains(text(),'Sign Out')]")
    WebElement btnSignOut;
    @FindBy(xpath = " //a[contains(text(),'reset your password.')]")
    WebElement resetPW;
    @FindBy(xpath = " //a[contains(text(),'create a new account')]")
    WebElement regNewAcc;
    @FindBy(xpath = " //h1[@data-uia='password-reset-header']")
    WebElement forgotPWmsg;




    //###___More options From Modal___###
    @FindBy(xpath = "//label[@for='bxid_rememberMe_true']")
    WebElement remBox;
    @FindBy(xpath = "//a[@class='link login-help-link']")
    WebElement needHelp;
    @FindBy(xpath = "//button[@class='recaptcha-terms-of-use--link-button']")
    WebElement learnMore;
    @FindBy(xpath = "//*[@class='svg-icon svg-icon-netflix-logo']")
    WebElement netflixHome;
    @FindBy(xpath = "//span[@class='fbBtnText']")
    WebElement fbLogin;
    @FindBy(xpath = "//a[contains(text(),'Sign up now')]")
    WebElement signUp;

    //###___Prompt-Message___###
    @FindBy(xpath = "//span[contains(text(),'Welcome back!')]")
    WebElement welcomeMsg;
    @FindBy(xpath = "//div[@class='ui-message-contents']")
    WebElement regError;
    @FindBy(xpath = "//div[@class='ui-message-contents']")
    WebElement passError;



    //####___Footer-Part___####
    @FindBy(xpath = "//a[@class='footer-top-a']")
    WebElement contactUs;
    @FindBy(xpath = "//span[contains(text(),'FAQ')]")
    WebElement FAQ;
    @FindBy(xpath = "//span[contains(text(),'Cookie Preferences')]")
    WebElement cookie;
    @FindBy(xpath = "//button[@class='save-preference-btn-handler onetrust-close-btn-handler']")
    WebElement saveCookie;
    @FindBy(xpath = "//span[contains(text(),'Help Center')]")
    WebElement helpCenter;
    @FindBy(xpath = "//span[contains(text(),'Corporate Information')]")
    WebElement corInfo;
    @FindBy(xpath = "//span[contains(text(),'Terms of Use')]")
    WebElement termsUse;
    @FindBy(xpath = "//span[contains(text(),'Privacy')]")
    WebElement privacy;


    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String doLogin(String email, String password) throws InterruptedException {

        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        remBox.click();
        btnSubmit.click();
        return welcomeMsg.getText();


    }

    public String doLoginForWrongPassword(String email, String password) throws InterruptedException {

        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        Thread.sleep(2000);
        resetPW.click();

        return forgotPWmsg.getText();

    }

    public String doLoginForInvalidEmail(String email, String password) throws InterruptedException {


        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return regError.getText();

    }



    public String checkModals_and_Link() {

        linkLogin.click();
        remBox.click();
        learnMore.click();
        needHelp.click();
        linkLogin.click();
        signUp.click();

        linkLogin.click();
        contactUs.click();
        linkLogin.click();
        FAQ.click();
        linkLogin.click();
        helpCenter.click();
        linkLogin.click();
        corInfo.click();
        linkLogin.click();
        termsUse.click();
        linkLogin.click();
        privacy.click();
        linkLogin.click();
        cookie.click();
        return saveCookie.getText();


    }
}