package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CityChoosePage;
import pages.LoginPage;
import pages.MainPage;
import pages.MenuPage;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP4GuestLoginTest extends BaseTest {

    //POSITIVE
    @Test
    public void guestLoginTest() throws Exception {

        //* Zmienne wejsciowe
        String defaultCity = "Białystok"; //TODO: dodac do properties?
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
        CityChoosePage cityChoose =  mainPage.getCityChooseIfVisible();
        if(cityChoose instanceof CityChoosePage) {
            cityChoose.isPageLoaded();
            cityChoose.scrollToTextAndClick(defaultCity);
            System.out.println("INFO: Wybrano domyślne miasto '"+defaultCity+"'");
        }
        //4. Otwieram menu
        mainPage.openMenu();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //5. Sprawdzam czy jestem zalogowany jako Gość (name i email)
        Assert.assertTrue(menuPage.getPersonName().equals(expectedName),"ERROR: Wyswietlona nazwa uzytkownika jest inna niz spodziewana");
        Assert.assertTrue(menuPage.getPersonEmail().equals(expectedEmail),"ERROR: Wyswietlona email uzytkownika jest inny niz spodziewany");
        System.out.println("INFO: Jestem zalogowany jako: "+menuPage.getPersonName()+" email: "+menuPage.getPersonEmail());

        //6. Wylogowuje się przez menu
        menuPage.scrollToTextAndClick("Wyloguj");
        System.out.println("INFO: Wylogowano przez boczne menu");
    }

}
