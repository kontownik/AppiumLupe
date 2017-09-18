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
        String[] categoryArray = { "Awaria oświetlenia ulicznego", "Dewastacja mienia","Komunikacja", "Porządek i śmieci", "Wodociągi i kanalizacja", "Zagrożenie bezpieczeństwa", "Zieleń", "Inne" };
        String newCategory = categoryArray[(int) (Math.random() * categoryArray.length)];
        String newDescription = "APPIUM opis wygenerowany automatycznie dla kategorii "+newCategory;
        String newPictureFilename = "";

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

        //4. Dodanie zgłoszenia przez przycisk (+)
        mainPage.useAddButton();
        GoogleMapPage newMarkerMap = new GoogleMapPage(driver);
        newMarkerMap.isPageLoaded();

        //Potwierdza miejsce wskazywane przez marker
        newMarkerMap.pickPlace();
        System.out.println("INFO: Wybrano lokalizację za pomocą markera");

        //card?
        //Potwierzenie lokalizacji (przechodzi na stronę edycji)
        newMarkerMap.confirmCard();
        System.out.println("INFO: Potwierdzono lokalizację zgłoszenia");

        //Wyświetla się strona dodawania nowego zgłoszenia
        AddEventPage addEventPage = new AddEventPage(driver);
        addEventPage.isPageLoaded();
        System.out.println("INFO: Udało się wyświetlić stronę dodawania nowego zgłoszenia");

        //Wypelniam formularz danymi i wysyłam
        addEventPage.addReport(newCategory, newDescription, newPictureFilename);

        //Sprawdzam czy przeszlo na stronę główną i wyświetlił się dobry komunikat
        CityChoosePage successPopup = new CityChoosePage(driver);
        successPopup.isPageLoaded();
        successPopup.verifyReportAdded();
        System.out.println("INFO: Pojawiło się okno potwierdzające dodanie zgłoszenia");

        //15. Otwieram menu
        mainPage.openMenu();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.isPageLoaded();
        System.out.println("INFO: Udało się otworzyć menu boczne");

        //16. Wylogowuje się przez menu
        menuPage.scrollToTextAndClick("Wyloguj");
        System.out.println("INFO: Wylogowano przez boczne menu");

    }

}
