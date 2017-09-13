package tests;

import pages.*;

/**
 * Created by Pawel on 2017-04-19.
 */
public class MapTest extends BaseTest {

    public void mainListTest(String value) throws Exception {

        //* Zmienne wejsciowe
        String defaultCity = "Białystok"; //TODO: dodac do properties?

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

        //5. Przechodzę na mapę zgłoszeń lub komunikatów (Mapa zgłoszeń)
        menuPage.scrollToTextAndClick(value);
        System.out.println("INFO: Menu boczne, przejście do '"+value+"'");

        //Wyświetla się Mapa
        MapPage mapPage = new MapPage(driver);
        mapPage.isPageLoaded();
        System.out.println("INFO: Udało się załadować Mapę zgłoszeń");

        //Wybieram pierwszy marker
        mapPage.clickMarkerByIndex(0);

        //Sprawdzam czy pokazało się okno Szczegółów (sprawdzam wartości pól wartości)
        mapPage.isDetailsLoaded();
        mapPage.printCurrentDetails();

        //Otwieram menu
        mainPage.openMenu();
        menuPage = new MenuPage(driver);
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //Wylogowuje się przez menu
        menuPage.scrollToTextAndClick("Wyloguj");
        System.out.println("INFO: Wylogowano przez boczne menu");

    }

}
