package tests;

import org.testng.annotations.Test;
import pages.CityChoosePage;
import pages.LoginPage;
import pages.MainPage;
import pages.MenuPage;

/**
 * Created by Pawel on 2017-04-19.
 */
public class MainListTest extends BaseTest {

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
            cityChoose.verifyCityList();
            cityChoose.scrollToTextAndClick(defaultCity);
            System.out.println("INFO: Wybrano domyślne miasto '"+defaultCity+"'");
        }

        //4. Otwieram menu
        mainPage.openMenu();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //5. Przechodzę na listę zgłoszeń lub komunikatów (Lista zgłoszeń / Komunikaty)
        menuPage.scrollToTextAndClick(value);
        System.out.println("INFO: Menu boczne, przejście do '"+value+"'");

        //5. Sprawdzam czy lista zgłoszeń nie jest pusta (jeżeli tak wylogowuje się i kończę test)
        if(mainPage.isListEmpty()){
            System.out.println("INFO: Lista główna jest pusta!");
            mainPage.openMenu();
            menuPage = new MenuPage(driver);
            menuPage.isPageLoaded();
            System.out.println("INFO: Udało się otworzyć menu boczne");
            menuPage.scrollToTextAndClick("Wyloguj");
            System.out.println("INFO: Wylogowano przez boczne menu");
        }

        //5. Sprawdzam wpisy na liście
        mainPage.verifyAndPrintVisibleItems(value);

        //6. Otwieram menu
        mainPage.openMenu();
        menuPage = new MenuPage(driver);
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //7. Wylogowuje się przez menu
        menuPage.scrollToTextAndClick("Wyloguj");
        System.out.println("INFO: Wylogowano przez boczne menu");

    }

}
