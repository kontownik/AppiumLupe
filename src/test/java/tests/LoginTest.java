package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleLoginPage;
import pages.LoginPage;
import pages.MainPage;

/**
 * Created by Pawel on 2017-04-19.
 */
public class LoginTest extends BaseTest {

    //POSITIVE
    @Test
    public void guestLogin()  throws Exception {

        //Thread.sleep(10000);
        //1. odpala sie strona logowania
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isPageLoaded();

        //2. Loguje się jako "gosc"
        //MainPage mainPage = loginPage.signInAsGuest();
        loginPage.signInAsGuest();
        MainPage mainPage = new MainPage(driver);
        mainPage.isPageLoaded();
        System.out.println("INFO: Udalo sie zalogowac jako gosc");
        Thread.sleep(5000);

    }

    //POSITIVE
    @Test
    public void smartsiteUserLogin() throws Exception {

        //1. odpala sie strona logowania
        LoginPage loginPage = new LoginPage(driver);

        //2. Loguje się jako "uzytkownik"
        MainPage mainPage = loginPage.signInAsUser(properties.getPropValue("admin_login"),properties.getPropValue("admin_password"));
        System.out.println("INFO: Udalo sie zalogowac jako uzytkownik");
    }

    //POSITIVE
    @Test
    public void googleUserLogin() throws Exception {

        //1. odpala sie strona logowania
        LoginPage loginPage = new LoginPage(driver);

        //2. Loguje się jako uzytkownik Google
        GoogleLoginPage googleLoginPage = loginPage.signInAsGoogleUser();
        googleLoginPage.loginAction(properties.getPropValue("google_login"));
        System.out.println("INFO: Udalo sie zalogowac jako uzytkownik Google");
    }

}
