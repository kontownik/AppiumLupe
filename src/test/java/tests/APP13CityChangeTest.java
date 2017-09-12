package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleLoginPage;
import pages.LoginPage;
import pages.MainPage;
import pages.MenuPage;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP13CityChangeTest extends BaseTest {

    //POSITIVE
    @Test
    public void cityChangeTest() throws Exception {

        //*Zmienne wejsciowe
        String expectedName = "Gość";
        String expectedEmail ="(brak)";

        //1. Otwiera się strona logowania
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isPageLoaded();

        //2. Loguje się jako "Gość" za pomocą przycisku (przekierowanie na stronę główna)
        loginPage.signInAsGuest();
        MainPage mainPage = new MainPage(driver);
        mainPage.isPageLoaded();
        System.out.println("INFO: Udało się zalogować jako użytkownik 'Gość'");

        //3. Jezeli pojawil sie wybor miasta wybiera domyslne

    }

}
