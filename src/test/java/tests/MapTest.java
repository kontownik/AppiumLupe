package tests;

import org.testng.Assert;
import pages.*;

/**
 * Created by Pawel on 2017-04-19.
 */
public class MapTest extends BaseTest {

    public void mapTest(String value) throws Exception {

        //* Zmienne wejsciowe
        String defaultCity = "Białystok"; //TODO: dodac do properties?
        Integer countOnMenu = 0;

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

        // Zbieram liczbę zgłoszeń z menu
        if(value.equals("Mapa zgłoszeń")) {
            countOnMenu = menuPage.getReportsCount();
        }

        //5. Przechodzę na mapę zgłoszeń (Mapa zgłoszeń)
        menuPage.scrollToTextAndClick(value);
        System.out.println("INFO: Menu boczne, przejście do '"+value+"'");

        //Wyświetla się Mapa
        MapPage mapPage = new MapPage(driver);
        mapPage.isPageLoaded();
        System.out.println("INFO: Udało się załadować Mapę zgłoszeń");

        //Zlicza ile jest markerow na mapie i porównuje z liczbą na menu
        System.out.println("WARNING: Markerów na menu "+countOnMenu+". Markerów na mapie "+mapPage.getMarkersCount());

        //Wybieram pierwszy marker
        mapPage.clickMarkerByIndex(0);

        //Sprawdzam czy pokazało się okno Szczegółów (sprawdzam wartości pól wartości)
        mapPage.isDetailsLoaded();
        mapPage.printCurrentDetails();
        mapPage.clickDetailsButton();
    }

}
