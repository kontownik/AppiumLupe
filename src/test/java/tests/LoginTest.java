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
public class LoginTest extends BaseTest {

    //POSITIVE
    @Test
    public void guestLogin()  throws Exception {

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

        //3. Sprawdzam czy lista zgłoszeń nie jest pusta
        Assert.assertFalse(mainPage.isListEmpty(),"ERROR: Lista główna jest pusta!");

        //4. Otwieram menu
        mainPage.openMenu();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //5. Sprawdzam czy menu ma odpowiednią liczbę wpisów
        menuPage.printMenuItems(); // UWAGA: to jest liczba wpisow na menu widoczna na ekranie!
        //Assert.assertEquals(menuPage.getMenuSize(),6); // UWAGA: to jest liczba wpisow na menu widoczna na ekranie!
        //TODO: zmienic na array pozycji w menu po nazwie

        //6. Sprawdzam czy jestem zalogowany jako Gość (name i email)
        Assert.assertTrue(menuPage.getPersonName().equals(expectedName),"ERROR: Wyswietlona nazwa uzytkownika jest inna niz spodziewana");
        Assert.assertTrue(menuPage.getPersonEmail().equals(expectedEmail),"ERROR: Wyswietlona email uzytkownika jest inny niz spodziewany");

        //7. Wylogowuje się przez menu
        menuPage.scrollToTextAndClick("Wyloguj");
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
