package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

/**
 * Created by Pawel on 2017-04-19.
 */
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private MainPage mainPage;

    //POSITIVE
    @Test
    public void guestLogin()  throws Exception {
        //inicjalizujemy tyle "stron" ile potrzebuje test, w tym przypadku tylko "strone" logowania
        loginPage = new LoginPage(driver);

        //Test przypominajacy scenariusz testowy
        System.out.println("Test [guestLogin]");

        //1. Przechodze na "strone" logowania
        Assert.assertTrue(loginPage.isLoginPageLoaded(),"ERROR: Strona logowania wyswietlila sie nieprawidlowo");
        System.out.println("INFO: Strona logowania wyswietlila sie prawidlowo");

        //2. Loguje się jako "gosc"
        Assert.assertTrue(loginPage.signInAsGuest(),"ERROR: Nie udalo sie zalogowac jako GOSC");
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isMainPageLoaded(),"ERROR: Strona glowna wyswietlila sie nieprawidlowo");
        System.out.println("INFO: Udalo sie zalogowac jako gosc");
    }

    //POSITIVE
    @Test
    public void smartsiteUserLogin() throws Exception {
        //inicjalizujemy tyle "stron" ile potrzebuje test, w tym przypadku tylko "strone" logowania
        loginPage = new LoginPage(driver);

        //Test przypominajacy scenariusz testowy
        System.out.println("Test [smartsiteUserLogin]");

        //1. Przechodze na "strone" logowania
        Assert.assertTrue(loginPage.isLoginPageLoaded(),"ERROR: Strona logowania wyswietlila sie nieprawidlowo");
        System.out.println("INFO: Strona logowania wyswietlila sie prawidlowo");

        //2. Loguje się jako "gosc"
        Assert.assertTrue(loginPage.signInAsUser(properties.getPropValue("admin_login"),properties.getPropValue("admin_password")),"ERROR: Nie udalo sie zalogowac jako USER");
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isMainPageLoaded(),"ERROR: Strona glowna wyswietlila sie nieprawidlowo");
        System.out.println("INFO: Udalo sie zalogowac jako uzytkownik");
    }

    //POSITIVE
    @Test
    public void googleUserLogin() throws Exception {
        loginPage = new LoginPage(driver);
        //Test przypominajacy scenariusz testowy
        System.out.println("Test [googleUserLogin]");

        //1. Przechodze na "strone" logowania
        Assert.assertTrue(loginPage.isLoginPageLoaded(),"ERROR: Strona logowania wyswietlila sie nieprawidlowo");
        System.out.println("INFO: Strona logowania wyswietlila sie prawidlowo");

        //2. Loguje się jako uzytkownik Google
        Assert.assertTrue(loginPage.signInAsGoogleUser(properties.getPropValue("google_login")),"ERROR: Nie udalo sie zalogowac za pomoca Google");
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isMainPageLoaded(),"ERROR: Strona glowna wyswietlila sie nieprawidlowo");
        System.out.println("INFO: Udalo sie zalogowac jako uzytkownik Google");
    }

}
