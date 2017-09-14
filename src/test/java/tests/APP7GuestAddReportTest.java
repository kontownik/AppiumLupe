package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Created by Pawel on 2017-04-19.
 */
public class APP7GuestAddReportTest extends BaseTest {

    //POSITIVE
    @Test
    public void guestAddReportTest() throws Exception {

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

        //4. Dodanie zgłoszenia przez przycisk (+)
        mainPage.useAddButton();
        GoogleMapPage newMarkerMap = new GoogleMapPage(driver);
        newMarkerMap.isPageLoaded();

        //Potwierdza miejsce wskazywane przez marker
        newMarkerMap.pickPlace();

        //card?
        //Potwierzenie lokalizacji (przechodzi na stronę edycji)
        newMarkerMap.confirmCard();

        //Wyświetla się strona dodawania nowego zgłoszenia
        AddEventPage addEventPage = new AddEventPage(driver);
    }

}
