package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP13CityChangeTest extends BaseTest {

    //POSITIVE
    @Test
    public void cityChangeTest() throws Exception {

        //* Zmienne wejsciowe
        String defaultCity = "Białystok"; //TODO: dodac do properties?
        String otherCity = "Łomża";
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

        //TODO: 4. Sprawdzam czy nad listą pojawiła się spodziewana nazwa miasta

        //5. Otwieram menu
        mainPage.openMenu();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //6. Sprawdzam czy jestem zalogowany jako Gość (name i email)
        Assert.assertTrue(menuPage.getPersonName().equals(expectedName),"ERROR: Wyswietlona nazwa uzytkownika jest inna niz spodziewana");
        Assert.assertTrue(menuPage.getPersonEmail().equals(expectedEmail),"ERROR: Wyswietlona email uzytkownika jest inny niz spodziewany");

        //7. Wybieram z bocznego menu "Zmień miasto"
        menuPage.scrollToTextAndClick("Zmień miasto");

        //8.Sprawdzam czy wyświetlilo się okno wyboru
        cityChoose = new CityChoosePage(driver);
        cityChoose.isPageLoaded();

        //9. Wybieram miasto Lomza
        cityChoose.scrollToTextAndClick(otherCity);
        System.out.println("INFO: Wybrano miasto '"+otherCity+"'");

        //TODO: 10. Sprawdzam czy nad listą pojawiła się spodziewana nazwa miasta

        //11. Otwieram menu
        mainPage.openMenu();
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //12. Wybieram z bocznego menu "Zmień miasto"
        menuPage.scrollToTextAndClick("Zmień miasto");

        //13.Sprawdzam czy wyświetlilo się okno wyboru
        cityChoose.isPageLoaded();

        //14. Wybieram miasto domyślne (Białystok)
        cityChoose.scrollToTextAndClick(defaultCity);
        System.out.println("INFO: Wybrano domyślne miasto '"+defaultCity+"'");

        //15. Otwieram menu
        mainPage.openMenu();
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //16. Wylogowuje się przez menu
        menuPage.scrollToTextAndClick("Wyloguj");
        System.out.println("INFO: Wylogowano przez boczne menu");
    }

}
